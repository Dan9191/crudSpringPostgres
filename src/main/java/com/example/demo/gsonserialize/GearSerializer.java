package com.example.demo.gsonserialize;

import com.example.demo.entity.Engine;
import com.example.demo.entity.Gear;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class GearSerializer implements JsonSerializer<Gear> {

    @Override
    public JsonElement serialize(Gear gear, Type typeOfGear, JsonSerializationContext context) {

        JsonObject result = new JsonObject();
        result.addProperty("name", gear.getName());
        result.addProperty("quantity", gear.getQuantity());
        return result;
    }
}