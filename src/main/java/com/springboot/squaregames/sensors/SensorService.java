package com.springboot.squaregames.sensors;

import com.springboot.squaregames.HeartbeatSensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Service
public class SensorService {

    @Autowired
    private SensorRepository repository;

    public Collection<Sensor> getAllSensors() {
        return repository.findAll();
    }

    public Sensor createSensor(Sensor sensor) {
        return repository.save(sensor);
    }

    public Sensor getSensorById(Long id) {
        Sensor sensor = repository.findById(id);
        if (sensor == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sensor with id " + id + " not found");
        }
        return sensor;
    }
}