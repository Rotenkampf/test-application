package com.test.antony.megakittest.utils.pojo;

/**
 * Created by admin on 12.08.17.
 */

public class AutoItem {

    private String id;
    private String name;
    private String owner;

    public AutoItem(String id, String name, String owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
