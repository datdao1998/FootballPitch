package api.model.dao.impl;

import api.model.dao.AccountDAO;
import api.model.dao.ServiceBillDAO;
import api.model.dao.UsedServiceDAO;
import api.model.entity.ServiceBill;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ServiceBillDAOImpl implements ServiceBillDAO {

    private AccountDAO accountDAO = new AccountDAOImpl();

    private UsedServiceDAO usedServiceDAO = new UsedServiceDAOImpl();

    @Override
    public List<ServiceBill> getAll() {
        List<ServiceBill> result = new ArrayList<>();

        String query = "SELECT * FROM service_bill";

        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ServiceBill serviceBill = new ServiceBill();
                serviceBill.setId(rs.getInt("id"));
                serviceBill.setAccount(accountDAO.findById(rs.getInt("account_id")).get());
                serviceBill.setTotalPayment(rs.getDouble("total_payment"));
                serviceBill.setTime(rs.getString("time"));
                serviceBill.setUsedServiceList(usedServiceDAO.search(serviceBill.getId().toString(),null,null));
                result.add(serviceBill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Optional<ServiceBill> save(ServiceBill serviceBill) {
        Optional<ServiceBill> optional =  findById(serviceBill.getId());
        if(optional.isPresent()) {
            String query = " UPDATE service_bill SET account_id = ?, total_payment = ?, time = ? WHERE id = ?";
            try {
                PreparedStatement statement = connectDB.getConnection().prepareStatement(query);

                if(serviceBill.getAccount() != null) statement.setInt(1, serviceBill.getAccount().getId());
                else statement.setInt(1, optional.get().getAccount().getId());

                statement.setDouble(2, serviceBill.getTotalPayment());

                if(serviceBill.getTime() != null) statement.setString(3, serviceBill.getTime());
                else statement.setString(3, optional.get().getTime());

                statement.setInt(4, serviceBill.getId());
                statement.executeUpdate();
                return findById(serviceBill.getId());
            } catch (SQLException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        }

        String query = "INSERT INTO  service_bill(account_id,total_payment,time)" +
                "VALUES (?,?,?)";
        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
            statement.setInt(1, serviceBill.getAccount().getId());
            statement.setDouble(2, serviceBill.getTotalPayment());
            statement.setString(3, serviceBill.getTime());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(getAll().get(getAll().size() - 1));
    }

    @Override
    public Optional<ServiceBill> findById(Integer id) {
        ServiceBill serviceBill = new ServiceBill();
        String query = "SELECT * FROM service_bill WHERE id = " + id;
        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                ServiceBill serviceBill1 = new ServiceBill();
                serviceBill.setId(rs.getInt("id"));
                serviceBill.setAccount(accountDAO.findById(rs.getInt("account_id")).get());
                serviceBill.setTotalPayment(rs.getDouble("total_payment"));
                serviceBill.setTime(rs.getString("time"));
                serviceBill.setUsedServiceList(usedServiceDAO.search(id.toString(), null, null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(serviceBill.getId() == null) return Optional.empty();
        return Optional.of(serviceBill);
    }

    @Override
    public List<ServiceBill> search(String accountId, String time) {
        List<ServiceBill> serviceBills = new ArrayList<>();
        String query = "SELECT * FROM service_bill WHERE (? is null OR account_id = ?) " +
                "AND (? is null OR time LIKE ?) " ;
        try {
            PreparedStatement statement=connectDB.getConnection().prepareStatement(query);
            statement.setString(1, accountId);
            statement.setString(2, accountId);
            statement.setString(3, time);
            statement.setString(4, "%" + time + "%");
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                ServiceBill serviceBill = new ServiceBill();
                serviceBill.setId(rs.getInt("id"));
                serviceBill.setAccount(accountDAO.findById(rs.getInt("account_id")).get());
                serviceBill.setTotalPayment(rs.getDouble("total_payment"));
                serviceBill.setTime(rs.getString("time"));
                serviceBill.setUsedServiceList(usedServiceDAO.search(serviceBill.getId().toString(),null, null));
                serviceBills.add(serviceBill);
            }
        }catch (Exception e ) {e.printStackTrace();}

        return serviceBills;
    }

}
