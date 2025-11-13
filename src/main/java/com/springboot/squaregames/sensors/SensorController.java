package com.springboot.squaregames.sensors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    // Récupère tous les capteurs
    @GetMapping
    public Collection<Sensor> getAllSensors() {
        return sensorService.getAllSensors();
    }

    @PostMapping
    public Sensor createSensor(@RequestBody Sensor sensor) {
        return sensorService.createSensor(sensor);
    }

    // Récupérer un capteur par son Id
    @GetMapping("/{id}")
    public Sensor getSensorById(@PathVariable Long id) {
        return sensorService.getSensorById(id);
    }
}
