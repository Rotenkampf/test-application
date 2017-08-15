/*
 * Copyright (c) 2017.
 * Antony Mosin mossanva@gmail.com
 */

package com.test.antony.megakittest.data.network.typeAdapter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.test.antony.megakittest.data.db.model.AutoData;
import com.test.antony.megakittest.data.db.model.OwnerData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Antony Mosin
 */

public class OwnerTypeAdapter extends TypeAdapter<OwnerData> {

    public OwnerData read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return null;
        }
        OwnerData ownerData=new OwnerData();
        reader.beginObject();
        while (reader.hasNext()) {
            switch (reader.nextName()) {
                case "id":
                    ownerData.setId(reader.nextString());
                    break;
                case "name":
                    ownerData.setName(reader.nextString());
                    break;
                case "experience":
                    ownerData.setExperience(reader.nextInt());
                    break;
                case "autos":
                    RealmList<AutoData> autoDatas=new RealmList<>();
                    autoDatas.addAll(parseAutos(reader));
                    ownerData.setAutos(autoDatas);
            }
        }
        reader.endObject();
        return ownerData;
    }

    private List<AutoData> parseAutos(JsonReader reader) throws IOException{
        List<AutoData> result=new ArrayList<>();
        reader.beginArray();
        while (reader.hasNext()) {
            reader.beginObject();
            AutoData autoData=new AutoData();
            while (reader.hasNext()) {
                switch (reader.nextName()) {
                    case "id":
                        autoData.setId(reader.nextString());
                        break;
                    case "name":
                        autoData.setName(reader.nextString());
                        break;
                    case "number":
                        autoData.setNumber(reader.nextString());
                        break;
                }
            }
            reader.endObject();
            result.add(autoData);
        }
        return result;
    }

    public void write(JsonWriter writer, OwnerData value) throws IOException {
        if (value == null) {
            writer.nullValue();
            return;
        }
        writer.beginObject();
        for (AutoData autoData:value.getAutos()){
            autoData.setOwner(null);
        }
        writer.name("id").value(value.getId());
        writer.name("name").value(value.getName());
        writer.name("experience").value(value.getExperience());
        writer.name("autos").jsonValue(new Gson().toJson(value.getAutos(), new TypeToken<RealmList<AutoData>>(){}.getType()));
        writer.endObject();
    }

}
