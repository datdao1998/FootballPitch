package api.model.entity;

import lombok.Data;

@Data
public class Pitch {

    private Integer id;

    private String name,description;

    private Type type;

}
