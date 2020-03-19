function checkDate(date) {

    var regex = /^\d{2}\/\d{2}\/\d{4}$/;
    if (!regex.test(date)) {
        return false;
    }
    var d = Number(date.toString().substring(0, 2));
    var m = Number(date.toString().substring(3, 5));
    var y = Number(date.toString().substring(6, 10));
    if (m < 1 || m > 12) {
        return false;
    }
    if (d < 1 || d > 31) {
        return false;
    }
    if (m == 4 || m == 6 || m == 9 || m == 11) {
        if (d > 30) {
            return false;
        }
    }
    if (m == 2) {
        if (y % 4 == 0) {
            if (d > 29) {
                return false;
            }
        } else {
            if (d > 28) {
                return false;
            }
        }
    }
    return true;
}
function btnPitchReportSearchClicked() {
    var dateOut = document.getElementById("staff-pitch-report-input-time-out").value;
    var dateIn = document.getElementById("staff-pitch-report-input-time-in").value;
    if(checkDate(dateOut) && checkDate(dateIn)){
        document.getElementById("staff-pitch-report-form").submit()
    }
    else{
        alert("Ngày sai định dạng");
        return;
    }
}