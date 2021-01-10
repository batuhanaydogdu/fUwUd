package com.impostors.fuwud.Model;

import java.util.HashMap;
import java.util.List;

public class Restaurant {
    private String restaurantName,password,email,phoneNumber;
    private String restaurant_id;
    private Double longitude,latitude;
    private String role;
    private HashMap<String,Product> products;
    private String cuisine;

    public Restaurant() {
        this.role = "restaurant";
    }

    public Restaurant(String restaurantName,String email, String phoneNumber, String restaurant_id, Double longitude, Double latitude, String cuisine) {
        this.restaurantName=restaurantName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.restaurant_id = restaurant_id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.cuisine = cuisine;
        this.role = "restaurant";
    }

    public void setProducts(HashMap<String, Product> products) {
        this.products = products;
    }

    public HashMap<String, Product> getProducts() {
        return products;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantName='" + restaurantName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", restaurant_id='" + restaurant_id + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", role='" + role + '\'' +
                '}';
    }
}
