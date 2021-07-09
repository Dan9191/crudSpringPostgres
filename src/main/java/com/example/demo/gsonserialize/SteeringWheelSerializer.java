package com.example.demo.gsonserialize;

import com.example.demo.entity.Gear;
import com.example.demo.entity.SteeringWheel;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class SteeringWheelSerializer implements JsonSerializer<SteeringWheel> {

    @Override
    public JsonElement serialize(SteeringWheel steeringWheel, Type typeOfSteeringWheel, JsonSerializationContext context) {

        JsonObject result = new JsonObject();
        result.addProperty("name", steeringWheel.getName());
        result.addProperty("material", steeringWheel.getMaterial());
        return result;
    }
}