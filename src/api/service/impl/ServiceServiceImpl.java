package api.service.impl;

import api.model.dao.ServiceDAO;
import api.model.dao.impl.ServiceDAOImpl;
import api.model.entity.Service;
import api.service.ServiceService;
import common.exception.FPException;

import java.util.List;
import java.util.Optional;

public class ServiceServiceImpl implements ServiceService {

    private ServiceDAO serviceDAO = new ServiceDAOImpl();

    @Override
    public Optional<Service> create(Service service) throws FPException {
        return serviceDAO.save(service);
    }

    @Override
    public Optional<Service> findById(Integer id) throws FPException {
        Optional<Service> opt = serviceDAO.findById(id);
        if (!opt.isPresent()) throw new FPException.NotFoundEntityException();

        return opt;
    }

    @Override
    public Optional<Service> update(Integer id, Service service) throws FPException {

        Optional<Service> opt = serviceDAO.findById(id);
        if (!opt.isPresent()) throw new FPException.NotFoundEntityException();

        service.setId(id);
        return serviceDAO.save(service);
    }

    @Override
    public List<Service> search(String name, String type) {
        List<Service> services = serviceDAO.search(name, type);
        services.removeIf(service -> {
            return (service.getAvailable() <= 0);
        });

        return services;
    }

}
