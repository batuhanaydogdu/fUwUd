package com.impostors.fuwud.Model;

public class Restaurant {
    private String restaurantName,password,email,phoneNumber;
    private String restaurant_id;
    private Double longitude,latitude;




    public Restaurant() {
    }

    public Restaurant(String restaurantName,String email, String phoneNumber, String restaurant_id, Double longitude, Double latitude) {
        this.restaurantName=restaurantName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.restaurant_id = restaurant_id;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRestaurant_id() {
        return restaurant_id;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
