function checkFullname(){
    var title = document.getElementById("fullname");
    var errNameEmpty = document.getElementById("err-name-empty");
    var errNameLength = document.getElementById("err-name-length");
    if(title.value == ""){
        errNameEmpty.className = "";
        errNameLength.className = "hidden";
        return 0;
    } else if (title.value.length > 50){
        errNameEmpty.className = "hidden";
        errNameLength.className = "";
        return 0;
    } else {
        errNameEmpty.className = "hidden";
        errNameLength.className = "hidden";
        return 1;
    }
}

function checkPrice(){
    var price = document.getElementById("price");
    var errPriceEmpty = document.getElementById("err-price-empty");
    var errPriceLength = document.getElementById("err-price-range");
    if(price.value == ""){
        errPriceEmpty.className = "";
        errPriceLength.className = "hidden";
        return 0;
    } else if(price.value < 0){
        errPriceEmpty.className = "hidden";
        errPriceLength.className = "";
        return 0;
    } else {
        errPriceEmpty.className = "hidden";
        errPriceLength.className = "hidden";
        return 1;
    }
}

function checkAuthorId(){
    var author = document.getElementById("author");
    var errAuthorEmpty = document.getElementById("err-author-empty");
    if(author.value == ""){
        errAuthorEmpty.className = "";
        return 0;
    } else {
        errAuthorEmpty.className = "hidden";
        return 1;
    }
}

function checkStatus(){
    var status = document.querySelector('input[name="status"]:checked');
    var errStatusEmpty = document.getElementById("err-status-empty");
    if(status == null){
        errStatusEmpty.className = "";
        return 0;
    } else {
        errStatusEmpty.className = "hidden";
        return 1;
    }
}

function checkAddBookFields(){
    var title = document.getElementById("title");
    var price = document.getElementById("price");
    var author = document.getElementById("author");
    var addBookForm = document.getElementById("form-add-book");
    var f = 1;
    if(!checkStatus()){
        author.focus();
        f = 0;
    }
    if(!checkAuthorId()){
        author.focus();
        f = 0;
    }
    if(!checkPrice()){
        price.focus();
        f = 0;
    }
    if(!checkTitle()) {
        title.focus();
        f = 0;
    }
    if(f == 1) {
        addBookForm.submit();
    }
}

function checkUpdBookFields(){
    var title = document.getElementById("title");
    var price = document.getElementById("price");
    var author = document.getElementById("author");
    var updBookForm = document.getElementById("form-update-book");
    var f = 1;
    if(!checkStatus()){
        author.focus();
        f = 0;
    }
    if(!checkAuthorId()){
        author.focus();
        f = 0;
    }
    if(!checkPrice()){
        price.focus();
        f = 0;
    }
    if(!checkTitle()) {
        title.focus();
        f = 0;
    }
    if(f == 1) {
        updBookForm.submit();
    }
}

function checkAuthorName(){
    var author = document.getElementById("author");
    var errAuthorEmpty = document.getElementById("err-author-empty");
    var errAuthorLength = document.getElementById("err-author-length");
    if(author.value == ""){
        errAuthorEmpty.className = "";
        errAuthorLength.className = "hidden";
        return 0;
    } else if (author.value.length > 50){
        errAuthorEmpty.className = "hidden";
        errAuthorLength.className = "";
        return 0;
    } else {
        errAuthorEmpty.className = "hidden";
        errAuthorLength.className = "hidden";
        return 1;
    }
}

function checkAddAuthorFields(){
    var author = document.getElementById("author");
    var addAuthorForm = document.getElementById("form-add-author");
    if(!checkAuthorName()){
        author.focus();
    } else {
        addAuthorForm.submit();
    }
}
