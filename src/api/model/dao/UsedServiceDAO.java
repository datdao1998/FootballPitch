package api.model.dao;

import api.model.entity.UsedService;

import java.util.List;

public interface UsedServiceDAO extends BaseDAO<UsedService> {

    List<UsedService> search(String serviceBillId, String serviceId, String quantity);

}
