package com.example.demo.controllers;

import com.example.demo.entity.*;
import com.example.demo.gsonserialize.*;
import com.example.demo.repository.EngineRepository;
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
@RequestMapping("/api/engine")
public class EngineController {
    private final static Logger logger = LoggerFactory.getLogger(EngineController.class);

    private EngineRepository engineRepository;

    @Autowired
    public EngineController(EngineRepository engineRepository) {
        this.engineRepository = engineRepository;
    }

    @PostMapping("/create")
    public ResponseEntity createEngine(@RequestBody Engine engine){
        engineRepository.save(engine);
        logger.info("engine create");
        return ResponseEntity.ok("двигатель сохранен");
    }

    @GetMapping("/read/{id}")
    public ResponseEntity showEngine(@PathVariable("id") Long id){
        List<Engine> engines = engineRepository.findAll();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Engine.class, new EngineSerializer())
                .create();
        if( id <= engines.size()){
            Engine engine = engineRepository.findById(id).orElse(new Engine());
            logger.info("engine read");
            return ResponseEntity.ok(gson.toJson(engine));
        }
        logger.info("engines read");
        return ResponseEntity.ok(gson.toJson(engines));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity updateEngine(@RequestBody Engine engine, @PathVariable("id") long id) throws NoEntityException {
        Engine engine1 = engineRepository
                .findById(id)
                .orElseThrow(() -> new NoEntityException("такого двигателя нет", id));
        engine1.setName(engine.getName());
        engine1.setPower(engine.getPower());
        engineRepository.save(engine1);
        return  ResponseEntity.ok("запись изменена");
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity deleteEngine(@PathVariable("id") long id) throws NoEntityException {
        Engine engine = engineRepository
                .findById(id)
                .orElseThrow(() -> new NoEntityException("такого двигателя нет", id));
        engineRepository.delete(engine);
        return ResponseEntity.ok("запись удалена");

    }
}
