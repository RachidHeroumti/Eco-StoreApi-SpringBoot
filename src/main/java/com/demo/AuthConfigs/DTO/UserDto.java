package com.demo.AuthConfigs.DTO;



public class UserDto {
    private int id;

    private  String email ;
    private  String password ;
    private  String name ;
    private  String role ;
    private  boolean enabled ;

    public UserDto() {
    }

    public UserDto(String name, String email, String password, String role) {
        this.password = password;
        this.email = email;
        this.name = name;
        this.role = role;
    }

    public UserDto(String email, String password) {
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
