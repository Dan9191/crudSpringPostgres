package com.example.demo.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Engine engine;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Gear gear;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Manual manual;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private SteeringWheel steeringWheel;


    public Car() {
    }
}
