package com.springboot.squaregames.sensors;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Sensor {
    Long id;
    String name;

    public Sensor() {
    }

    public Sensor(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}