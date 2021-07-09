package com.example.demo.service;

import com.example.demo.controllers.CarController;
import com.example.demo.entity.Car;
import com.example.demo.repository.CarRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;


@Service
public class CarService implements ICarService {

    @Autowired
    private CarRepository carRepository;

    private final static Logger logger = LoggerFactory.getLogger(CarController.class);


    public  static void createJsonFile(Car car) {
        GsonBuilder b = new GsonBuilder();
        Gson gson = b.setPrettyPrinting()
                .create();
        String json = gson.toJson(car);

        try(FileOutputStream fos=new FileOutputStream("resume.json")) {
            byte[] buffer = json.getBytes();
            fos.write(buffer, 0, buffer.length);
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Optional<Car> findById(long id) {
        return carRepository.findById(id);
    }
}
