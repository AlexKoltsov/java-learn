package com.example.java_learn.bookstore.person;

import com.example.java_learn.bookstore.item.Book;
import com.example.java_learn.bookstore.util.Helper;

import java.text.ParseException;
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

    public static Author createAuthorFromString(String authorStr) throws ParseException {
        String[] fields = authorStr.split("\t");

        if (fields.length != 4) {
            throw new ParseException("Должно быть 4 поля для инициализации автора. В строке было найлено только " + fields.length +
                    ". Переданная строка: " + authorStr, fields.length);
        }

        return new Author(fields[0], fields[1], fields[2], Helper.strToDate(fields[3]));
    }
}
