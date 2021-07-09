package com.example.demo.gsonserialize;

import com.example.demo.entity.Car;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class CarSerializer implements JsonSerializer<Car> {

    @Override
    public JsonElement serialize(Car car, Type typeOfCar, JsonSerializationContext context) {

        JsonObject result = new JsonObject();
        result.addProperty("name", car.getName());
        result.add("engine", context.serialize(car.getEngine()));
        result.add("gear", context.serialize(car.getGear()));
        result.add("manual", context.serialize(car.getManual()));
        result.add("steeringWheel", context.serialize(car.getSteeringWheel()));
        return result;
    }

}
