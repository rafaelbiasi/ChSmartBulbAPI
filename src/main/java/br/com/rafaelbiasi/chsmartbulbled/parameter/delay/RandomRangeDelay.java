package br.com.rafaelbiasi.chsmartbulbled.parameter.delay;

public class RandomRangeDelay implements Delay {
    private final float minMs;
    private final float maxMs;
    private long startTime;

    public RandomRangeDelay(float minMs, float max) {
        this.minMs = minMs;
        this.maxMs = max;
    }

    public static RandomRangeDelay ms(float min, float max) {
        return new RandomRangeDelay(min, max);
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
        double ms = (Math.random() * maxMs) + minMs;
        return Math.round(ms * 1000000);
    }
}
