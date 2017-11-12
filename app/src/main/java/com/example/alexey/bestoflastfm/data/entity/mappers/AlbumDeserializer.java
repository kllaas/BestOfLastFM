package com.example.alexey.bestoflastfm.data.entity.mappers;


import com.example.alexey.bestoflastfm.data.entity.remote.AlbumRemote;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by alexey
 */

public class AlbumDeserializer implements JsonDeserializer<AlbumRemote> {

    @Override
    public AlbumRemote deserialize(JsonElement json, Type typeOfT,
                              JsonDeserializationContext context) throws JsonParseException {
        Type listType = new TypeToken<AlbumRemote>() {}.getType();

        return new Gson().fromJson(json, listType);
    }

}
