package com.demo.AuthConfigs.controllers;


import com.demo.AuthConfigs.DTO.UserDto;
import com.demo.AuthConfigs.models.User;
import com.demo.AuthConfigs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserService userService ;
    @PostMapping("auth/sign-in")
    public String signIn (@RequestBody UserDto user){
        return userService.RegisterUser(user);
    }

    @PostMapping("auth/log-in")
    public String Login (@RequestBody UserDto user){

        return userService.Login(user);
    }
    
    @GetMapping("auth/get-user")
    public Optional<User> getUser(@PathVariable int id){
        return userService.getUser(id);
        
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestParam("token") String token) {
        String result = userService.validateVerificationToken(token);
        if ("valid".equals(result)) {
            return ResponseEntity.ok("User verified successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }
}
