document.getElementById("btn-submit-staff-service-add-service").onclick = btnAddService;
function checkRegex(str) {
    if (str.includes("!") || str.includes("/") || str.includes("|") || str.includes("#") ||
        str.includes("$") || str.includes("%") || str.includes("^") || str.includes(">") ||
        str.includes("<") || str.includes("*") || str.includes("(") || str.includes(")") ||
        str.includes(",") || str.includes("?") || str.includes(";") || str.includes(":") ||
        str.includes("&") || str.includes("-") || str.includes("+") || str.includes("=") ) {

        return true;
        }
    return false;
}
function checkValue(str,mess) {
    if(str == ""){
        alert(mess+" không được trống");
        return true;
    }
    if(checkRegex(str)){
        alert(mess+" không được có kí tự đặc biệt");
        return true;
    }
    return false;
}
function btnAddService() {
    var str = document.getElementById("input-staff-service-add-service-name").value;
    if(checkValue(str,"Tên")){
        return;
    }

    str = document.getElementById("input-staff-service-add-service-type").value;
    if(checkValue(str,"Loại")){
        return;
    }
    str = document.getElementById("input-staff-service-add-service-unit").value;
    if(checkValue(str,"Đơn Vị")){
        return;
    }
    str = document.getElementById("input-staff-service-add-service-available").value;
    if(str ==""){
        alert("số lượng không được trống");
        return;
    }
    if(Number(str) <= 0){
        alert("số lượng Không Được âm hoặc bằng không");
        return;
    }
    str = document.getElementById("input-staff-service-add-service-price").value;
    if(str ==""){
        alert("giá không được trống");
        return;
    }
    if(Number(str) <= 0){
        alert("giá không được âm hoặc bằng không");
        return;
    }
    document.getElementById("form-staff-service-add-service").submit();


}