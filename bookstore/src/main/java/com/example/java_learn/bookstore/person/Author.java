package com.example.java_learn.bookstore.person;

import com.example.java_learn.bookstore.item.Book;

import java.util.ArrayList;
import java.util.Date;

public class Author extends Person {

    private ArrayList<Book> books;

    public Author(String firstName, String secondName,
                  String country, Date birthday,
                  ArrayList<Book> books) {
        super(firstName, secondName, country, birthday);
        this.books = books;
    }

    public Author(String firstName, String secondName,
                  String country, Date birthday) {
        this(firstName, secondName, country, birthday, new ArrayList<>());
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public long writtenPages() {
        return this.books.stream()
                .mapToInt(Book::getNumberPages)
                .sum();
    }
}
