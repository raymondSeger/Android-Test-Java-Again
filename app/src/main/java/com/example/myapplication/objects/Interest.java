package com.example.myapplication.objects;

public class Interest {
    private int id;
    private int imageDrawableId;
    private String name;

    public Interest(int id, int imageDrawableId, String name) {
        this.id                 = id;
        this.imageDrawableId    = imageDrawableId;
        this.name               = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageDrawableId() {
        return imageDrawableId;
    }

    public void setImageDrawableId(int imageDrawableId) {
        this.imageDrawableId = imageDrawableId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
