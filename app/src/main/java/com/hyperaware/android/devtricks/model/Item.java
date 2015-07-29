package com.hyperaware.android.devtricks.model;

import java.io.Serializable;

public class Item implements Serializable {

    private String name;

    public Item(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

}
