package api.model.entity;

import lombok.Data;

@Data
public class UsedService {
    private Integer id;

    private Integer serviceBillId;

    private Service service;

    private Integer quantity;

    private String time;
}
