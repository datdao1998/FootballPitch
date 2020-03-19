document.getElementById("staff-service-manage-detail-btn-add-available").onclick = staffAddServiceForAddAvailable;

function staffAddServiceForAddAvailable() {
    document.getElementById("staff-service-manage-detail-form").submit();
}

function staffChangeQuantity() {
    var quantity = Number(document.getElementById("staff-service-manage-add-available-input-add").value);
    var available = Number(document.getElementById("staff-service-manage-detail-service-available").value);
    if (quantity <= available) {
        alert("Số lượng mới phải lớn hơn số lượng ban đầu.");
        document.getElementById("staff-service-manage-add-available-input-add").value = available + 1;
        return;
    }
}
