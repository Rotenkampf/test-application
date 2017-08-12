package com.test.antony.megakittest.data.db.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by admin on 12.08.17.
 */

public class OwnerData extends RealmObject {

    @PrimaryKey
    private String id;

    private String name;
    private int experience;
    private RealmList<AutoData> autos;

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

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public RealmList<AutoData> getAutos() {
        return autos;
    }

    public void setAutos(RealmList<AutoData> autos) {
        this.autos = autos;
    }
}
