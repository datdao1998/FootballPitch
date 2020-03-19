document.getElementById("btn-submit-sign-up").onclick = submitFormSignUp;

function submitFormSignUp() {
    var name = document.getElementById("input-sign-up-name").value;
    var email = document.getElementById("input-sign-up-email").value;
    var phone = document.getElementById("input-sign-up-phone").value;
    var userName = document.getElementById("input-sign-up-user-name").value;
    var password = document.getElementById("input-sign-up-password").value;
    var rePassword = document.getElementById("input-sign-up-re-password").value;

    if (name =="") {
        alert("Không được để trống tên");
        return;
    }
    if (email =="") {
        alert("Không được để trống email");
        return;
    }
    var regex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    if (! regex.test(email)) {
        alert("Email sai định dạng");
        return;
    }
    if (phone =="") {
        alert("Không được để trống số điện thoại");
        return;
    }
    if (phone.length != 10 || phone.charAt(0) != 0) {
        alert("Số điện thoại sai định dạng");
        return;
    }
    if (userName =="") {
        alert("Không được để trống tên đăng nhập");
        return;
    }
    if (userName.includes("!") || userName.includes("/") || userName.includes("|") || userName.includes("#") ||
        userName.includes("$") || userName.includes("%") || userName.includes("^") || userName.includes(">") ||
        userName.includes("<") || userName.includes("*") || userName.includes("(") || userName.includes(")") ||
        userName.includes(",") || userName.includes("?") || userName.includes(";") || userName.includes(":") ||
        userName.includes("&") || userName.includes("-") || userName.includes("+") || userName.includes("=") ||
        userName.includes(" ")) {
        alert("Tên đăng nhập không được chứa ký tự đặc biệt");
        return;
    }
    if (password.includes(" ")) {
        alert("Mật khẩu không được chứa khoảng trống")
        return;;
    }
    if (password =="") {
        alert("Không được để trống mật khẩu");
        return;
    }
    if (password.length < 8) {
        alert("Mật khẩu phải từ 8 ký tự trở lên");
        return;
    }
    if (! (password === rePassword)) {
        alert("2 mật khẩu không giống nhau");
        return;
    }

    if (document.getElementById("sign-up-user-name-existed").innerHTML == "User name is valid") {
        document.getElementById("form-sign-up").submit();
    }
    else {
        alert("Tên đăng nhập đã tồn tại");
    }
}

document.getElementById("btn-submit-sign-up").onmouseover = changeColorWhite;
document.getElementById("btn-submit-sign-up").onmouseout = changeColorDefault;

function changeColorWhite() {
    document.getElementById("btn-submit-sign-up").setAttribute("style", "background-color: white");
}
function changeColorDefault() {
    document.getElementById("btn-submit-sign-up").setAttribute("style", "background-color: rgb(253, 189, 0)");
}
document.getElementById("input-sign-up-user-name").onchange = checkUserNameExisted;
function checkUserNameExisted() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("sign-up-user-name-existed").innerHTML = this.responseText;
            if (document.getElementById("sign-up-user-name-existed").innerHTML != "User name is valid") {
                document.getElementById("sign-up-user-name-existed").setAttribute("style", "color: red");
            }
            else {
                document.getElementById("sign-up-user-name-existed").setAttribute("style", "color: green");
            }
        }
    };
    var userName = document.getElementById("input-sign-up-user-name").value;
    xhttp.open("POST", "accounts/check_user_name_existed", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("user_name="+userName);
}
