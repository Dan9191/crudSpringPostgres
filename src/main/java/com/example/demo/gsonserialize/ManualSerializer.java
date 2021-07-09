package com.example.demo.gsonserialize;

import com.example.demo.entity.Gear;
import com.example.demo.entity.Manual;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class ManualSerializer implements JsonSerializer<Manual> {

    @Override
    public JsonElement serialize(Manual manual, Type typeOfManual, JsonSerializationContext context) {

        JsonObject result = new JsonObject();
        result.addProperty("name", manual.getName());
        return result;
    }
}