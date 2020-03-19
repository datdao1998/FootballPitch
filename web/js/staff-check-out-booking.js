document.getElementById("staff-check-out-booking-btn-check-out").onclick = submitCheckOutBooking;
function submitCheckOutBooking() {
    var r  = confirm("Bạn có chắc chắn muốn check out không ?");
    if (r == false) return;
    document.getElementById("staff-check-out-booking-form").submit();
}