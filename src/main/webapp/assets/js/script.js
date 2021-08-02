var id = 0;

function initPage() {
    if (id === 0) {
        if (window.history.state == null) {
            window.history.replaceState({
                id: id,
                url: window.location.href,
                title: document.title,
                hasParam: window.location.search != null && window.location.search != ""
            }, "");
        } else {
            id = window.history.state.id;
        }
    }
    if(document.getElementById("edit-form") != null){
        duplicate();
    }
}

window.addEventListener('popstate', (event) => {
    //console.log(event.state);
    document.title = event.state.title;
    document.getElementById("page-title").innerHTML = event.state.title;
    console.log("POP: " + event.state.url);
    loadPage(event.state.url, event.state.hasParam);
    id = event.state.id;
})

function changePage(url, hasParam = false, title = document.title) {
    if(url[0] == '?'){
        url = window.location.pathname + url;
    }
    window.history.pushState({id: ++id, url: url, title: title, hasParam: hasParam}, "", url);
    console.log(window.location.href);
    document.title = title;
    document.getElementById("page-title").innerHTML = title;
    loadPage(url, hasParam);
    hideStatus();
}

function changePageIndex(index) {
    console.log(window.location.search);
    if (window.location.search == null || window.location.search == "" ) {
        changePage(`${window.location.pathname}?index=${index}`, true);
    } else {
        changePage(`${window.location.pathname}&index=${index}`, true);
    }
}


function loadPage(url, hasParam) {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            updatePage(xhr.responseText);
        }
    };
    xhr.open("GET", `${url}${hasParam ? '&' : '?'}raw`, true);
    xhr.send();
}

function updatePage(newContent) {
    const main = document.getElementById("main");
    main.innerHTML = newContent;
    if(document.getElementById("edit-form") != null){
        duplicate();
    }
}

function backToList() {
    const addView = document.getElementById("add-view");
    const listView = document.getElementById("list-view");
    listView.className = "view";
    addView.className = "view hide-top";
    let rows = document.querySelectorAll(".invalid");
    rows.forEach(function(row){
        row.className = "";
    });

    let errors = document.querySelectorAll(".error:not(.status-message)");
    errors.forEach(function(error){
        error.className = "error hidden";
    });
}

function toAdd() {
    const addView = document.getElementById("add-view");
    const listView = document.getElementById("list-view");
    addView.className = "view on-top";
    hideStatus();
}

function hideStatus(){
    document.getElementById("added-status").className = "status-message success hidden";
    document.getElementById("updated-status").className = "status-message success hidden";
    document.getElementById("db-error-status").className = "status-message error hidden";
}

function hideErrors(row){
    let child = row.childNodes[3].childNodes;
    for(let i = 0; i < child.length; i++){
        //console.log(child[i]);
        if(child[i].className == "error"){
            child[i].className = "error hidden";
        }
    }
}

function buildParameter(form, type) {
    let formData = new FormData(form);
    let jsonObject = {};
    let failed = 0;
    for (let entry of formData.entries()) {
        jsonObject[entry[0]] = entry[1];
        if((type == 1 && entry[1] != old[entry[0]]) || type == 0){
            let elmRow = document.getElementById(`${entry[0]}-row`);
            if(elmRow.getAttribute("func") == "") continue;
            let func = window[elmRow.getAttribute("func")];
            console.log("FUNC: " + elmRow.getAttribute("func") + " : " +`${entry[0]}-row`);
            let checkResult = func(entry[1]);
            if(checkResult != 0){
                failed = 1;
                elmRow.className = "invalid";
                hideErrors(elmRow);
                console.log("RES: " + `error-${entry[0]}-${checkResult}`);
                document.getElementById(`error-${entry[0]}-${checkResult}`).className = "error";
            } else {
                elmRow.className = "";
                hideErrors(elmRow);
            }
        }
    }
    return failed == 1 ? 0 : jsonObject;
}

function submitAddForm() {
    const form = document.getElementById("add-form");
    let obj = buildParameter(form, 0);
    if(obj == 0) return;
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log(xhr.responseText);
            if(xhr.responseText == "success"){
                changePage(window.location.pathname);
                document.getElementById("added-status").className = "status-message success";

            } else {
                document.getElementById("db-error-status").className = "status-message error";
            }
        }
    };
    xhr.open("POST", `${form.getAttribute("action")}`, true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.send(JSON.stringify(obj));
}

function submitEditForm() {
    const form = document.getElementById("edit-form");
    let obj = buildParameter(form, 1);
    if(obj == 0) return;
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log(xhr.responseText);
            if(xhr.responseText == "success"){
                let url = window.location.pathname.substring(0, window.location.pathname.lastIndexOf("/"));
                changePage(url);
                document.getElementById("updated-status").className = "status-message success";
            } else {
                document.getElementById("db-error-status").className = "status-message error";
            }
        }
    };
    xhr.open("PUT", `${form.getAttribute("action")}`, true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.send(JSON.stringify(obj));
}

function querySearch() {
    const searchValue = document.getElementById("search-area").value;
    let elm = document.getElementById("date-filter");
    let url = `${window.location.pathname}?`;
    let hasParam = true;

    if(searchValue == null || searchValue.trim() == ""){
        //console.log(elm.value);
        if(elm != null && elm.value != null && elm.value != ""){
            url = url + `filter=${document.title == "Trip Manager" ? "departureDate" : "bookedTime"}&keyword=${elm.value}`;
        } else {
            url = `${window.location.pathname}`;
            hasParam = false;
        }
    } else {
        const filter = document.getElementById("filter-select").value;
        if(elm != null && elm.value != null && elm.value != ""){
            url = url + `filter=${document.title == "Trip Manager" ? "departureDate" : "bookedTime"}&keyword=${elm.value}&`;
        }
        url = url + `filter=${filter}&keyword=${searchValue.trim()}`;
    }
    window.history.pushState({id: ++id, url: window.location.origin + url, title: document.title, hasParam: hasParam}, "", url);
    loadPage(url, hasParam);
}