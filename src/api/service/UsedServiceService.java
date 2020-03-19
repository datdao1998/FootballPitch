package api.service;

import api.model.entity.UsedService;
import common.exception.FPException;

import java.util.List;
import java.util.Optional;

public interface UsedServiceService {

    List<UsedService> findByServiceBillId(Integer serviceBillId);

    List<UsedService> findByServiceId(Integer serviceId);

    List<UsedService> findByTime(String startTime, String endTime) throws FPException;

    Optional<UsedService> create(UsedService usedService) throws FPException;

    Optional<UsedService> findById(Integer id) throws FPException;

}
