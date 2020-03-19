package api.model.entity;


import lombok.Data;

@Data
public class Type {

    private Integer id;

    private Integer numOfPlayer;

    private String description;

    private Double unitCost;

}
