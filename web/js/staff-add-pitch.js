function btnAddPitchClicked(){
    var name = document.getElementById("staff-add-pitch-input-name").value
    var description = document.getElementById("staff-add-pitch-input-description").value
    if(name == null || name ==""){
        alert("Sân phải có tên chứ");
        return;
    }
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
    document.getElementById("staff-add-pitch-form").submit();
}