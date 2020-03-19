package api.model.dao;

import api.model.entity.Account;

import java.util.List;
import java.util.Optional;

/**
 * Author : Nguyen Viet Anh
 * Email: anhnv@vnpay.vn
 */

public interface AccountDAO extends BaseDAO<Account> {

    Optional<Account> findByUserName(String userName);

    List<Account> search(String userName, String name, String email, String role);
}
