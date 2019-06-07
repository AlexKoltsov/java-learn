package com.example.java_learn.bookstore.item;

import com.example.java_learn.bookstore.person.Author;

public class Book extends ItemForSale {

    private Author author;
    private int numberPages;

    public Book(String name, int creationYear, int price, Author author, int numberPages) {
        super(name, creationYear, price);
        this.author = author;
        this.numberPages = numberPages;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getNumberPages() {
        return numberPages;
    }

    public void setNumberPages(int numberPages) {
        this.numberPages = numberPages;
    }

    @Override
    public String getDescription() {
        return this.getClass().getSimpleName() + " " + name + ": written by " +
                author.getSecondName() + " in " + creationYear + " year, " + numberPages + " pages";
    }
}
