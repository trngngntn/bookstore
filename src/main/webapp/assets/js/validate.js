var old;

function duplicate(){
    old = buildParameter(document.getElementById("edit-form"), 2);
    console.log("DUPLICATED");
}

function checkString(value){
    console.log("checking");
    return (value == null || value.trim() == "") ? 1 : 0;
}

function checkPhone(value){
    if(value == null || value.trim() == "") return 1;
    else if(value.match(/(\d){8,}/g)) return 0;
    else return 2;
}

function checkNull(value){
    return (value == null || value.trim() == "") ? 1 : 0;
}

function checkDOB(value){
    if(value == null || value.trim() == "") return 1;
    else if(value >= new Date()) return 2;
    else return 0;
}
function checkEmail(value){
    if(value == null || value.trim() == "") return 1;
    else if(value.match(/[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)+/g)) return 0;
    else return 2;
}

function checkAccount(value){
    if(value == null || value.trim() == "") return 1;
    //else if(value.match(/\*/g)) return 0;
    else return 0;
}

function checkPassword(value){
    if(value == null || value.trim() == "") return 1;
    else if(value.match(/(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,}/g)) return 0;
    else return 2;
}