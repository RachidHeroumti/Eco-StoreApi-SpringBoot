package com.demo.AuthConfigs.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "Categories",
  uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id ;
    private String name ;
    private String description ;

    public Category(String name, String description) {
    }
    //private Product products ;

}
