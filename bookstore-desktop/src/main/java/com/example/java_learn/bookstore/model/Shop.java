package com.example.java_learn.bookstore.model;

import bookstore.model.exceptions.InvalidPriceException;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Shop {
    protected ArrayList<ItemForSale> itemsForSale;

    public Shop(ArrayList<ItemForSale> itemsForSale) {
        this.itemsForSale = itemsForSale;
    }

    public Shop(ArrayList<? extends ItemForSale> itemsForSale1, ArrayList<? extends ItemForSale> itemsForSale2) {
        this(new ArrayList<ItemForSale>());

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            for (ItemForSale item : itemsForSale1)
                addNewItemForSale(item, br);
            for (ItemForSale item : itemsForSale2)
                addNewItemForSale(item, br);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addNewItemForSale(ItemForSale item, BufferedReader br) throws IOException {
        System.out.println("Введите цену для " + item.getDescription() + ":");

        do {
            String lineFromUser = br.readLine();

            try {
                int price = Integer.parseInt(lineFromUser);

                if (price <= 0)
                    throw new InvalidPriceException();

                item.setPrice(price);
                this.itemsForSale.add(item);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели не целое число! Повторите попытку:");
            } catch (InvalidPriceException e) {
                System.out.println("Вы ввели не положитльное целое число! Повторите попытку:");
            }
        } while (true);

    }

    public void showItems() {
        for (ItemForSale item : itemsForSale) {
            System.out.println(item.getDescription() + " - " + item.getPrice() + " rubles");
        }
    }

    public void showItems(String fileName) {
        try (FileWriter outputFile = new FileWriter(fileName)) {
            for (ItemForSale item : itemsForSale) {
                outputFile.append(item.getDescription() + " - " + item.getPrice() + " rubles\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ItemForSale> getItemsForSale() {
        return itemsForSale;
    }

    public void setItemsForSale(ArrayList<ItemForSale> itemsForSale) {
        this.itemsForSale = itemsForSale;
    }
}
