package api.model.dao.impl;

import api.model.dao.ServiceDAO;
import api.model.dao.UsedServiceDAO;
import api.model.entity.UsedService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UsedServiceDAOImpl implements UsedServiceDAO {
    @Override
    public List<UsedService> search(String serviceBillId, String serviceId, String quantity) {
        List<UsedService> usedServices = new ArrayList<>();
        ServiceDAO serviceDAO = new ServiceDAOImpl();
        String query = "SELECT * FROM used_service WHERE (? is null OR service_bill_id = ?) " +
                "AND (? is null OR service_id = ?) " +
                "AND (? is null OR quantity = ?) ";
        try {
            PreparedStatement statement=connectDB.getConnection().prepareStatement(query);
            statement.setString(1,serviceBillId);
            statement.setString(2,serviceBillId);
            statement.setString(3,serviceId);
            statement.setString(4,serviceId);
            statement.setString(5,quantity);
            statement.setString(6,quantity);

            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                UsedService usedService = new UsedService();
                usedService.setId(rs.getInt("id"));
                usedService.setServiceBillId(rs.getInt("service_bill_id"));
                usedService.setService(serviceDAO.findById(rs.getInt("service_id")).get());
                usedService.setQuantity(rs.getInt("quantity"));
                usedService.setTime(rs.getString("time"));
                usedServices.add(usedService);
            }
        }catch (Exception e ) {e.printStackTrace();}

        return usedServices;
    }

    @Override
    public List<UsedService> getAll() {
        List<UsedService> result = new ArrayList<>();

        String query = "SELECT * FROM used_service";
        ServiceDAO serviceDAO = new ServiceDAOImpl();
        try {
            PreparedStatement statement=connectDB.getConnection().prepareStatement(query);
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                UsedService usedService = new UsedService();
                usedService.setId(rs.getInt("id"));
                usedService.setServiceBillId(rs.getInt("service_bill_id"));
                usedService.setService(serviceDAO.findById(rs.getInt("service_id")).get());
                usedService.setQuantity(rs.getInt("quantity"));
                usedService.setTime(rs.getString("time"));
                result.add(usedService);
            }
        }catch (Exception e ) {e.printStackTrace();}

        return result;
    }

    @Override
    public Optional<UsedService> save(UsedService usedService) {
        Optional<UsedService> optional = findById(usedService.getId());
        if (optional.isPresent()) {
            String query = "UPDATE used_service SET service_bill_id = ?, service_id = ?, quantity = ? WHERE id = ?";
            try {
                PreparedStatement statement=connectDB.getConnection().prepareStatement(query);

                if (usedService.getServiceBillId() != null) statement.setInt(1, usedService.getServiceBillId());
                else statement.setInt(1, optional.get().getServiceBillId());

                if (usedService.getService() != null) statement.setInt(2, usedService.getService().getId());
                else statement.setInt(2, optional.get().getService().getId());

                if (usedService.getQuantity() != null) statement.setInt(3, usedService.getQuantity());
                else statement.setInt(3, optional.get().getQuantity());

                statement.setInt(4,usedService.getId());

                statement.executeUpdate();
                return findById(usedService.getId());
            } catch (Exception e ) {
                e.printStackTrace();
                return Optional.empty();
            }
        }

        String query = "INSERT INTO used_service (service_bill_id, service_id, quantity, time) " +
                "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement=connectDB.getConnection().prepareStatement(query);
            statement.setInt(1, usedService.getServiceBillId());
            statement.setInt(2, usedService.getService().getId());
            statement.setInt(3, usedService.getQuantity());
            statement.setString(4, usedService.getTime());
            statement.executeUpdate();
        } catch (Exception e ) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(getAll().get(getAll().size() - 1));
    }

    @Override
    public Optional<UsedService> findById(Integer id) {
        UsedService usedService = new UsedService();
        String query = "SELECT * FROM used_service WHERE id = " + id;
        try {
            PreparedStatement statement=connectDB.getConnection().prepareStatement(query);
            ResultSet rs=statement.executeQuery();
            ServiceDAO serviceDAO = new ServiceDAOImpl();
            while(rs.next()){
                usedService.setId(rs.getInt("id"));
                usedService.setService(serviceDAO.findById(rs.getInt("service_id")).get());
                usedService.setQuantity(rs.getInt("quantity"));
                usedService.setServiceBillId(rs.getInt("service_bill_id"));
                usedService.setTime(rs.getString("time"));
            }
        }catch (Exception e ) {
            e.printStackTrace();
        }

        if (usedService.getId() == null) return Optional.empty();
        return Optional.of(usedService);
    }
}
