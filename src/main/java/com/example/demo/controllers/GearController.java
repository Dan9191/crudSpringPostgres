package com.example.demo.controllers;

import com.example.demo.entity.Gear;
import com.example.demo.gsonserialize.GearSerializer;
import com.example.demo.repository.GearRepository;
import com.example.demo.utils.NoEntityException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/gear")
public class GearController {

    private final static Logger logger = LoggerFactory.getLogger(GearController.class);

    private GearRepository gearRepository;

    @Autowired
    public GearController(GearRepository gearRepository) {
        this.gearRepository = gearRepository;
    }

    @PostMapping("/create")
    public ResponseEntity createGear(@RequestBody Gear gear){
        gearRepository.save(gear);
        logger.info("engine create");
        return ResponseEntity.ok("двигатель сохранен");
    }

    @GetMapping("/read/{id}")
    public ResponseEntity showGear(@PathVariable("id") Long id){
        List<Gear> gears = gearRepository.findAll();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Gear.class, new GearSerializer())
                .create();
        if( id <= gears.size()){
            Gear gear = gearRepository.findById(id).orElse(new Gear());
            logger.info("gears read");
            return ResponseEntity.ok(gson.toJson(gear));
        }
        logger.info("gears read");
        return ResponseEntity.ok(gson.toJson(gears));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity updateGear(@RequestBody Gear gear, @PathVariable("id") long id) throws NoEntityException {
        Gear gear1 = gearRepository
                .findById(id)
                .orElseThrow(() -> new NoEntityException("такого двигателя нет", id));
        gear1.setName(gear.getName());
        gear1.setQuantity(gear.getQuantity());
        gearRepository.save(gear1);
        return  ResponseEntity.ok("запись изменена");
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity deleteGear(@PathVariable("id") long id) throws NoEntityException {
        Gear gear = gearRepository
                .findById(id)
                .orElseThrow(() -> new NoEntityException("такой шекстерни нет", id));
        gearRepository.delete(gear);
        return ResponseEntity.ok("запись удалена");

    }
}