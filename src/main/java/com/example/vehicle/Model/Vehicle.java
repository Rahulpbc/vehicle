package com.example.vehicle.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//the overall Model of the application
@Entity
public class Vehicle {

    @Id
    public Integer id;
    public Integer year;
    public String make;
    public String model;

    //creating the getters and setters of the vehicle properties
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        model = model;
    }
}
