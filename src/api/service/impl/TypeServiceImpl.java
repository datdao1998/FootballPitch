package api.service.impl;

import api.model.dao.TypeDAO;
import api.model.dao.impl.TypeDAOImpl;
import api.model.entity.Type;
import api.service.TypeService;
import common.exception.FPException;

import java.util.List;
import java.util.Optional;

public class TypeServiceImpl implements TypeService {

    private TypeDAO typeDAO = new TypeDAOImpl();

    @Override
    public Optional<Type> findById(Integer id) throws FPException {
        Optional<Type> opt = typeDAO.findById(id);
        if (!opt.isPresent()) throw new FPException.NotFoundEntityException();

        return opt;
    }

    @Override
    public List<Type> search(Integer numOfPlayer) {
        return typeDAO.search(numOfPlayer);
    }
}
