package com.driver.model;

import javax.persistence.*;

@Table(name="admin")
@Entity

public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;
    private String username;

    private String password;

    public Admin(int id, String userName, String password) {
        this.adminId = id;
        this.username = userName;
        this.password = password;
    }
    public Admin(){

    }

    public int getAdminId() {
        return adminId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}