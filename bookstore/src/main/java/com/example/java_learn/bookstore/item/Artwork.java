package com.example.java_learn.bookstore.item;

import com.example.java_learn.bookstore.exception.UnknownCreatorException;
import com.example.java_learn.bookstore.person.Painter;

import java.text.ParseException;
import java.util.List;

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

    public static Artwork createPictureFromString(String pictureStr, List<Painter> painters) throws ParseException, UnknownCreatorException {
        String[] fields = pictureStr.split("\t");

        if (fields.length != 6) {
            throw new ParseException("Должно быть 6 полей для инициализации книги. В строке было найлено только " + fields.length +
                    ". Переданная строка: " + pictureStr, fields.length);
        }

        Painter painter = painters.stream()
                .filter(a -> a.getFirstName().equals(fields[3]))
                .filter(a -> a.getSecondName().equals(fields[4]))
                .findFirst()
                .orElseThrow(() -> new UnknownCreatorException("Painter " + fields[3] + " " + fields[4] + " not found"));

        Artwork artwork = new Artwork(fields[0], Integer.valueOf(fields[1]), Integer.valueOf(fields[5]), painter, fields[2]);
        painter.addArtwork(artwork);
        return artwork;
    }
}
