package api.service.impl;

import api.model.dao.AccountDAO;
import api.model.dao.impl.AccountDAOImpl;
import api.model.entity.Account;
import api.service.AccountService;
import common.exception.FPException;

import java.util.List;
import java.util.Optional;

/**
 * Author : Nguyen Viet Anh
 * Email: anhnv@vnpay.vn
 */

public class AccountServiceImpl implements AccountService {

    private AccountDAO accountDAO  = new AccountDAOImpl();

    @Override
    public Optional<Account> create(Account account) throws FPException {
        Optional<Account> opt = accountDAO.findByUserName(account.getUserName());
        if (opt.isPresent()) throw new FPException.DuplicateEntityException();

        return accountDAO.save(account);
    }

    @Override
    public Optional<Account> findById(Integer id) throws FPException {
        Optional<Account> opt = accountDAO.findById(id);
        if (!opt.isPresent()) throw new FPException.NotFoundEntityException();

        return opt;
    }

    @Override
    public Optional<Account> update(Integer id, Account account) throws FPException {

        Optional<Account> opt = accountDAO.findById(id);
        if (!opt.isPresent()) throw new FPException.NotFoundEntityException();

        account.setId(id);
        return accountDAO.save(account);
    }

    @Override
    public List<Account> search(String userName, String name, String email, String role) {
        return accountDAO.search(userName, name, email, role);
    }

}
