package br.com.rafaelbiasi.chsmartbulbled.bulb;

import java.time.Duration;

public class RandomRangeDelay implements Delay {
    private final float minMs;
    private final float maxMs;

    public RandomRangeDelay(float minMs, float max) {
        this.minMs = minMs;
        this.maxMs = max;
    }

    public static RandomRangeDelay ms(float min, float max) {
        return new RandomRangeDelay(min, max);
    }

    @Override
    public void controlSpeed() {
        try {
            double ms = (Math.random() * maxMs) + minMs;
            Thread.sleep(Duration.ofNanos(Math.round(ms * 1000000)));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
