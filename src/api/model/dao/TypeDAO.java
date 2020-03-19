package api.model.dao;

import api.model.entity.Type;

import java.util.List;
import java.util.Optional;

public interface TypeDAO extends BaseDAO<Type> {

    List<Type> search(Integer numOfPlayer);

}
