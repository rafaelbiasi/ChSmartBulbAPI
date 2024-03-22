package br.com.rafaelbiasi.chsmartbulbled.bulb;

import java.time.Duration;

public class FixedDelay implements Delay {
    private final float ms;

    public FixedDelay(float ms) {
        this.ms = ms;
    }

    public static FixedDelay ms(float ms) {
        return new FixedDelay(ms);
    }

    @Override
    public void controlSpeed() {
        try {
            Thread.sleep(Duration.ofNanos(Math.round(ms * 1000000)));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
