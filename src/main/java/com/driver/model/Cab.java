package com.driver.model;

import javax.persistence.*;

@Table
@Entity
public class Cab{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private int perKmRate;
    private boolean available;
    @OneToOne
    @JoinColumn
    private Driver driver;

    public void setId(int cabId) {
        this.Id = cabId;
    }

    public void setPerKmRate(int perKmRate) {
        this.perKmRate = perKmRate;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public int getId() {
        return Id;
    }

    public int getPerKmRate() {
        return perKmRate;
    }

    public boolean getAvailable() {
        return available;
    }

    public Driver getDriver() {
        return driver;
    }
}