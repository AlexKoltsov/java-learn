package com.example.java_learn.bookstore.person;

import com.example.java_learn.bookstore.item.Artwork;
import com.example.java_learn.bookstore.util.Helper;

import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;

public class Painter extends Person {

    private String drawingStyle;
    private ArrayList<Artwork> artWorks;

    public Painter(String firstName, String secondName, String country, Date birthday, String drawingStyle, ArrayList<Artwork> artWorks) {
        super(firstName, secondName, country, birthday);
        this.drawingStyle = drawingStyle;
        this.artWorks = artWorks;
    }

    public Painter(String firstName, String secondName, String country, Date birthday, String drawingStyle) {
        this(firstName, secondName, country, birthday, drawingStyle, new ArrayList<>());
    }

    public String getDrawingStyle() {
        return drawingStyle;
    }

    public void setDrawingStyle(String drawingStyle) {
        this.drawingStyle = drawingStyle;
    }

    public ArrayList<Artwork> getArtWorks() {
        return artWorks;
    }

    public void setArtWorks(ArrayList<Artwork> artWorks) {
        this.artWorks = artWorks;
    }

    public static Painter createPainterFromString(String painterStr) throws ParseException, DateTimeParseException {
        String[] fields = painterStr.split("\t");

        if (fields.length != 5) {
            throw new ParseException("Должно быть 5 полей для инициализации художника. В строке было найлено только " + fields.length +
                    ". Переданная строка: " + painterStr, fields.length);
        }

        return new Painter(fields[0], fields[1], fields[2], Helper.strToDate(fields[3]), fields[4]);
    }

    public void addArtwork(Artwork artwork) {
        artWorks.add(artwork);
    }
}
