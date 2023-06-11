package com.driver.model;

import javax.persistence.*;

@Table(name="admin")
@Entity

public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;
    private String userName;

    private String password;

    public Admin(int id, String userName, String password) {
        this.adminId = id;
        this.userName = userName;
        this.password = password;
    }
    public Admin(){

    }

    public int getAdminId() {
        return adminId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}