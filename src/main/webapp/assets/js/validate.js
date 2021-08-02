var old;

function duplicate() {
    old = buildParameter(document.getElementById("edit-form"), 2);
    console.log("DUPLICATED");
}

function checkString(value) {
    console.log("checking");
    return (value == null || value.trim() == "") ? 1 : 0;
}

function checkPhone(value) {
    if (value == null || value.trim() == "") return 1;
    else if (value.match(/(\d){8,}/g)) return 0;
    else return 2;
}

function checkNull(value) {
    return (value == null || value.trim() == "") ? 1 : 0;
}

function checkDOB(value) {
    if (value == null || value.trim() == "") return 1;
    else if (new Date(value) >= new Date()) return 2;
    else return 0;
}

function checkEmail(value) {
    if (value == null || value.trim() == "") return 0;
    else if (value.match(/[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)+/g)) {
        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                console.log("done");
            }
        };
        xhr.open("GET", `/CPMS/check?email=${value}`, false);
        xhr.send();
        console.log(xhr.responseText);
        if (xhr.responseText == "false") return 0;
        else return 3;
    } else return 2;
}

function checkAccount(value) {
    if (value == null || value.trim() == "") return 1;
    else if(value == "admin" || value == "root" || value == "guest"){
        return 3;
    } else if(value.indexOf(" ") != -1){
        return 4;
    }
    else {
        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                console.log("done");
            }
        };
        xhr.open("GET", `/CPMS/check?account=${value}`, false);
        xhr.send();
        if (xhr.responseText == "false") return 0;
        else return 2;
    }
}

function checkLicensePlate(value) {
    if (value == null || value.trim() == "") return 1;
    else {
        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                console.log("done");
            }
        };
        xhr.open("GET", `/CPMS/check?licensePlate=${value}`, false);
        xhr.send();
        if (xhr.responseText == "false") return 0;
        else return 2;
    }
}

function checkPassword(value) {
    if (value == null || value.trim() == "") return 1;
    else if (value.match(/(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,}/g)) return 0;
    else return 2;
}

function checkNum(value){
    if (value == null || value.trim() == "") return 1;
    else {
        if (value >= 0) return 0;
        else return 2;
    }
}