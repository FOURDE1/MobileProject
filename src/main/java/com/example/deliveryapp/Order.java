package com.example.deliveryapp;

public class Order {
    private int id;
    private String name;
    private double total;

    public Order(int id, String name, double total) {
        this.id = id;
        this.name = name;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getTotal() {
        return total;
    }
}