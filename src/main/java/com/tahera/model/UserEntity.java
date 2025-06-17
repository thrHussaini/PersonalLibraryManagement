package com.tahera.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
    private String role;

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword(){
        return password;
    }
}
