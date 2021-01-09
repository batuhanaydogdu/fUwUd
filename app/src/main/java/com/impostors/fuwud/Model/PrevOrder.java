package com.impostors.fuwud.Model;


import java.util.Date;
import java.util.HashMap;

public class PrevOrder {

    private boolean isCompleted;
    private HashMap<String, Product> orders;
    private double Price;
    private String restaurantName;
    private Date date;


    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public HashMap<String, Product> getOrders() {
        return orders;
    }

    public void setOrders(HashMap<String, Product> orders) {
        this.orders = orders;
    }




}
