package api.model.dao.impl;

import api.model.dao.AccountDAO;
import api.model.entity.Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Author : Nguyen Viet Anh
 * Email: anhnv@vnpay.vn
 */

public class AccountDAOImpl implements AccountDAO {

    @Override
    public List<Account> getAll() {
        List<Account> result = new ArrayList<>();

        String query = "SELECT * FROM account";

        try {
            PreparedStatement statement=connectDB.getConnection().prepareStatement(query);
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                Account account = new Account();
                account.setId(rs.getInt("id"));
                account.setUserName(rs.getString("user_name"));
                account.setPassword(rs.getString("password"));
                account.setCode(rs.getString("code"));
                account.setEmail(rs.getString("email"));
                account.setPhone(rs.getString("phone"));
                account.setName(rs.getString("name"));
                account.setRole(rs.getString("role"));
                result.add(account);
            }
        }catch (Exception e ) {e.printStackTrace();}

        return result;

    }

    @Override
    public Optional<Account> save(Account account) {

        Optional<Account> optional = findById(account.getId());
        if (optional.isPresent()) {
            String query = "UPDATE account SET password = ?, code = ?, email = ?, phone = ?, name = ? WHERE id = ?";
            try {
                PreparedStatement statement=connectDB.getConnection().prepareStatement(query);

                if (account.getPassword() != null) statement.setString(1, account.getPassword());
                else statement.setString(1, optional.get().getPassword());

                if (account.getCode() != null) statement.setString(2, account.getCode());
                else statement.setString(2, optional.get().getCode());

                if (account.getEmail() != null) statement.setString(3, account.getEmail());
                else statement.setString(3, optional.get().getEmail());

                if (account.getPhone() != null) statement.setString(4, account.getPhone());
                else statement.setString(4, optional.get().getPhone());

                if (account.getName() != null) statement.setString(5, account.getName());
                else statement.setString(5, optional.get().getName());

                statement.setInt(6, account.getId());
                statement.executeUpdate();
                return findById(account.getId());
            } catch (Exception e ) {
                e.printStackTrace();
                return Optional.empty();
            }
        }

        String query = "INSERT INTO account (user_name, password, code, email, phone, name, role) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement=connectDB.getConnection().prepareStatement(query);
            statement.setString(1, account.getUserName());
            statement.setString(2, account.getPassword());
            statement.setString(3, account.getCode());
            statement.setString(4, account.getEmail());
            statement.setString(5, account.getPhone());
            statement.setString(6, account.getName());
            statement.setString(7, account.getRole());
            statement.executeUpdate();
        } catch (Exception e ) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(getAll().get(getAll().size() - 1));
    }

    @Override
    public Optional<Account> findById(Integer id) {
        Account account = new Account();
        String query = "SELECT * FROM account WHERE id = " + id;
        try {
            PreparedStatement statement=connectDB.getConnection().prepareStatement(query);
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                account.setId(rs.getInt("id"));
                account.setUserName(rs.getString("user_name"));
                account.setPassword(rs.getString("password"));
                account.setCode(rs.getString("code"));
                account.setEmail(rs.getString("email"));
                account.setPhone(rs.getString("phone"));
                account.setName(rs.getString("name"));
                account.setRole(rs.getString("role"));
            }
        }catch (Exception e ) {e.printStackTrace();}

        if (account.getId() == null) return Optional.empty();
        return Optional.of(account);
    }

    @Override
    public Optional<Account> findByUserName(String userName) {
        Account account = new Account();
        String query = "SELECT * FROM account WHERE user_name = ?";
        try {
            PreparedStatement statement=connectDB.getConnection().prepareStatement(query);
            statement.setString(1, userName);
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                account.setId(rs.getInt("id"));
                account.setUserName(rs.getString("user_name"));
                account.setPassword(rs.getString("password"));
                account.setCode(rs.getString("code"));
                account.setEmail(rs.getString("email"));
                account.setPhone(rs.getString("phone"));
                account.setName(rs.getString("name"));
                account.setRole(rs.getString("role"));
            }
        }catch (Exception e ) {e.printStackTrace();}

        if (account.getId() == null) return Optional.empty();
        return Optional.of(account);
    }

    @Override
    public List<Account> search(String userName, String name, String email, String role) {
        List<Account> accounts = new LinkedList<>();
        String query = "SELECT * FROM account WHERE (? is null OR user_name = ?) " +
                "AND (? is null OR name LIKE ?) " +
                "AND (? is null OR email LIKE ?) " +
                "AND (? is null OR role = ?)";
        try {
            PreparedStatement statement=connectDB.getConnection().prepareStatement(query);
            statement.setString(1, userName);
            statement.setString(2, userName);
            statement.setString(3, name);
            statement.setString(4, "%" + name + "%");
            statement.setString(5, email);
            statement.setString(6, "%" + email + "%");
            statement.setString(7, role);
            statement.setString(8, role);

            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                Account account = new Account();
                account.setId(rs.getInt("id"));
                account.setUserName(rs.getString("user_name"));
                account.setPassword(rs.getString("password"));
                account.setCode(rs.getString("code"));
                account.setEmail(rs.getString("email"));
                account.setPhone(rs.getString("phone"));
                account.setName(rs.getString("name"));
                account.setRole(rs.getString("role"));
                accounts.add(account);
            }
        }catch (Exception e ) {e.printStackTrace();}

        return accounts;
    }

//    public static void main(String[] args) {
//        AccountDAO accountDAO = new AccountDAOImpl();
//        System.out.println(accountDAO.search("vietanh", null, null, null));
//    }
}
