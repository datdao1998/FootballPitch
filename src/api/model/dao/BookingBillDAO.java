package api.model.dao;

import api.model.entity.BookingBill;

import java.util.List;

public interface BookingBillDAO extends BaseDAO<BookingBill> {

    List<BookingBill> search(String timeIn, String status,String pitchId, String accountStaffId, String accountCustomerId);

}
