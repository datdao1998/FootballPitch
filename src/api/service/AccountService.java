package api.service;

import api.model.entity.Account;
import common.exception.FPException;

import java.util.List;
import java.util.Optional;

/**
 * Author : Nguyen Viet Anh
 * Email: anhnv@vnpay.vn
 */

public interface AccountService {

    Optional<Account> create(Account account) throws FPException;

    Optional<Account> findById(Integer id) throws FPException;

    Optional<Account> update(Integer id, Account account) throws FPException;

    List<Account> search(String userName, String name, String email, String role);
}
