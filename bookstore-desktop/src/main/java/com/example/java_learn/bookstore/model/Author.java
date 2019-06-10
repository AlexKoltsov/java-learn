package com.example.java_learn.bookstore.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Author extends Person {
    protected ArrayList<Book> books;

    public Author() {
        super();
    }

    public Author(String firstName, String secondName,
                  String country, LocalDate birthday,
                  ArrayList<Book> books) {
        super(firstName, secondName, country, birthday);
        this.books = books;
    }

    public static ArrayList<Author> loadFromFile(String fileName) {
        ArrayList<Author> authors = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String authorStr = br.readLine();

            while (authorStr != null) {
                try {
                    Author author = createAuthorFromString(authorStr);
                    authors.add(author);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                authorStr = br.readLine();
            }

        } catch (IOException | DateTimeParseException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        return authors;
    }

    private static Author createAuthorFromString(String authorStr) throws ParseException, DateTimeParseException {
        StringTokenizer authorFields = new StringTokenizer(authorStr, "\t");

        ArrayList<String> fields = new ArrayList<>();
        while (authorFields.hasMoreTokens()) {
            fields.add(authorFields.nextToken());
        }

        if (fields.size() != 4)
            throw new ParseException("Должно быть 4 поля для инициализации автора. В строке было найлено только " + fields.size() +
                    ". Переданная строка: " + authorStr, fields.size());

        Author author = new Author(fields.get(0), fields.get(1), fields.get(2),
                LocalDate.parse(fields.get(3), DateTimeFormatter.ofPattern("d.M.yyyy")),
                new ArrayList<>());

        return author;
    }

    @Override
    public String toString() {
        return firstName + " " + secondName;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }
}
