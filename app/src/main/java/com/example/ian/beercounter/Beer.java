package com.example.ian.beercounter;

import java.io.Serializable;

/**
 * Created by Ian on 2018-03-18.
 */

public class Beer implements Serializable {

    private String name;
    private int price, quantity;


    public Beer(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;

    }

    public Beer() {

    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
