package com.example.demo.controllers;

import com.example.demo.entity.Manual;
import com.example.demo.gsonserialize.ManualSerializer;
import com.example.demo.repository.ManualRepository;
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
@RequestMapping("/api/manual")
public class ManualController {
    private final static Logger logger = LoggerFactory.getLogger(ManualController.class);

    private ManualRepository manualRepository;

    @Autowired
    public ManualController(ManualRepository manualRepository) {
        this.manualRepository = manualRepository;
    }

    @PostMapping("/create")
    public ResponseEntity createManual(@RequestBody Manual manual){
        manualRepository.save(manual);
        logger.info("Manual create");
        return ResponseEntity.ok("двигатель сохранен");
    }

    @GetMapping("/read/{id}")
    public ResponseEntity showManual(@PathVariable("id") Long id){
        List<Manual> manuals = manualRepository.findAll();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Manual.class, new ManualSerializer())
                .create();
        if( id <= manuals.size()){
            Manual manual = manualRepository.findById(id).orElse(new Manual());
            logger.info("manual read");
            return ResponseEntity.ok(gson.toJson(manual));
        }
        logger.info("manuals read");
        return ResponseEntity.ok(gson.toJson(manuals));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity updateManual(@RequestBody Manual manual, @PathVariable("id") long id) throws NoEntityException {
        Manual manual1 = manualRepository
                .findById(id)
                .orElseThrow(() -> new NoEntityException("такого руководства нет", id));
        manual1.setName(manual.getName());
        manualRepository.save(manual1);
        return  ResponseEntity.ok("запись изменена");
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity deleteManual(@PathVariable("id") long id) throws NoEntityException {
        Manual manual = manualRepository
                .findById(id)
                .orElseThrow(() -> new NoEntityException("такого руководства нет", id));
        manualRepository.delete(manual);
        return ResponseEntity.ok("запись удалена");

    }
}