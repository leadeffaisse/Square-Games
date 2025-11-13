package com.springboot.squaregames;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
class RandomHeartbeat implements HeartbeatSensor{
    Random random = new Random();

    @Override
    public int get() {
        return random.nextInt(191) + 40;
    }
}