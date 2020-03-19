package api.service.impl;

import api.model.dao.ServiceBillDAO;
import api.model.dao.ServiceDAO;
import api.model.dao.UsedServiceDAO;
import api.model.dao.impl.ServiceBillDAOImpl;
import api.model.dao.impl.ServiceDAOImpl;
import api.model.dao.impl.UsedServiceDAOImpl;
import api.model.entity.Service;
import api.model.entity.ServiceBill;
import api.model.entity.UsedService;
import api.service.ServiceBillService;
import common.exception.FPException;
import common.util.FPUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServiceBillServiceImpl  implements ServiceBillService {

    private ServiceBillDAO serviceBillDAO = new ServiceBillDAOImpl();

    private UsedServiceDAO usedServiceDAO = new UsedServiceDAOImpl();

    private ServiceDAO serviceDAO = new ServiceDAOImpl();

    @Override
    public Optional<ServiceBill> create(ServiceBill serviceBill) throws FPException {
        Optional<ServiceBill> bill = serviceBillDAO.save(serviceBill);
        List<ServiceBill> serviceBills = serviceBillDAO.getAll();
        Integer id = serviceBills.get(serviceBills.size()-1).getId();
        List<UsedService> list = serviceBill.getUsedServiceList();
        for(UsedService usedService: list){
            usedService.setServiceBillId(id);
            usedServiceDAO.save(usedService);
            Service service = usedService.getService();
            service.setAvailable(service.getAvailable() - usedService.getQuantity());
            serviceDAO.save(service);
        }
        return bill;
    }

    @Override
    public Optional<ServiceBill> findById(Integer id) throws FPException {
        Optional<ServiceBill> opt = serviceBillDAO.findById(id);
        if (!opt.isPresent()) throw new FPException.NotFoundEntityException();

        return opt;
    }

    @Override
    public Optional<ServiceBill> update(Integer id, ServiceBill serviceBill) throws FPException {
        Optional<ServiceBill> opt = serviceBillDAO.findById(id);
        if (!opt.isPresent()) throw new FPException.NotFoundEntityException();

        serviceBill.setId(id);
        return serviceBillDAO.save(serviceBill);
    }

    @Override
    public List<ServiceBill> findByTime(String startTime, String endTime) throws FPException {
        if (!FPUtils.validateDateFormat(startTime)) throw new FPException.DateFormatException();
        if (!FPUtils.validateDateFormat(endTime)) throw new FPException.DateFormatException();
        List<ServiceBill> serviceBills = serviceBillDAO.search(null, null);
        serviceBills.removeIf(serviceBill -> {
            try {
                return !FPUtils.checkTimeIsIn(serviceBill.getTime(), startTime, endTime);
            } catch (ParseException e) {
                e.printStackTrace();
                return true;
            }
        });
        return serviceBills;
    }
}
