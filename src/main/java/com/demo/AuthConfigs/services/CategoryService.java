package com.demo.AuthConfigs.services;


import com.demo.AuthConfigs.DTO.CategoyDto;
import com.demo.AuthConfigs.models.Category;
import com.demo.AuthConfigs.repositories.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    public ResponseEntity<Iterable<Category>> getCategories() {
        Iterable<Category> it = categoryRepo.findAll();
       return new ResponseEntity<>(it, HttpStatus.CREATED) ;

    }


    public ResponseEntity<Category> addCategory(CategoyDto categoy) {

        Category c=new Category(
                categoy.getName(),
                categoy.getDescription()
        );
                categoryRepo.save(c);
        return new ResponseEntity<>(c,HttpStatus.CREATED) ;
    }
}
