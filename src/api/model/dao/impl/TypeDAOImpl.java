package api.model.dao.impl;

import api.model.dao.TypeDAO;
import api.model.entity.Type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class TypeDAOImpl implements TypeDAO {

    @Override
    public List<Type> getAll() {
        List<Type> result = new ArrayList<>();

        String query = "SELECT * FROM type";

        try {
            PreparedStatement statement=connectDB.getConnection().prepareStatement(query);
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
               Type type = new Type();
               type.setId(rs.getInt("id"));
               type.setNumOfPlayer(rs.getInt("num_of_player"));
               type.setDescription(rs.getString("description"));
               type.setUnitCost(rs.getDouble("unit_cost"));
               result.add(type);
            }
        }catch (Exception e ) {e.printStackTrace();}

        return result;

    }

    @Override
    public Optional<Type> save(Type type) {
        return Optional.empty();
    }

    @Override
    public Optional<Type> findById(Integer id) {
        Type type = new Type();
        String query = "SELECT * FROM type WHERE id = " + id;
        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                type.setId(rs.getInt("id"));
                type.setNumOfPlayer(rs.getInt("num_of_player"));
                type.setDescription(rs.getString("description"));
                type.setUnitCost(rs.getDouble("unit_cost"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(type.getId() == null) return Optional.empty();
        return Optional.of(type);

    }

    @Override
    public List<Type> search(Integer numOfPlayer) {
        List<Type> types = new LinkedList<>();
        String query = "SELECT * FROM type WHERE " + numOfPlayer  + " is null OR num_of_player = " + numOfPlayer;
        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Type type = new Type();
                type.setId(rs.getInt("id"));
                type.setNumOfPlayer(rs.getInt("num_of_player"));
                type.setDescription(rs.getString("description"));
                type.setUnitCost(rs.getDouble("unit_cost"));
                types.add(type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return types;
    }

//    public static void main(String[] args) {
//        TypeDAO dao = new TypeDAOImpl();
//        System.out.println(dao.search(null));
//    }

}
