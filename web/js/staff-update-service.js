function btnUpdateServiceSearchClicked() {
    var name = document.getElementById("staff-update-service-input-name").value
    var type = document.getElementById("staff-update-service-input-type").value
    if (name.includes("!") || name.includes("/") || name.includes("|") || name.includes("#") ||
        name.includes("$") || name.includes("%") || name.includes("^") || name.includes(">") ||
        name.includes("<") || name.includes("*") || name.includes("(") || name.includes(")") ||
        name.includes(",") || name.includes("?") || name.includes(";") || name.includes(":") ||
        name.includes("&") || name.includes("-") || name.includes("+") || name.includes("=")||
        name.includes(" ")){
        alert("Tên sân không được chứa ký tự đặc biệt");
        return;
    }
    if (type.includes("!") || type.includes("/") || type.includes("|") || type.includes("#") ||
        type.includes("$") || type.includes("%") || type.includes("^") || type.includes(">") ||
        type.includes("<") || type.includes("*") || type.includes("(") || type.includes(")") ||
        type.includes(",") || type.includes("?") || type.includes(";") || type.includes(":") ||
        type.includes("&") || type.includes("-") || type.includes("+") || type.includes("=")){
        alert("kiểu không được chứa ký tự đặc biệt");
        return;
    }
    document.getElementById("staff-update-service-form").submit();
}