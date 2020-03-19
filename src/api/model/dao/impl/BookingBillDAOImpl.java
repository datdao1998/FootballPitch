package api.model.dao.impl;

import api.model.dao.AccountDAO;
import api.model.dao.BookingBillDAO;
import api.model.dao.PitchDAO;
import api.model.entity.Account;
import api.model.entity.BookingBill;

import java.awt.print.Book;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingBillDAOImpl implements BookingBillDAO {
    @Override
    public List<BookingBill> search(String timeIn, String status,String pitchId, String accountStaffId, String accountCustomerId) {
        List<BookingBill> bookingBills = new ArrayList<>();
        PitchDAO pitchDAO = new PitchDAOImpl();
        AccountDAO accountDAO = new AccountDAOImpl();
        String query = "SELECT * FROM booking_bill WHERE (? is null OR time_in LIKE ?) " +
                "AND (? is null OR status LIKE ?) " +
                "AND (? is null OR pitch_id = ?) " +
                "AND (? is null OR account_staff_id = ?)" +
                "AND (? is null OR account_customer_id = ?)";
        try {
            PreparedStatement statement=connectDB.getConnection().prepareStatement(query);
            statement.setString(1, timeIn);
            statement.setString(2, "%" + timeIn + "%");
            statement.setString(3, status);
            statement.setString(4, "%" + status + "%");
            statement.setString(5, pitchId);
            statement.setString(6, pitchId);
            statement.setString(7, accountStaffId);
            statement.setString(8, accountStaffId);
            statement.setString(9, accountCustomerId);
            statement.setString(10, accountCustomerId);
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                BookingBill bookingBill = new BookingBill();
                bookingBill.setId(rs.getInt("id"));
                bookingBill.setTimeIn(rs.getString("time_in"));
                bookingBill.setTimeOut(rs.getString("time_out"));
                bookingBill.setStatus(rs.getString("status"));
                bookingBill.setPitch(pitchDAO.findById(rs.getInt("pitch_id")).get());
                if  ((Integer) rs.getInt("account_staff_id") != 0) {
                    bookingBill.setAccountStaff(accountDAO.findById(rs.getInt("account_staff_id")).get());
                }
                bookingBill.setAccountCustomer(accountDAO.findById(rs.getInt("account_customer_id")).get());
                bookingBills.add(bookingBill);
            }
        }catch (Exception e ) {e.printStackTrace();}

        return bookingBills;
    }

    @Override
    public List<BookingBill> getAll() {
        ArrayList<BookingBill> bookingBills = new ArrayList<>();
        PitchDAO pitchDAO = new PitchDAOImpl();
        AccountDAO accountDAO = new AccountDAOImpl();
        String sql = "SELECT * FROM booking_bill";
        try {
            PreparedStatement preparedStatement = connectDB.getConnection().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery(sql);
            while (rs.next()){
                BookingBill bookingBill = new BookingBill();
                bookingBill.setId(rs.getInt("id"));
                bookingBill.setTimeIn(rs.getString("time_in"));
                bookingBill.setTimeOut(rs.getString("time_out"));
                bookingBill.setStatus(rs.getString("status"));
                bookingBill.setPitch(pitchDAO.findById(rs.getInt("pitch_id")).get());
                if ((Integer) rs.getInt("account_staff_id") != 0 ) {
                    bookingBill.setAccountStaff(accountDAO.findById(rs.getInt("account_staff_id")).get());
                }
                bookingBill.setAccountCustomer(accountDAO.findById(rs.getInt("account_customer_id")).get());
                bookingBills.add(bookingBill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingBills;

    }

    @Override
    public Optional<BookingBill> save(BookingBill bookingBill) {
        Optional<BookingBill> opt = findById(bookingBill.getId());
        if(opt.isPresent()){
            if (bookingBill.getAccountStaff() != null) {
                String sql = "UPDATE booking_bill SET status = ?, account_staff_id = ? WHERE id = ?";
                try {
                    PreparedStatement preparedStatement = connectDB.getConnection().prepareStatement(sql);
                    if (bookingBill.getStatus() != null && !bookingBill.getStatus().isEmpty()) {
                        preparedStatement.setString(1, bookingBill.getStatus());
                    } else {
                        preparedStatement.setString(1, opt.get().getStatus());
                    }
                    preparedStatement.setInt(2, bookingBill.getAccountStaff().getId());
                    preparedStatement.setInt(3, bookingBill.getId());
                    preparedStatement.executeUpdate();
                    return findById(bookingBill.getId());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else {
                String sql = "UPDATE booking_bill SET status = ? WHERE id = ?";
                try {
                    PreparedStatement preparedStatement = connectDB.getConnection().prepareStatement(sql);
                    if (bookingBill.getStatus() != null && !bookingBill.getStatus().isEmpty()) {
                        preparedStatement.setString(1, bookingBill.getStatus());
                    } else {
                        preparedStatement.setString(1, opt.get().getStatus());
                    }
                    preparedStatement.setInt(2, bookingBill.getId());
                    preparedStatement.executeUpdate();
                    return findById(bookingBill.getId());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        String sql = "INSERT INTO booking_bill(time_in,time_out,status,pitch_id,account_customer_id) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connectDB.getConnection().prepareStatement(sql);
            preparedStatement.setString(1,bookingBill.getTimeIn());
            preparedStatement.setString(2,bookingBill.getTimeOut());
            preparedStatement.setString(3,bookingBill.getStatus());
            preparedStatement.setInt(4,bookingBill.getPitch().getId());
            preparedStatement.setInt(5,bookingBill.getAccountCustomer().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(getAll().get(getAll().size() - 1));
    }

    @Override
    public Optional<BookingBill> findById(Integer id) {
        PitchDAO pitchDAO = new PitchDAOImpl();
        AccountDAO accountDAO = new AccountDAOImpl();
        BookingBill bookingBill = new BookingBill();
        String query = "SELECT * FROM booking_bill WHERE id = " + id;
        try {
            PreparedStatement statement=connectDB.getConnection().prepareStatement(query);
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                bookingBill.setId(rs.getInt("id"));
                bookingBill.setTimeIn(rs.getString("time_in"));
                bookingBill.setTimeOut(rs.getString("time_out"));
                bookingBill.setStatus(rs.getString("status"));
                bookingBill.setPitch(pitchDAO.findById(rs.getInt("pitch_id")).get());
                if (rs.getInt("account_staff_id") != 0) {
                    bookingBill.setAccountStaff(accountDAO.findById(rs.getInt("account_staff_id")).get());
                }
                bookingBill.setAccountCustomer(accountDAO.findById(rs.getInt("account_customer_id")).get());
            }
        }catch (Exception e ) {e.printStackTrace();}

        if (bookingBill.getId() == null) return Optional.empty();
        return Optional.of(bookingBill);
    }
}
