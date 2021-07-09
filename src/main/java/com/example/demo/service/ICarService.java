package com.example.demo.service;

import com.example.demo.entity.Car;

import java.util.Optional;

public interface ICarService {

    Optional<Car> findById(long id);
}
