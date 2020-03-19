package api.model.entity;

import lombok.Data;

/**
 * Author : Nguyen Viet Anh
 * Email: anhnv@vnpay.vn
 */

@Data
public class Account {

    private Integer id;

    private String userName;

    private String password;

    private String role;

    private String name;

    private String phone;

    private String email;

    private String code;
}
