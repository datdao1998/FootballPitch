package api.service;

import api.model.entity.BookingBill;
import common.exception.FPException;

import java.util.List;
import java.util.Optional;

public interface BookingBillService {

    Optional<BookingBill> create(BookingBill bookingBill) throws FPException;

    Optional<BookingBill> findById(Integer id) throws FPException;

    Optional<BookingBill> update(Integer id, BookingBill bookingBill) throws FPException;

    List<BookingBill> search(String timeIn, String status,String pitchName, String accountStaffUsername, String accountCustomerUsername);

}
