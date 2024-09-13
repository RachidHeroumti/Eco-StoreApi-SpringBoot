package com.demo.AuthConfigs.controllers;

import com.demo.AuthConfigs.DTO.CategoyDto;
import com.demo.AuthConfigs.Responces.ResponceApi;
import com.demo.AuthConfigs.models.Category;
import com.demo.AuthConfigs.services.CategoryService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService ;

    @GetMapping("get-all")
    public ResponseEntity<Iterable<Category>> getCategories(){
        return categoryService.getCategories();
    }

    @PostMapping("add-gt")
    public ResponseEntity<ResponceApi> addCategory(@RequestBody CategoyDto categoy){
        return categoryService.addCategory(categoy);

    }

}
