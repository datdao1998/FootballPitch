package api.model.entity;


import lombok.Data;

import java.util.List;

@Data
public class ServiceBill {

    private Integer id;

    private Account account;

    private Double totalPayment;

    private String time;

    private List<UsedService> usedServiceList;

}
