package com.example.java_learn.bookstore;

import com.example.java_learn.bookstore.item.ItemForSale;

import java.util.ArrayList;
import java.util.List;

public class Shop {

    private List<ItemForSale> itemsForSale;

    public Shop(List<ItemForSale> itemsForSale) {
        this.itemsForSale = itemsForSale;
    }

    public void showItems() {
        itemsForSale.forEach(item -> System.out.println(item.getDescription() + " - " + item.getPrice() + " rubles"));
    }

    public List<ItemForSale> getItemsForSale() {
        return itemsForSale;
    }

    public void setItemsForSale(ArrayList<ItemForSale> itemsForSale) {
        this.itemsForSale = itemsForSale;
    }
}
