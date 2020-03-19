package api.model.dao.impl;

import api.model.dao.PitchDAO;
import api.model.dao.TypeDAO;
import api.model.entity.Pitch;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PitchDAOImpl implements PitchDAO {
    @Override
    public List<Pitch> search(String name, String description, String typeId) {
        List<Pitch> pitches = new ArrayList<>();
        TypeDAO typeDAO = new TypeDAOImpl();
        String query = "SELECT * FROM pitch WHERE (? is null OR name LIKE ?) " +
                "AND (? is null OR description LIKE ?) " +
                "AND (? is null OR type_id = ?)";
        try {
            PreparedStatement statement=connectDB.getConnection().prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, "%" + name + "%");
            statement.setString(3, description);
            statement.setString(4, "%" + description + "%");
            statement.setString(5, typeId);
            statement.setString(6, typeId);

            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                Pitch pitch = new Pitch();
                pitch.setId(rs.getInt("id"));
                pitch.setName(rs.getString("name"));
                pitch.setDescription(rs.getString("description"));
                pitch.setType(typeDAO.findById(rs.getInt("type_id")).get());
                pitches.add(pitch);
            }
        }catch (Exception e ) {e.printStackTrace();}

        return pitches;
    }

    @Override
    public List<Pitch> getAll() {
        String sql = "SELECT * FROM pitch";
        TypeDAO typeDAO = new TypeDAOImpl();
        ArrayList<Pitch> pitches = new ArrayList<>();
        try {
            PreparedStatement ps = connectDB.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Pitch pitch = new Pitch();
                pitch.setId(rs.getInt("id"));
                pitch.setName(rs.getString("name"));
                pitch.setDescription(rs.getString("description"));
                pitch.setType(typeDAO.findById(rs.getInt("type_id")).get());
                pitches.add(pitch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pitches;
    }

    @Override
    public Optional<Pitch> save(Pitch pitch) {
        if(pitch.getId()!=null) {
            Optional<Pitch> opt = findById(pitch.getId());
            if (opt.isPresent()) {
                String sql = "UPDATE pitch SET name = ?, description = ?, type_id = ? WHERE id = ?";
                try {
                    PreparedStatement ps = connectDB.getConnection().prepareStatement(sql);
                    if (pitch.getName() != null) {
                        ps.setString(1, pitch.getName());
                    } else {
                        ps.setString(1, opt.get().getName());
                    }
                    if (pitch.getDescription() != null) {
                        ps.setString(2, pitch.getDescription());
                    } else {
                        ps.setString(2, opt.get().getDescription());
                    }
                    if (pitch.getType() != null) {
                        ps.setInt(3, pitch.getType().getId());
                    } else {
                        ps.setInt(3, opt.get().getType().getId());
                    }
                    ps.setInt(4, pitch.getId());
                    ps.executeUpdate();
                    return findById(pitch.getId());
                } catch (SQLException e) {
                    e.printStackTrace();
                    return Optional.empty();
                }
            }
        }
        String sql = "INSERT INTO pitch(name, description, type_id) VALUE(?,?,?)";
        try {
            PreparedStatement ps = connectDB.getConnection().prepareStatement(sql);
            ps.setString(1,pitch.getName());
            ps.setString(2,pitch.getDescription());
            ps.setInt(3,pitch.getType().getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            Optional.empty();
        }
        return Optional.of(getAll().get(getAll().size() - 1));
    }

    @Override
    public Optional<Pitch> findById(Integer id) {
        TypeDAO typeDAO = new TypeDAOImpl();
        Pitch pitch = new Pitch();
        String sql = "SELECT * FROM pitch WHERE id = ?";
        try {
            PreparedStatement statement = connectDB.getConnection().prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                pitch.setId(resultSet.getInt("id"));
                pitch.setName(resultSet.getString("name"));
                pitch.setDescription(resultSet.getString("description"));
                pitch.setType(typeDAO.findById(resultSet.getInt("type_id")).get());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(pitch.getId()==null) return Optional.empty();
        return Optional.of(pitch);
    }
}
