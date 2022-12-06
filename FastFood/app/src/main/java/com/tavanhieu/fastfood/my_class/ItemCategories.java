package com.tavanhieu.fastfood.my_class;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

//Class for item: pizza, pie, drink, bugger, hotdog
public class ItemCategories implements Serializable {
    private String name, description, image;
    private float price;

    public ItemCategories() {
    }

    public ItemCategories(String name, String description, String image, float price) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
