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

/**
 * Created by Antony Mosin
 */

public class AutoTypeAdapter extends TypeAdapter<AutoData>{

    public AutoData read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return null;
        }
        AutoData autoData=new AutoData();
        reader.beginObject();
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
                case "owner":
                    autoData.setOwner(parseOwner(reader));
            }
        }
        reader.endObject();
        return autoData;
    }

    private OwnerData parseOwner(JsonReader reader) throws IOException{
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
            }
        }
        reader.endObject();
        return ownerData;
    }

    public void write(JsonWriter writer, AutoData value) throws IOException {
        if (value == null) {
            writer.nullValue();
            return;
        }
        writer.beginObject();
        value.getOwner().setAutos(null);
        writer.name("id").value(value.getId());
        writer.name("name").value(value.getName());
        writer.name("number").value(value.getNumber());
        writer.name("owner").jsonValue(new Gson().toJson(value.getOwner(), new TypeToken<OwnerData>(){}.getType()));
        writer.endObject();
    }

}
