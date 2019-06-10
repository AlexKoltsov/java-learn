package com.example.java_learn.bookstore;

import com.example.java_learn.bookstore.item.Artwork;
import com.example.java_learn.bookstore.item.Book;
import com.example.java_learn.bookstore.item.ItemForSale;
import com.example.java_learn.bookstore.person.Author;
import com.example.java_learn.bookstore.person.Painter;
import com.example.java_learn.bookstore.util.Helper;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        String defaultResPath = Main.class.getResource("/").getPath();

        List<Book> books = createBooks(defaultResPath);
        List<Artwork> pictures = createPictures(defaultResPath);
        List<ItemForSale> allItems = Stream.concat(books.stream(), pictures.stream())
                .collect(Collectors.toList());

        Shop shop = new Shop(allItems);
        shop.showItems();

        List<Author> authors = books.stream()
                .map(Book::getAuthor)
                .distinct()
                .collect(Collectors.toList());
        printInfoAboutNumberOfPages(authors);
    }

    /**
     * Print indormation about:
     * 1) total number of pages in all the books of each author
     * 2) total number of pages in all the books
     *
     * @param authors - array of authors for getting books
     */
    private static void printInfoAboutNumberOfPages(List<Author> authors) {
        authors
                .forEach(author -> System.out.println("The total number of pages in all the books of "
                        + author.getSecondName() + " are: " + author.writtenPages()));

        int totalPages = authors.stream()
                .map(Author::getBooks)
                .flatMap(Collection::stream)
                .mapToInt(Book::getNumberPages)
                .sum();
        System.out.println("The total number of pages in all the books are: " + totalPages);
    }

    /**
     * Create authors -> create books -> add books ro authors
     *
     * @param defaultResPath
     * @return ArrayList of Book
     */
    private static List<Book> createBooks(String defaultResPath) throws IOException {
        List<Author> authors = Helper.loadFromFile(defaultResPath + "AUTHORS.txt", Author::createAuthorFromString);
        return Helper.loadFromFileWithDependency(defaultResPath + "BOOKS.txt", Book::createBookFromString, authors);
    }

    /**
     * Create painters -> create pictures -> add pictures to painters
     *
     * @param defaultResPath
     * @return ArrayList of pictures
     */
    private static List<Artwork> createPictures(String defaultResPath) throws IOException {
        List<Painter> painters = Helper.loadFromFile(defaultResPath + "PAINTERS.txt", Painter::createPainterFromString);
        return Helper.loadFromFileWithDependency(defaultResPath + "PICTURES.txt", Artwork::createPictureFromString, painters);
    }
}
