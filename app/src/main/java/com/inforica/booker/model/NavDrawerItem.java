package com.inforica.booker.model;

/**
 */
public class NavDrawerItem {
    public String title;
    public String image;

    public NavDrawerItem(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public NavDrawerItem() {

    }

    public String getTitle () {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public String getImage () {
        return image;
    }

    public void setImage (String image) {
        this.image = image;
    }
}
