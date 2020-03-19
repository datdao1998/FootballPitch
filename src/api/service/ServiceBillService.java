package api.service;

import api.model.entity.ServiceBill;
import common.exception.FPException;

import java.util.List;
import java.util.Optional;

public interface ServiceBillService {

    Optional<ServiceBill> create(ServiceBill serviceBill) throws FPException;

    Optional<ServiceBill> findById(Integer id) throws FPException;

    Optional<ServiceBill> update(Integer id, ServiceBill serviceBill) throws FPException;

    List<ServiceBill> findByTime(String startTime, String endTime) throws FPException;

}
