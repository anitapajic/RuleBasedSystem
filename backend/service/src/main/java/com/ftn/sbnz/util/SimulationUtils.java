package com.ftn.sbnz.util;

import java.util.Random;

public class SimulationUtils {

    private static final double NORMAL_TEMPERATURE_MIN = 36.5;
    private static final double NORMAL_TEMPERATURE_MAX = 37.5;
    private static final double FEVER_TEMPERATURE_MIN = 38.5;
    private static final double FEVER_TEMPERATURE_MAX = 40.0;
    private static final double FEVER_PROBABILITY = 0.1;

    private static final double NORMAL_SATURATION_MIN = 92.5;
    private static final double NORMAL_SATURATION_MAX = 100.0;
    private static final double LOW_SATURATION_MIN = 85.0;
    private static final double LOW_SATURATION_MAX = 92.4;
    private static final double LOW_SATURATION_PROBABILITY = 0.1;

    private static final Random random = new Random();

    public static Double generateTemperature(){
        if (random.nextDouble() < FEVER_PROBABILITY) {
            return FEVER_TEMPERATURE_MIN + (FEVER_TEMPERATURE_MAX - FEVER_TEMPERATURE_MIN) * random.nextDouble();
        } else {
            return NORMAL_TEMPERATURE_MIN + (NORMAL_TEMPERATURE_MAX - NORMAL_TEMPERATURE_MIN) * random.nextDouble();
        }
    }

    public static Double generateOxygenSaturation() {
        if (random.nextDouble() < LOW_SATURATION_PROBABILITY) {
            return LOW_SATURATION_MIN + (LOW_SATURATION_MAX - LOW_SATURATION_MIN) * random.nextDouble();
        } else {
            return NORMAL_SATURATION_MIN + (NORMAL_SATURATION_MAX - NORMAL_SATURATION_MIN) * random.nextDouble();
        }
    }

}
