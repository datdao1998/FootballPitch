function updateButtonClicked() {
    var name = document.getElementById("staff-service-detail-input-name").value
    var type = document.getElementById("staff-service-detail-input-type").value
    var price = document.getElementById("staff-service-detail-input-price").value
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
    if(isNaN(price)){
        alert("giá phải là số chứ")
        return;
    }
    document.getElementById("staff-update-service-form").submit();
}