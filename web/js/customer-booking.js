document.getElementById("customer-booking-btn-search").onclick = submitFormSearch;
function submitFormSearch() {
    var time = document.getElementById("customer-booking-input-time").value;
    var date = document.getElementById("customer-booking-input-date").value;
    var numOfPlayer = document.getElementById("customer-booking-input-num-player").value;

    var regex = /^\d{2}\/\d{2}\/\d{4}$/;
    if (! regex.test(date)) {
        alert("Ngày sai định dạng");
        return;
    }
    var d = Number(date.toString().substring(0, 2));
    var m = Number(date.toString().substring(3, 5));
    var y = Number(date.toString().substring(6, 10));
    console.log(d);
    console.log(m);
    console.log(y);
    if (m < 1 || m > 12) {
        alert("Ngày không hợp lệ");
        return;
    }
    if (d < 1 || d > 31) {
        alert("Ngày không hợp lệ");
        return;
    }
    if (m == 4 || m == 6 || m == 9 || m == 11) {
        if (d > 30) {
            alert("Ngày không hợp lệ");
            return;
        }
    }
    if (m == 2) {
        if (y % 4 == 0) {
            if (d > 29) {
                alert("Ngày không hợp lệ");
                return;
            }
        }
        else {
            if (d > 28) {
                alert("Ngày không hợp lệ");
                return;
            }
        }
    }

    var today = new Date();
    console.log(date.toString().split("/").reverse().join("/"));
    var dt = new Date(date.toString().split("/").reverse().join("/"));
    console.log(dt);
    if (dt < today) {
        alert("Ngày phải từ hôm nay chứ");
        return;
    }

    document.getElementById("customer-booking-form-search").submit();
}

function customerSubmitBooking(formId) {
    document.getElementById(formId).submit();
}