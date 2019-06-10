package com.example.java_learn.bookstore.item;

import com.example.java_learn.bookstore.exception.UnknownCreatorException;
import com.example.java_learn.bookstore.person.Author;

import java.text.ParseException;
import java.util.List;

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

    public static Book createBookFromString(String bookStr, List<Author> authors) throws ParseException, UnknownCreatorException {
        String[] fields = bookStr.split("\t");

        if (fields.length != 6) {
            throw new ParseException("Должно быть 6 полей для инициализации книги. В строке было найлено только " + fields.length +
                    ". Переданная строка: " + bookStr, fields.length);
        }

        Author author = authors.stream()
                .filter(a -> a.getFirstName().equals(fields[3]))
                .filter(a -> a.getSecondName().equals(fields[4]))
                .findFirst()
                .orElseThrow(() -> new UnknownCreatorException("Author " + fields[3] + " " + fields[4] + " not found"));

        Book book = new Book(fields[0], Integer.valueOf(fields[1]), Integer.valueOf(fields[5]), author, Integer.valueOf(fields[2]));
        author.addBook(book);
        return book;
    }

}
