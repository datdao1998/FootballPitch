package api.service.impl;

import api.model.dao.UsedServiceDAO;
import api.model.dao.impl.UsedServiceDAOImpl;
import api.model.entity.UsedService;
import api.service.UsedServiceService;
import common.exception.FPException;
import common.util.FPUtils;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public class UsedServiceServiceImpl implements UsedServiceService {

    private UsedServiceDAO usedServiceDAO = new UsedServiceDAOImpl();

    @Override
    public List<UsedService> findByServiceBillId(Integer serviceBillId) {
        return usedServiceDAO.search(String.valueOf(serviceBillId),null,null);
    }

    @Override
    public List<UsedService> findByServiceId(Integer serviceId) {
        return usedServiceDAO.search(null,String.valueOf(serviceId),null);
    }

    @Override
    public List<UsedService> findByTime(String startTime, String endTime) throws FPException {
        if (!FPUtils.validateDateFormat(startTime)) throw new FPException.DateFormatException();
        if (!FPUtils.validateDateFormat(endTime)) throw new FPException.DateFormatException();
        List<UsedService> usedServices = usedServiceDAO.search(null, null, null);
        usedServices.removeIf(usedService -> {
            try {
                return !FPUtils.checkTimeIsIn(usedService.getTime(), startTime, endTime);
            } catch (ParseException e) {
                e.printStackTrace();
                return true;
            }
        });
        return usedServices;
    }

    @Override
    public Optional<UsedService> create(UsedService usedService) throws FPException {
        Optional<UsedService> opt = usedServiceDAO.findById(usedService.getId());
        if (opt.isPresent()) throw new FPException.DuplicateEntityException();

        return usedServiceDAO.save(usedService);
    }

    @Override
    public Optional<UsedService> findById(Integer id) throws FPException {
        Optional<UsedService> opt = usedServiceDAO.findById(id);
        if (!opt.isPresent()) throw new FPException.NotFoundEntityException();

        return opt;
    }
//
//    public static void main(String[] args) {
//        UsedServiceService service = new UsedServiceServiceImpl();
//        System.out.println(service.findByTime("26/11/2019", "29/11/2019"));
//    }
}
