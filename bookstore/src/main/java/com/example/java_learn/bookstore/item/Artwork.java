package com.example.java_learn.bookstore.item;

import com.example.java_learn.bookstore.person.Painter;

import java.text.ParseException;

public class Artwork extends ItemForSale {

    private Painter author;
    private String drawingStyle;

    public Artwork(String name, int creationYear, int price, Painter author, String drawingStyle) {
        super(name, creationYear, price);
        this.author = author;
        this.drawingStyle = drawingStyle;
    }

    public Artwork(String name, int creationYear, int price, String drawingStyle) {
        this(name, creationYear, price, null, drawingStyle);
    }

    public Painter getAuthor() {
        return author;
    }

    public void setAuthor(Painter author) {
        this.author = author;
    }

    public String getDrawingStyle() {
        return drawingStyle;
    }

    public void setDrawingStyle(String drawingStyle) {
        this.drawingStyle = drawingStyle;
    }

    @Override
    public String getDescription() {
        return this.getClass().getSimpleName() + " " + name + ": written by " + author.getSecondName() +
                " in " + creationYear + " year, in " + drawingStyle + " style";
    }

    public static Artwork createPictureFromString(String pictureStr) throws ParseException {
        String[] fields = pictureStr.split("\t");

        if (fields.length != 6) {
            throw new ParseException("Должно быть 6 полей для инициализации книги. В строке было найлено только " + fields.length +
                    ". Переданная строка: " + pictureStr, fields.length);
        }

        return new Artwork(fields[0], Integer.valueOf(fields[1]), Integer.valueOf(fields[5]), fields[2]);
    }
}
