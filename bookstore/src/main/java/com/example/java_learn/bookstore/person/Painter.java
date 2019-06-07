package com.example.java_learn.bookstore.person;

import com.example.java_learn.bookstore.item.Artwork;

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
}
