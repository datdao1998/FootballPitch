document.getElementById("staff-service-manage-add-available-btn-search").onclick = staffSearchServiceForAddAvailable;

function staffSearchServiceForAddAvailable() {
    var serviceName = document.getElementById("staff-service-manage-add-available-input-search").value;
    if (serviceName.includes("!") || serviceName.includes("/") || serviceName.includes("|") || serviceName.includes("#") ||
        serviceName.includes("$") || serviceName.includes("%") || serviceName.includes("^") || serviceName.includes(">") ||
        serviceName.includes("<") || serviceName.includes("*") || serviceName.includes("(") || serviceName.includes(")") ||
        serviceName.includes(",") || serviceName.includes("?") || serviceName.includes(";") || serviceName.includes(":") ||
        serviceName.includes("&") || serviceName.includes("-") || serviceName.includes("+") || serviceName.includes("=")) {
        alert("Tên không được chứa ký tự đặc biệt");
        return;
    }
    document.getElementById("staff-service-manage-add-available-form-search").submit();
}

function staffSelectServiceForAddAvailable(id) {
    document.getElementById("staff-service-manage-add-available-form-select-" + id).submit();
}