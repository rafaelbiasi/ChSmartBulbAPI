package br.com.rafaelbiasi.chsmartbulbled.parameter.delay;

import java.time.Duration;

public class VariableDelay implements Delay {
    private final float minMs;
    private final float maxMs;

    public VariableDelay(float minMs, float max) {
        this.minMs = minMs;
        this.maxMs = max;
    }

    public static VariableDelay ms(float min, float max) {
        return new VariableDelay(min, max);
    }

    @Override
    public void doDelay(long frame) {
        try {

            Thread.sleep(Duration.ofNanos(calcDelay(frame)));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private long calcDelay(long frame) {
        long range = (long) (maxMs - minMs) * 2;
        long cyclePos = frame % range;
        double ms = cyclePos <= maxMs - minMs ? minMs + cyclePos : maxMs - (cyclePos - (maxMs - minMs));
        return Math.round(ms * 1000000);
    }
}