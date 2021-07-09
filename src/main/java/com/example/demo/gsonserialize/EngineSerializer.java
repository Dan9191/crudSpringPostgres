package com.example.demo.gsonserialize;

import com.example.demo.entity.Car;
import com.example.demo.entity.Engine;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class EngineSerializer implements JsonSerializer<Engine> {

    @Override
    public JsonElement serialize(Engine engine, Type typeOfEngine, JsonSerializationContext context) {

        JsonObject result = new JsonObject();
        result.addProperty("name", engine.getName());
        result.addProperty("power", engine.getPower());
        return result;
    }
}