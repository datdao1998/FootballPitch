package api.model.dao;

import api.model.connectdb.ConnectDB;

import java.util.List;
import java.util.Optional;

/**
 * Author : Nguyen Viet Anh
 * Email: anhnv@vnpay.vn
 */

public interface BaseDAO<R> {

    public static ConnectDB connectDB = ConnectDB.getInstance();

    List<R> getAll();

    Optional<R> save(R r);

    Optional<R> findById(Integer id);
}
