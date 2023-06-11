package com.driver.model;

import javax.persistence.*;

@Table
@Entity
public class Cab{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cabId;
    private int perKmRate;
    private boolean available;
    @OneToOne
    @JoinColumn
    private Driver driver;

    public void setCabId(int cabId) {
        this.cabId = cabId;
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

    public int getCabId() {
        return cabId;
    }

    public int getPerKmRate() {
        return perKmRate;
    }

    public boolean isAvailable() {
        return available;
    }

    public Driver getDriver() {
        return driver;
    }
}