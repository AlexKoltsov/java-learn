package com.example.java_learn.bookstore.model;

import bookstore.model.exceptions.InvalidPriceException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

abstract public class ItemForSale {
    protected StringProperty name;
    protected IntegerProperty creationYear;
    protected IntegerProperty price;

    public ItemForSale() throws InvalidPriceException {
        this("", 0, 0);
    }

    public ItemForSale(String name, int creationYear, int price) throws InvalidPriceException {
        this.name = new SimpleStringProperty(name);
        this.creationYear = new SimpleIntegerProperty(creationYear);

        if (price < 0)
            throw new InvalidPriceException("Стоимость объекта должна быть не отрицательной. " +
                                            "Переданное значение: " + price);
        this.price = new SimpleIntegerProperty(price);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getCreationYear() {
        return creationYear.get();
    }

    public void setCreationYear(int creationYear) {
        this.creationYear.set(creationYear);
    }

    public int getPrice() {
        return price.get();
    }

    public void setPrice(int price) throws InvalidPriceException {
        if (price < 0)
            throw new InvalidPriceException("Стоимость объекта должна быть не отрицательной. " +
                    "Переданное значение: " + price);

        this.price.set(price);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public IntegerProperty creationYearProperty() {
        return creationYear;
    }

    public IntegerProperty priceProperty() {
        return price;
    }

    abstract protected String getDescription();
}
