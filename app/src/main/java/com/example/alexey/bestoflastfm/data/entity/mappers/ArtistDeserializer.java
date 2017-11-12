package com.example.alexey.bestoflastfm.data.entity.mappers;


import com.example.alexey.bestoflastfm.data.entity.remote.ArtistRemote;
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

public class ArtistDeserializer implements JsonDeserializer<ArtistRemote> {

    @Override
    public ArtistRemote deserialize(JsonElement json, Type typeOfT,
                              JsonDeserializationContext context) throws JsonParseException {
        Type listType = new TypeToken<ArtistRemote>() {}.getType();

        return new Gson().fromJson(json, listType);
    }

}
