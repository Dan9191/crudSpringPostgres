package com.example.demo.controllers;



import com.example.demo.entity.SteeringWheel;
import com.example.demo.gsonserialize.SteeringWheelSerializer;
import com.example.demo.repository.SteeringWheelRepository;
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
@RequestMapping("/api/steeringwheel")
public class SteeringWheelController {
        private final static Logger logger = LoggerFactory.getLogger(EngineController.class);

        private SteeringWheelRepository steeringRepository;

    @Autowired
    public SteeringWheelController(SteeringWheelRepository steeringRepository) {
        this.steeringRepository = steeringRepository;
    }

        @PostMapping("/create")
        public ResponseEntity createSteeringWheel(@RequestBody SteeringWheel steeringWheel){
        steeringRepository.save(steeringWheel);
        logger.info("engine create");
        return ResponseEntity.ok("руль сохранен");
    }

        @GetMapping("/read/{id}")
        public ResponseEntity showSteeringWheel(@PathVariable("id") Long id){
        List<SteeringWheel> steeringWheels = steeringRepository.findAll();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(SteeringWheel.class, new SteeringWheelSerializer())
                .create();
        if( id <= steeringWheels.size()){
            SteeringWheel steeringWheel = steeringRepository.findById(id).orElse(new SteeringWheel());
            logger.info("engine read");
            return ResponseEntity.ok(gson.toJson(steeringWheel));
        }
        logger.info("engines read");
        return ResponseEntity.ok(gson.toJson(steeringWheels));
    }

        @PostMapping("/update/{id}")
        public ResponseEntity updateSteeringWheel(@RequestBody SteeringWheel steeringWheel, @PathVariable("id") long id) throws NoEntityException
        {
            SteeringWheel steeringWheel1 = steeringRepository
                    .findById(id)
                    .orElseThrow(() -> new NoEntityException("такого руля нет", id));
            steeringWheel1.setName(steeringWheel.getName());
            steeringWheel1.setMaterial(steeringWheel.getMaterial());
            steeringRepository.save(steeringWheel1);
            return  ResponseEntity.ok("запись изменена");
        }

        @PostMapping("/delete/{id}")
        public ResponseEntity deleteSteeringWheel(@PathVariable("id") long id) throws NoEntityException {
        SteeringWheel steeringWheel = steeringRepository
                .findById(id)
                .orElseThrow(() -> new NoEntityException("такого руля нет", id));
        steeringRepository.delete(steeringWheel);
        return ResponseEntity.ok("запись удалена");

    }
}
