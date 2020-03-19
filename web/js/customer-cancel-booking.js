document.getElementById("customer-view-booking-btn-cancel").onclick = submitCancelBooking;
function submitCancelBooking() {
    var r  = confirm("Bạn có chắc chắn muốn hủy không ?");
    if (r == false) return;
    document.getElementById("customer-view-booking-form").submit();
}
