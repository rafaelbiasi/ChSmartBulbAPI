package br.com.rafaelbiasi.chsmartbulbled.util;

public class FloatRangeValidation {

    private final float min;
    private final float max;

    public FloatRangeValidation(float min, float max) {
        this.min = min;
        this.max = max;
    }

    public boolean isInRange(float num) {
        return num >= min && num <= max;
    }

    public void requireInRange(float num) {
        if (!isInRange(num)) {
            throw new IllegalArgumentException("Number " + num + " is not in range " + min + " to " + max);
        }
    }

    public void requireInRange(float num, String field) {
        if (!isInRange(num)) {
            throw new IllegalArgumentException(field + " number " + num + " is not in range " + min + " to " + max);
        }
    }
}

