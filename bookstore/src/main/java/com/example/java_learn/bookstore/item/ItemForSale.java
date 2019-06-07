package com.example.java_learn.bookstore.item;

abstract public class ItemForSale {

    protected String name;
    protected int creationYear;
    protected int price;

    public ItemForSale(String name, int creationYear, int price) {
        this.name = name;
        this.creationYear = creationYear;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreationYear() {
        return creationYear;
    }

    public void setCreationYear(int creationYear) {
        this.creationYear = creationYear;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    abstract public String getDescription();
}
