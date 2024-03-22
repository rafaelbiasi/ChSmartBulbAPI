package br.com.rafaelbiasi.chsmartbulbled.parameter.delay;

import br.com.rafaelbiasi.chsmartbulbled.util.FloatMinValidation;

public class FixedDelay implements Delay {

    public static final int MIN_MS = 15;
    private static final FloatMinValidation INT_MIN_VALIDATION = new FloatMinValidation(MIN_MS);

    private final float ms;
    private long startTime;

    public FixedDelay(float ms) {
        this.ms = ms;
    }

    public static FixedDelay ms(float ms) {
        INT_MIN_VALIDATION.requireMin(ms);
        return new FixedDelay(ms);
    }

    public static FixedDelay minMs() {
        return new FixedDelay(MIN_MS);
    }

    @Override
    public void prepareDelay() {
        this.startTime = System.nanoTime();
    }

    @Override
    public void waitDelay(long frame) {
        long delay = calcDelay(frame);
        while ((System.nanoTime() - startTime) < delay) {
        }
    }

    @Override
    public long calcDelay(long frame) {
        return Math.round(ms * 1000000);
    }
}
