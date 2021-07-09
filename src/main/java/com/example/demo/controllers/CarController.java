package com.example.demo.controllers;

import com.example.demo.entity.*;
import com.example.demo.gsonserialize.*;
import com.example.demo.repository.CarRepository;
import com.example.demo.utils.NoEntityException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/car")
public class CarController {
    private final static Logger logger = LoggerFactory.getLogger(CarController.class);

    private CarRepository carRepository;

    @Autowired
    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @RequestMapping
    public ResponseEntity json(){
        URL url = this.getClass().getClassLoader().getResource("cars.json");
        if(url!=null) {
            File jsonFile = new File(url.getFile());
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                List<Car> cars = objectMapper.readValue(jsonFile, new TypeReference<List<Car>>(){});
                for(Car car: cars) {
                        carRepository.save(car);
                }
                logger.info("record saved");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else{
            logger.warn("id is null");
        }
        return ResponseEntity.ok("записи добавлены");
    }


    @PostMapping("/create")
    public ResponseEntity createCar(@RequestBody Car car){
        carRepository.save(car);
        logger.info("car create");
        return ResponseEntity.ok("автомобиль сохранен");
    }


    @GetMapping("/read/{id}")
    public ResponseEntity showCar(@PathVariable("id") Long id){
        List<Car> cars = carRepository.findAll();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Car.class, new CarSerializer())
                .registerTypeAdapter(Engine.class, new EngineSerializer())
                .registerTypeAdapter(Gear.class, new GearSerializer())
                .registerTypeAdapter(Manual.class, new ManualSerializer())
                .registerTypeAdapter(SteeringWheel.class, new SteeringWheelSerializer())
                .create();
        if( id <= cars.size()){
            Car car = carRepository.findById(id).orElse(new Car());
            logger.info("car read");
            return ResponseEntity.ok(gson.toJson(car));
        }
        logger.info("cars read");
        return ResponseEntity.ok(gson.toJson(cars));
    }



    @PostMapping("/update/{id}")
    public ResponseEntity updateCar(@RequestBody Car car, @PathVariable("id") long id) throws NoEntityException {
        Car car1 = carRepository
                .findById(id)
                .orElseThrow(() -> new NoEntityException("такой машины нет", id));
        car1.setName(car.getName());
        car1.setEngine(car.getEngine());
        car1.setGear(car.getGear());
        car1.setManual(car.getManual());
        car1.setSteeringWheel(car.getSteeringWheel());
        carRepository.save(car1);
        return  ResponseEntity.ok("запись изменена");
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity deleteCar(@PathVariable("id") long id) throws NoEntityException {
        Car car = carRepository
                .findById(id)
                .orElseThrow(() -> new NoEntityException("такой машины нет", id));
        carRepository.delete(car);
        return ResponseEntity.ok("запись удалена");

    }

}
