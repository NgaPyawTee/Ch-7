package com.homework.ch7;

public class Drink {
    private String name;
    private String description;
    private int imageResourceID;

    public static final Drink[] drinks = {
            new Drink("Coffee","AAAAAAAAAAAAAAAAAAAAAAA",R.drawable.re),
            new Drink("Juice","BBBBBBBBBBBBBBBBBB",R.drawable.gow),
            new Drink("Soup","CCCCCCCCCCCCCCCCCCC",R.drawable.ac)
    };

    private Drink(String name, String description, int imageResourceID) {
        this.name = name;
        this.description = description;
        this.imageResourceID = imageResourceID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceID() {
        return imageResourceID;
    }

    public String toString() {
        return this.name;
    }
}
