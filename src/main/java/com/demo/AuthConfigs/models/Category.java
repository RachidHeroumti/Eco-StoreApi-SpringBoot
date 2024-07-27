package com.demo.AuthConfigs.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "Categories",
  uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id ;
    private String name ;
    private String description ;
    //private Product products ;

}
