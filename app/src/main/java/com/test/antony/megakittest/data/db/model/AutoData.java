package com.test.antony.megakittest.data.db.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Antony Mosin
 */

public class AutoData extends RealmObject implements Serializable{

    @PrimaryKey
    private String id;

    private String name;
    private String number;
    private OwnerData owner;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public OwnerData getOwner() {
        return owner;
    }

    public void setOwner(OwnerData owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return getName();
    }
}
