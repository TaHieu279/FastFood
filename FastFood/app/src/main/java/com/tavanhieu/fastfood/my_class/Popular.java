package com.tavanhieu.fastfood.my_class;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.RenameTable;

import java.io.Serializable;

public class Popular implements Serializable {
    private String tile, description;
    private int img;
    private float price;

    public Popular(String tile, String description, int img, float price) {
        this.tile = tile;
        this.description = description;
        this.img = img;
        this.price = price;
    }

    public String getTile() {
        return tile;
    }

    public void setTile(String tile) {
        this.tile = tile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
