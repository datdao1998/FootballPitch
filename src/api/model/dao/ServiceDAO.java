package api.model.dao;

import api.model.entity.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceDAO extends BaseDAO<Service> {

    Optional<Service> findByName(String name);

    List<Service> search(String name, String type);

}
