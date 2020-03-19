package api.model.dao;

import api.model.entity.ServiceBill;

import java.util.List;
import java.util.Optional;

public interface ServiceBillDAO extends BaseDAO<ServiceBill> {

    List<ServiceBill> search(String accountId, String time);

}
