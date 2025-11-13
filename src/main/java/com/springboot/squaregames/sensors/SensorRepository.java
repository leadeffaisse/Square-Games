package com.springboot.squaregames.sensors;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class SensorRepository {

    private Map<Long, Sensor> sensors = new HashMap<>();
    private Long currentId = 1L;

    public Collection<Sensor> findAll() {
        return sensors.values();
    }

    public Sensor save(Sensor sensor) {
        if (sensor.getId() == null) {
            sensor.setId(currentId++);
        }
        sensors.put(sensor.getId(), sensor);
        return sensor;
    }

    public Sensor findById(Long id) {
        return sensors.get(id);
    }
}