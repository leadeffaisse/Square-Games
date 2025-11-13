package com.springboot.squaregames;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
class RandomHeartbeat implements HeartbeatSensor{

    private Random random;

    public RandomHeartbeat() {
        random = new Random();
    }

    @Override
    public int get() {
        return random.nextInt(191) + 40;
    }
}