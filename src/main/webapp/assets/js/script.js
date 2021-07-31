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
}




/*function updatePage(newContent, type){
    const main = document.getElementById("main");
    const mainChild = main.childNodes;
    let onView;
    for(let i = mainChild.length - 1; i>=0; i--){
        if(mainChild[i].className != "view"){
            main.removeChild(mainChild[i]);
        } else {
            onView = mainChild[i];
        }
    }
    const newPage = new DOMParser().parseFromString(newContent, 'text/html').getElementsByTagName("BODY")[0];
    let newView = newPage.firstChild;
    newPage.removeChild(newView);
    let newElm = newPage.innerHTML;
    onView.ontransitionstart = function (){
        newView.className = "view";
    };
    onView.ontransitionend = function (){
        setTimeout(() => {
            main.removeChild(onView);
            main.innerHTML = main.innerHTML + newElm;
        }, 10);
    };

    newView.className = type == 0?"view hide-right":"view hide-left";
    main.appendChild(newView);
    onView.className = type == 0?"view hide-left":"view hide-right";
}

function loadPage(url, type){
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            updatePage(xhr.responseText, type);
        }
    };
    xhr.open("GET", `${url}?raw`, true);
    xhr.send();
}*/


/*function loadPageSearch(url){
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            updatePageSearch(xhr.responseText);
        }
    };
    xhr.open("GET", `${url}&raw`, true);
    xhr.send();
}*/

function backToList() {
    const addView = document.getElementById("add-view");
    const listView = document.getElementById("list-view");
    listView.className = "view";
    addView.className = "view hide-top";
}

function toAdd() {
    const addView = document.getElementById("add-view");
    const listView = document.getElementById("list-view");
    addView.className = "view on-top";
}

function buildParameter(form) {
    let formData = new FormData(form);
    let jsonObject = {};
    for (let entry of formData.entries()) {
        jsonObject[entry[0]] = entry[1];
    }
    return jsonObject;
}

function submitAddForm() {
    const form = document.getElementById("add-form");
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log(xhr.responseText);
        }
    };
    xhr.open("POST", `${form.getAttribute("action")}`, true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.send(JSON.stringify(buildParameter(form)));
}

function submitEditForm() {
    const form = document.getElementById("edit-form");
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log(xhr.responseText);
        }
    };
    xhr.open("PUT", `${form.getAttribute("action")}`, true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.send(JSON.stringify(buildParameter(form)));
}

function querySearch() {
    const searchValue = document.getElementById("search-area").value;
    let url = `${window.location.pathname}?keyword=${searchValue}`;
    window.history.pushState({id: ++id, url: window.location.origin + url, title: document.title, hasParam: true}, "", url);
    loadPage(url, true);
}