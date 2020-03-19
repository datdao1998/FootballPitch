document.getElementById("booked-service-btn-search").onclick = seacrhBookedService;
function checkDate(date) {
    var d = Number(date.toString().substring(0, 2));
    var m = Number(date.toString().substring(3, 5));
    var y = Number(date.toString().substring(6, 10));
    if (m < 1 || m > 12) {
        return true;
    }
    if (d < 1 || d > 31) {
        return true;
    }
    if (m == 4 || m == 6 || m == 9 || m == 11) {
        if (d > 30) {
            return true;
        }
    }
    if (m == 2) {
        if (( y % 400 == 0) || (y % 4 == 0 &&  y % 100 != 0)) {
            if (d > 29) {
                return true;
            }
        }
        else {
            if (d > 28) {
                return true;
            }
        }
    }
    document.getElementById("booked-service-form-search").submit();

}

function seacrhBookedService() {
    var dateStart = document.getElementById("booked-service-input-date").value;
    var dateEnd = document.getElementById("booked-service-end-input-date").value;
    if (dateStart =="") {
        alert("Không được để trống ngày bắt đầu ");
        return;
    }
    if (dateEnd =="") {
        alert("Không được để trống ngày kết thúc");
        return;
    }
    var regex = /^\d{2}\/\d{2}\/\d{4}$/;
    if (! regex.test(dateStart) || !regex.test(dateEnd)){
        alert("Ngày sai định dạng");
        return;
    }
    if(checkDate(dateStart)){
        alert("Ngày sai định dạng bắt đầu");
        return ;
    }
    if(checkDate(dateEnd)){
        alert("Ngày sai định dạng kết thúc");
        return ;
    }



}