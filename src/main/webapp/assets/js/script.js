function changePage(url){
    window.history.pushState(null, "", url);
    document.title = "New";
    doAjaxGet(url);
}

function updatePage(newContent){
    const main = document.getElementById("main");
    const listView = document.getElementById("list-view");
    const addView = document.getElementById("add-view");
    main.removeChild(addView);
    const editView = new DOMParser().parseFromString(newContent, 'text/html').getElementById("edit-view");

    listView.ontransitionend = function (){
        main.removeChild(listView);
        console.log("DELETED");
    };
    listView.ontransitionstart = function (){
        editView.className = "view";
    };
    editView.className = "view hide-right";
    main.appendChild(editView);
    listView.className = "view hide-left";

}

function doAjaxGet(url){
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            updatePage(xhr.responseText);
        }
    };
    xhr.open("GET", `${url}?raw`, true);
    xhr.send();
}

function backToList(){
    var addView = document.getElementById("add-view");
    var listView = document.getElementById("list-view");
    listView.className = "view";
    addView.className = "view hide-right";
}

function toAdd(){
    var addView = document.getElementById("add-view");
    var listView = document.getElementById("list-view");
    listView.className = "view hide-left";
    addView.className = "view";
}