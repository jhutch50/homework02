/*
Team 11:
Homework 2
Names:
Joshua Hutcheson
Hailey Brown
Ormelia Robinson
 */

package com.example.homework02;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {
    private ArrayList<String> toppings = new ArrayList<>();
    private boolean delivery = false;
    private float pizzaPrice = 6.50f;
    private float toppingPrice = 1.50f;
    private float deliveryPrice = 4.00f;
    private int toppingQuantity;

    public Order() {
    }

    public Order(boolean delivery, ArrayList<String> toppings) {

        this.delivery = delivery;
        this.toppings = toppings;
        this.toppingQuantity = toppings.size();
    }

    public ArrayList<String> getToppings() {
        return toppings;
    }

    public void setToppings(ArrayList<String> toppings) {
        this.toppings = toppings;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public float getPizzaPrice() {
        return pizzaPrice;
    }

    public void setPizzaPrice(float pizzaPrice) {
        this.pizzaPrice = pizzaPrice;
    }

    public float getToppingPrice() {
        return toppingPrice;
    }

    public void setToppingPrice(float toppingPrice) {
        this.toppingPrice = toppingPrice;
    }

    public float getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(float deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public int getToppingQuantity() {
        return toppings.size();
    }

    @Override
    public String toString() {
        return "Order{" +
                "toppings=" + toppings +
                ", delivery=" + delivery +
                ", pizzaPrice=" + pizzaPrice +
                ", toppingPrice=" + toppingPrice +
                ", deliveryPrice=" + deliveryPrice +
                ", toppingQuantity=" + toppingQuantity +
                '}';
    }
}
