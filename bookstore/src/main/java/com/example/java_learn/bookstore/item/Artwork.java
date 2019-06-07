package com.example.java_learn.bookstore.item;

import com.example.java_learn.bookstore.person.Painter;

public class Artwork extends ItemForSale {

    private Painter author;
    private String drawingStyle;

    public Artwork(String name, int creationYear, int price, Painter author, String drawingStyle) {
        super(name, creationYear, price);
        this.author = author;
        this.drawingStyle = drawingStyle;
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
}
