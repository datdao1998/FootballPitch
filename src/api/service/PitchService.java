package api.service;

import api.model.entity.Pitch;
import common.exception.FPException;

import java.util.List;
import java.util.Optional;

public interface PitchService {

    Optional<Pitch> create(Pitch pitch) throws FPException;

    Optional<Pitch> findById(Integer id) throws FPException;

    Optional<Pitch> update(Integer id, Pitch pitch) throws FPException;

    List<Pitch> search(String name, String description, String numberOfPlayer);

    List<Pitch> searchAvailablePitch(String timeIn, String numberOfPlayer) throws FPException.DateFormatException;


}
