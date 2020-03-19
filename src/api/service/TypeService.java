package api.service;

import api.model.entity.Type;
import common.exception.FPException;

import java.util.List;
import java.util.Optional;

public interface TypeService {

    Optional<Type> findById(Integer id) throws FPException;

    List<Type> search(Integer numOfPlayer);

}
