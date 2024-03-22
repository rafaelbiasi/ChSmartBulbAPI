package br.com.rafaelbiasi.chsmartbulbled.util;

public class IntRangeValidation {

    private final int min;
    private final int max;

    public IntRangeValidation(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public boolean isInRange(int num) {
        return num >= min && num <= max;
    }

    public void requireInRange(int num) {
        if (!isInRange(num)) {
            throw new IllegalArgumentException("Number " + num + " is not in range " + min + " to " + max);
        }
    }
}

