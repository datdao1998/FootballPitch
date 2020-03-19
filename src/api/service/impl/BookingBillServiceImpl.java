package api.service.impl;

import api.model.dao.AccountDAO;
import api.model.dao.BookingBillDAO;
import api.model.dao.PitchDAO;
import api.model.dao.impl.AccountDAOImpl;
import api.model.dao.impl.BookingBillDAOImpl;
import api.model.dao.impl.PitchDAOImpl;
import api.model.entity.Account;
import api.model.entity.BookingBill;
import api.model.entity.Pitch;
import api.service.BookingBillService;
import common.constant.FPConstant;
import common.exception.FPException;
import common.util.FPUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingBillServiceImpl implements BookingBillService {

    private BookingBillDAO bookingBillDAO = new BookingBillDAOImpl();

    @Override
    public Optional<BookingBill> create(BookingBill bookingBill) throws FPException {
        String timeIn = bookingBill.getTimeIn();
        String timeOut = FPUtils.toTimeOut(timeIn);
        bookingBill.setTimeOut(timeOut);
        bookingBill.setStatus(FPConstant.BookingStatus.BOOKED);
        bookingBill.setId(null);
        return bookingBillDAO.save(bookingBill);
    }

    @Override
    public Optional<BookingBill> findById(Integer id) throws FPException {
        Optional<BookingBill> opt = bookingBillDAO.findById(id);
        if (!opt.isPresent()) throw new FPException.NotFoundEntityException();

        return opt;
    }

    @Override
    public Optional<BookingBill> update(Integer id, BookingBill bookingBill) throws FPException {
        Optional<BookingBill> opt = bookingBillDAO.findById(id);
        if (!opt.isPresent()) throw new FPException.NotFoundEntityException();

        if (bookingBill.getStatus()!=null && !bookingBill.getStatus().isEmpty() && !bookingBill.getStatus().equals(FPConstant.BookingStatus.CANCELLED) && !bookingBill.getStatus().equals(FPConstant.BookingStatus.CHECKED_IN) && !bookingBill.getStatus().equals(FPConstant.BookingStatus.CHECKED_OUT)){
            throw new FPException.StatusInvalidException();
        }
        bookingBill.setId(id);
        return bookingBillDAO.save(bookingBill);
    }

    @Override
    public List<BookingBill> search(String timeIn, String status, String pitchName, String accountStaffUsername, String accountCustomerUsername) {
        PitchDAO pitchDAO = new PitchDAOImpl();
        AccountDAO accountDAO = new AccountDAOImpl();
        String accountStaffId = null;
        String accountCustomerId = null;
        Optional<Account> optionalAccountStaff = accountDAO.findByUserName(accountStaffUsername);
        Optional<Account> optionalAccountCustomer = accountDAO.findByUserName(accountCustomerUsername);
        if(optionalAccountStaff.isPresent()){
            accountStaffId = optionalAccountStaff.get().getId().toString();
        }
        if(optionalAccountCustomer.isPresent()){
            accountCustomerId = optionalAccountCustomer.get().getId().toString();
        }

        ArrayList<BookingBill> bookingBills = new ArrayList<>();
        if(pitchName!=null && !pitchName.isEmpty()) {
            ArrayList<Pitch> pitches = (ArrayList<Pitch>) pitchDAO.search(pitchName, null, null);
            for (int i = 0; i < pitches.size(); i++) {
                ArrayList<BookingBill> element = (ArrayList<BookingBill>) bookingBillDAO.search(timeIn, status, pitches.get(i).getId() + "", accountStaffId, accountCustomerId);
                bookingBills.addAll(element);
            }
            return bookingBills;
        }
        else{
            return bookingBillDAO.search(timeIn,status,null,accountStaffId,accountCustomerId);
        }
    }


}
