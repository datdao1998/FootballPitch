document.getElementById("staff-service-manage-sell-btn-search").onclick = staffSearchServiceForSell;

function staffSearchServiceForSell() {
    var serviceName = document.getElementById("staff-service-manage-sell-input-search").value;
    if (serviceName.includes("!") || serviceName.includes("/") || serviceName.includes("|") || serviceName.includes("#") ||
        serviceName.includes("$") || serviceName.includes("%") || serviceName.includes("^") || serviceName.includes(">") ||
        serviceName.includes("<") || serviceName.includes("*") || serviceName.includes("(") || serviceName.includes(")") ||
        serviceName.includes(",") || serviceName.includes("?") || serviceName.includes(";") || serviceName.includes(":") ||
        serviceName.includes("&") || serviceName.includes("-") || serviceName.includes("+") || serviceName.includes("=")) {
        alert("Tên không được chứa ký tự đặc biệt");
        return;
    }
    document.getElementById("staff-service-manage-sell-form-search").submit();
}

function staffSelectServiceForSell(id) {
    document.getElementById("staff-service-manage-sell-form-select-" + id).submit();
}

function staffChangeQuantity(id) {
    var s = id.toString().split("-");
    var serviceId = s[s.length - 1];
    var quantityMax = Number(document.getElementById("quantity-max-" + serviceId).innerHTML);
    var quantity = Number(document.getElementById(id).value);
    if (quantity < 0) {
        alert("Số lượng không thể âm");
        document.getElementById(id).value = 0;
    }
    if (quantity > quantityMax) {
        alert("Số lượng vượt quá giới hạn");
        document.getElementById(id).value = 0;
    }
    document.getElementById("staff-service-manage-sell-form-change-quantity-" + serviceId).submit();
}