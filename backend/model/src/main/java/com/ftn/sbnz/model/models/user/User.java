package com.ftn.sbnz.model.models.user;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.List;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Pattern(regexp = "^[a-zA-Z]*$", message = "The name must contain only letters")
    @Column
    private String name;
    @NotNull
    @Pattern(regexp = "^[a-zA-Z]*$", message = "The name must contain only letters")
    @Column
    private String surname;
    @NotNull
    @Column
    private String email;
    @NotNull

    @Column
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;


    //region Constructors

    public User() {
    }

    //endregion

    //region Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull @Pattern(regexp = "^[a-zA-Z]*$", message = "The name must contain only letters") String getName() {
        return name;
    }

    public void setName(@NotNull @Pattern(regexp = "^[a-zA-Z]*$", message = "The name must contain only letters") String name) {
        this.name = name;
    }

    public @NotNull @Pattern(regexp = "^[a-zA-Z]*$", message = "The name must contain only letters") String getSurname() {
        return surname;
    }

    public void setSurname(@NotNull @Pattern(regexp = "^[a-zA-Z]*$", message = "The name must contain only letters") String surname) {
        this.surname = surname;
    }

    public @NotNull String getEmail() {
        return email;
    }

    public void setEmail(@NotNull String email) {
        this.email = email;
    }

    public @NotNull String getPassword() {
        return password;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    //endregion

    //region Auth
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    //endregion

}
