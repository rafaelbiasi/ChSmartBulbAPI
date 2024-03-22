package br.com.rafaelbiasi.chsmartbulbled.parameter.delay;

public class LinearUpDownDelay implements Delay {
    private final float minMs;
    private final float maxMs;

    private long startTime;

    public LinearUpDownDelay(float minMs, float max) {
        this.minMs = minMs;
        this.maxMs = max;
    }

    public static LinearUpDownDelay ms(float min, float max) {
        return new LinearUpDownDelay(min, max);
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
        long range = (long) (maxMs - minMs) * 2;
        long cyclePos = frame % range;
        double ms = cyclePos <= maxMs - minMs ? minMs + cyclePos : maxMs - (cyclePos - (maxMs - minMs));
        return Math.round(ms * 1000000);
    }
}