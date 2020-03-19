package api.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Service {

    private Integer id;

    private String name;

    private String type;

    private String unit;

    private  Double price;

    private Integer available;
}
