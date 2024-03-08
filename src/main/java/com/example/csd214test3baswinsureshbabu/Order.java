package com.example.csd214test3baswinsureshbabu;

public class Order {
    private int id;
    private String name;
    private int number;
    private String size;
    private int toppings;
    private int bill;

    public Order(int id, String name, int number, String size, int toppings, int bill) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.size = size;
        this.toppings = toppings;
        this.bill = bill;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }
    public int getBill() {
        return bill;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getToppings() {
        return toppings;
    }

    public void setToppings(int toppings) {
        this.toppings = toppings;
    }
    public void setBill(int bill) {
        this.bill = bill;
    }
}
