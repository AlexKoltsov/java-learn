package com.example.java_learn.bookstore.item;

import com.example.java_learn.bookstore.person.Author;

import java.text.ParseException;

public class Book extends ItemForSale {

    private Author author;
    private int numberPages;

    public Book(String name, int creationYear, int price, Author author, int numberPages) {
        super(name, creationYear, price);
        this.author = author;
        this.numberPages = numberPages;
    }

    public Book(String name, int creationYear, int price, int numberPages) {
        this(name, creationYear, price, null, numberPages);
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

    public static Book createBookFromString(String bookStr) throws ParseException {
        String[] fields = bookStr.split("\t");

        if (fields.length != 6) {
            throw new ParseException("Должно быть 6 полей для инициализации книги. В строке было найлено только " + fields.length +
                    ". Переданная строка: " + bookStr, fields.length);
        }

        return new Book(fields[0], Integer.valueOf(fields[1]), Integer.valueOf(fields[5]), Integer.valueOf(fields[2]));
    }

}
