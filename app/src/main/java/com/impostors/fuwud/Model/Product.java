package com.impostors.fuwud.Model;

public class Product {
    public String name;
    public double buyPrice;




    public Product() {
    }

    public Product(String name, double buyPrice) {
        this.name = name;
        this.buyPrice = buyPrice;

    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", buyPrice=" + buyPrice +
                ", product_id='"  + '\'' +
                '}';
    }



    public String getName() {
        return name;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }
}
