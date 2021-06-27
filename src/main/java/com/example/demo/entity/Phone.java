package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "phoneID")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int phoneID;
    private String brand;
    private String model;
    private float screenSize;
    private float internalMemory;
    private float price;

    public int getPhoneID() {
        return phoneID;
    }

    public void setPhoneID(int phoneID) {
        this.phoneID = phoneID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(float screenSize) {
        this.screenSize = screenSize;
    }

    public float getInternalMemory() {
        return internalMemory;
    }

    public void setInternalMemory(float internalMemory) {
        this.internalMemory = internalMemory;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Phone(int phoneID, String brand, String model, float screenSize, float internalMemory, float price) {
        this.phoneID = phoneID;
        this.brand = brand;
        this.model = model;
        this.screenSize = screenSize;
        this.internalMemory = internalMemory;
        this.price = price;
    }

    @OneToMany(targetEntity = Review.class, cascade = CascadeType.ALL)
    private List<Review> reviews;


    @OneToMany(mappedBy= "phone", cascade= CascadeType.ALL)
    private List<PhoneFeature> phoneFeatures;

    public void addReview(Review r) {
        reviews.add(r);
    }
}
