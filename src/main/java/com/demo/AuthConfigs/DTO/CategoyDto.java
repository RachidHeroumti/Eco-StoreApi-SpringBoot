package com.demo.AuthConfigs.DTO;

public class CategoyDto {
    private Long  id ;
    private String name ;
    private String description ;

    public CategoyDto() {
    }

    public CategoyDto(String description, String name) {
        this.description = description;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
