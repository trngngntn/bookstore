var id = 0;

function initPage(){
    if(id === 0){
        if(window.history.state == null){
            window.history.replaceState({id: id, url: window.location.href, title: document.title} , "");
        } else {
            id = window.history.state.id;
        }
    }
}

function changePage(url, title){
    if(title == null) document.title;
    window.history.pushState({id: ++id, url: window.location.origin + url, title: title} , "", url);
    document.title = title;
    document.getElementById("page-title").innerHTML = title;
    loadPage(url, 0);
}

window.addEventListener('popstate', (event) =>{
    //console.log(event.state);
    document.title = event.state.title;
    document.getElementById("page-title").innerHTML = event.state.title;
    loadPage(event.state.url, event.state.id<id?1:0);
    id = event.state.id;
})

function updatePage(newContent, type){
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

function updatePageSearch(newContent){
    const main = document.getElementById("main");
    main.innerHTML = "";
    const newPage = new DOMParser().parseFromString(newContent, 'text/html').getElementsByTagName("BODY")[0];
    let newView = newPage.firstChild;
    main.appendChild(newView);
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
}

function loadPageSearch(url){
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            updatePageSearch(xhr.responseText);
        }
    };
    xhr.open("GET", `${url}&raw`, true);
    xhr.send();
}

function backToList(){
    const addView = document.getElementById("add-view");
    const listView = document.getElementById("list-view");
    listView.className = "view";
    addView.className = "view hide-top";
}

function toAdd(){
    const addView = document.getElementById("add-view");
    const listView = document.getElementById("list-view");
    addView.className = "view on-top";
}

function buildParameter(data){
    let result = "";
    for (let entry of data.entries()) {
        result += entry[0] + "=" + entry[1] + "&";
    }
    return result;
}
function countParameter(data){
    let result = 0;
    for (let entry of data.entries()) {
        result++;
    }
    return result;
}

function submitAddForm(){
    const form = document.getElementById("add-form");
    let formData = new FormData(form);
    console.log(buildParameter(formData));
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log(xhr.responseText);
        }
    };
    xhr.open("POST", `${form.getAttribute("action")}`, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    //xhr.setRequestHeader("Content-Length", countParameter(formData).toString());
    xhr.send(buildParameter(formData));
}

function querySearch(){
    const searchValue = document.getElementById("search-area").value;
    let url = `${window.location.pathname}?keyword=${searchValue}`;
    window.history.pushState({id: ++id, url: window.location.origin + url, title: document.title} , "",url);
    loadPageSearch(url);
}