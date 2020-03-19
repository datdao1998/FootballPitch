package api.model.dao.impl;

import api.model.dao.ServiceDAO;
import api.model.entity.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ServiceDAOImpl implements ServiceDAO {

    @Override
    public Optional<Service> findByName(String name) {
        Service service = new Service();
        String query = "SELECT * FROM service WHERE name = ?";
        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
            statement.setString(1,name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                service.setId(rs.getInt("id"));
                service.setName(rs.getString("name"));
                service.setType(rs.getString("type"));
                service.setUnit(rs.getString("unit"));
                service.setPrice(rs.getDouble("price"));
                service.setAvailable(rs.getInt("available"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       if(service.getId() == null) return Optional.empty();
       return Optional.of(service);
    }

    @Override
    public List<Service> search(String name, String type) {
        List<Service> services = new LinkedList<>();
        String query = "SELECT * FROM service WHERE (? is null OR name LIKE ?) " +
                "AND (? is null OR type LIKE ?)";
        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
            statement.setString(1,name);
            statement.setString(2, "%" + name + "%");
            statement.setString(3,type);
            statement.setString(4,"%" + type + "%");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Service service = new Service();
                service.setId(rs.getInt("id"));
                service.setName(rs.getString("name"));
                service.setType(rs.getString("type"));
                service.setUnit(rs.getString("unit"));
                service.setPrice(rs.getDouble("price"));
                service.setAvailable(rs.getInt("available"));
                services.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }

    @Override
    public List<Service> getAll() {
        List<Service> result = new ArrayList<>();

        String query = "SELECT * FROM service";

        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Service service = new Service();
                service.setId(rs.getInt("id"));
                service.setName(rs.getString("name"));
                service.setType(rs.getString("type"));
                service.setUnit(rs.getString("unit"));
                service.setPrice(rs.getDouble("price"));
                service.setAvailable(rs.getInt("available"));
                result.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Optional<Service> save(Service service) {
        Optional<Service> optional =  findById(service.getId());
        if(optional.isPresent()) {
            String query = " UPDATE service SET name = ?, type = ?, unit = ?, price = ?, available = ? WHERE id = ?";
            try {
                PreparedStatement statement = connectDB.getConnection().prepareStatement(query);

                if (service.getName() != null) statement.setString(1, service.getName());
                else statement.setString(1, optional.get().getName());

                if (service.getType() != null) statement.setString(2, service.getType());
                else statement.setString(2, optional.get().getType());

                if (service.getUnit() != null) statement.setString(3, service.getUnit());
                else statement.setString(3, optional.get().getUnit());

                if (service.getPrice() != null) statement.setDouble(4, service.getPrice());
                else statement.setDouble(4, optional.get().getPrice());

                if (service.getAvailable()!=null) statement.setInt(5,service.getAvailable());
                else statement.setInt(5,optional.get().getAvailable());

                statement.setInt(6, service.getId());
                statement.executeUpdate();
                return findById(service.getId());
            } catch (SQLException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        }

        String query = "INSERT INTO  service(name,type,unit,price,available)" +
                "VALUES (?,?,?,?,?)";
        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
            statement.setString(1, service.getName());
            statement.setString(2, service.getType());
            statement.setString(3, service.getUnit());
            statement.setDouble(4, service.getPrice());
            statement.setInt(5,service.getAvailable());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(getAll().get(getAll().size() - 1));
    }

    @Override
    public Optional<Service> findById(Integer id) {
        Service service = new Service();
        String query = "SELECT * FROM service WHERE id = " + id;
        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                service.setId(rs.getInt("id"));
                service.setName(rs.getString("name"));
                service.setType(rs.getString("type"));
                service.setUnit(rs.getString("unit"));
                service.setPrice(rs.getDouble("price"));
                service.setAvailable(rs.getInt("available"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(service.getId() == null) return Optional.empty();
        return Optional.of(service);
    }

//    public static void main(String[] args) {
//        ServiceDAO dao = new ServiceDAOImpl();
//        Service service = new Service(2,"cocacola","nuocuong","chai", 10000.00, 10);
//        System.out.println(dao.search("co","").get(0).getId());
//    }
}
