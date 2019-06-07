package com.example.java_learn.bookstore;

import com.example.java_learn.bookstore.item.Artwork;
import com.example.java_learn.bookstore.item.Book;
import com.example.java_learn.bookstore.item.ItemForSale;
import com.example.java_learn.bookstore.person.Author;
import com.example.java_learn.bookstore.person.Painter;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws ParseException {

        List<ItemForSale> books = createBooks();
        List<ItemForSale> pictures = createPictures();
        List<ItemForSale> allItems = Stream.concat(books.stream(), pictures.stream())
                .collect(Collectors.toList());

        Shop shop = new Shop(allItems);
        shop.showItems();

        List<Author> authors = books.stream()
                .map(item -> (Book) item)
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
     * @return ArrayList of Book
     * @throws ParseException
     */
    private static List<ItemForSale> createBooks() throws ParseException {
        Book book;
        List<ItemForSale> books = new ArrayList<>();

        Author alexanderPushkin = new Author("Alexander", "Pushkin", "Russia", strToDate("26.05.1799"));
        Author oscarWilde = new Author("Oscar", "Wilde", "Ireland", strToDate("16.10.1854"));
        Author charlesDickens = new Author("Charles ", "Dickens", "England", strToDate("7.2.1812"));

        books.add(book = new Book("The Tale Of Tsar Saltan", 1832, 100, alexanderPushkin, 56));
        alexanderPushkin.getBooks().add(book);

        books.add(book = new Book("The Stationmaster", 1831, 150, alexanderPushkin, 32));
        alexanderPushkin.getBooks().add(book);


        books.add(book = new Book("The Picture of Dorian Gray", 1890, 250, oscarWilde, 320));
        oscarWilde.getBooks().add(book);

        books.add(book = new Book("De Profundis", 1905, 300, oscarWilde, 224));
        oscarWilde.getBooks().add(book);


        books.add(book = new Book("The Adventures of Oliver Twist", 1837, 185, charlesDickens, 288));
        charlesDickens.getBooks().add(book);

        books.add(book = new Book("The Personal History, Adventures, Experience and Observation of David " +
                "Copperfield the Younger of Blunderstone Rookery", 1850, 379, charlesDickens, 928));
        charlesDickens.getBooks().add(book);


        return books;
    }

    /**
     * Create painters -> create pictures -> add pictures to painters
     *
     * @return ArrayList of pictures
     * @throws ParseException
     */
    private static List<ItemForSale> createPictures() throws ParseException {
        Artwork picture;
        List<ItemForSale> pictures = new ArrayList<>();

        Painter victorVasnetsov = new Painter("Victor", "Vasnetsov", "Russia", strToDate("15.05.1848"), "realism");

        pictures.add(picture = new Artwork("Hero", 1920, 650, victorVasnetsov, "Realism"));
        victorVasnetsov.getArtWorks().add(picture);

        pictures.add(picture = new Artwork("Alyonushka", 1881, 489, victorVasnetsov, "Romanticism"));
        victorVasnetsov.getArtWorks().add(picture);

        return pictures;
    }

    private static Date strToDate(String date) throws ParseException {
        return new SimpleDateFormat("dd.MM.yyyy").parse(date);
    }
}
