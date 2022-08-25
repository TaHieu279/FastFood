package com.tavanhieu.fastfood.my_class;

import java.io.Serializable;

public class Categories implements Serializable {
    private Integer img;
    private String title;

    public Categories(int img, String title) {
        this.img = img;
        this.title = title;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
