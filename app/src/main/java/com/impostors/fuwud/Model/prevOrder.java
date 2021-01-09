package com.impostors.fuwud.Model;

import java.util.ArrayList;
import java.util.HashMap;

public class prevOrder  {

    private boolean isCompleted;
    private HashMap<String, Product> orders;

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

    public prevOrder(boolean isCompleted, HashMap<String, Product> orders) {
        this.isCompleted = isCompleted;
        this.orders = orders;
    }


}
