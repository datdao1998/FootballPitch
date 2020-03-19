package api.model.entity;

import lombok.Data;

@Data
public class BookingBill {
    private Integer id;

    private String timeIn,timeOut,status;

    private Pitch pitch;

    private Account accountStaff, accountCustomer;
}
