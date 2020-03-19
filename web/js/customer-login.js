document.getElementById("customer-login-btn-login").onclick = login;

function login() {
    var userName = document.getElementById("customer-login-input-user-name").value;
    var password = document.getElementById("customer-login-input-password").value;

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
    if (password =="") {
        alert("Không được để trống mật khẩu");
        return;
    }
    if (userName.toString().toLowerCase().substring(userName.toString().length - 7, userName.toString().length) == "or true") {
        alert("Tên đăng nhập không được chứa ký tự đặc biệt");
        return;
    }
    document.getElementById("customer-login-form").submit();
}