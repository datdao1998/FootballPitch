document.getElementById("staff-check-in-booking-btn-check-in").onclick = submitCheckInBooking;
function submitCheckInBooking() {
    var r  = confirm("Bạn có chắc chắn muốn check in không ?");
    if (r == false) return;
    document.getElementById("staff-check-in-booking-form").submit();
}