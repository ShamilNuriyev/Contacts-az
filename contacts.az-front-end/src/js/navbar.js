//navbar media
const menuBtn = document.querySelector(".menu-icon span");
const searchBtn = document.querySelector(".search-icon");
const cancelBtn = document.querySelector(".cancel-icon");
const items = document.querySelector(".nav-items");
const form = document.querySelector("form");
menuBtn.onclick = () => {
    items.classList.add("active");
    menuBtn.classList.add("hide");
    searchBtn.classList.add("hide");
    cancelBtn.classList.add("show");
}
cancelBtn.onclick = () => {
    items.classList.remove("active");
    menuBtn.classList.remove("hide");
    searchBtn.classList.remove("hide");
    cancelBtn.classList.remove("show");
    form.classList.remove("active");
    cancelBtn.style.color = "#fff";
}
searchBtn.onclick = () => {
    form.classList.add("active");
    searchBtn.classList.add("hide");
    cancelBtn.classList.add("show");
}

//btn
document.getElementById("logout_link").hidden = true;
document.getElementById("mycards").hidden = true;



function changeButton_old() {
    document.getElementById("bg_signin").hidden = false;
}

function changeButton_new() {
    document.getElementById("bg_signin").hidden = true;
}

function changeButton_old2() {
    document.getElementById("bg_reg").hidden = false;
}

function changeButton_new2() {
    document.getElementById("bg_reg").hidden = true;
}
function changeButton_old3() {
    document.getElementById("bg_cre").hidden = false;
}

function changeButton_new3() {
    document.getElementById("bg_cre").hidden = true;
}

