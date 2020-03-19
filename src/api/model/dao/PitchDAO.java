package api.model.dao;

import api.model.entity.Pitch;

import java.util.List;

public interface PitchDAO extends BaseDAO<Pitch> {

    List<Pitch> search(String name, String description, String type_id);

}
