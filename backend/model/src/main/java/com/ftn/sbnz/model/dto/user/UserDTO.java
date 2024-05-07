package com.ftn.sbnz.model.dto.user;

import jakarta.validation.constraints.Pattern;

public class UserDTO {

    private Integer id;
    @Pattern(regexp = "^[a-zA-Z]*$", message = "The name must contain only letters")
    private String name;
    @Pattern(regexp = "^[a-zA-Z]*$", message = "The surname must contain only letters")
    private String surname;
    private String email;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).*$",
            message = "Password must contain at least 1 number and 1 letter")
    private String password;

    private String role;

    //region Constructors
    public UserDTO() {

    }

    public UserDTO(String name, String surname, String email, String role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
    }
    //endregion

    //region Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    //endregion

}
