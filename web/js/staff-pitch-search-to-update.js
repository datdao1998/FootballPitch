function btnUpdatePitchSearchClicked() {
    var name = document.getElementById("staff-update-pitch-input-name").value
    var description = document.getElementById("staff-update-pitch-input-description").value
    if (name.includes("!") || name.includes("/") || name.includes("|") || name.includes("#") ||
        name.includes("$") || name.includes("%") || name.includes("^") || name.includes(">") ||
        name.includes("<") || name.includes("*") || name.includes("(") || name.includes(")") ||
        name.includes(",") || name.includes("?") || name.includes(";") || name.includes(":") ||
        name.includes("&") || name.includes("-") || name.includes("+") || name.includes("=")||
        name.includes(" ")){
        alert("Tên sân không được chứa ký tự đặc biệt");
        return;
    }
    if (description.includes("!") || description.includes("/") || description.includes("|") || description.includes("#") ||
        description.includes("$") || description.includes("%") || description.includes("^") || description.includes(">") ||
        description.includes("<") || description.includes("*") || description.includes("(") || description.includes(")") ||
        description.includes(",") || description.includes("?") || description.includes(";") || description.includes(":") ||
        description.includes("&") || description.includes("-") || description.includes("+") || description.includes("=")){
        alert("Mô tả không được chứa ký tự đặc biệt");
        return;
    }
    document.getElementById("staff-update-pitch-form").submit();
}