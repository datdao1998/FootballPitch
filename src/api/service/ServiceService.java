package api.service;

import api.model.entity.Service;
import common.exception.FPException;

import java.util.List;
import java.util.Optional;

public interface ServiceService {

    Optional<Service> create(Service service) throws FPException;

    Optional<Service> findById(Integer id) throws FPException;

    Optional<Service> update(Integer id, Service service) throws FPException;

    List<Service> search(String name, String type);

}
