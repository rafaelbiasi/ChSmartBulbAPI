package br.com.rafaelbiasi.chsmartbulbled.util;

public class FloatMinValidation {

    private final float min;

    public FloatMinValidation(float min) {
        this.min = min;
    }

    public boolean isGreatThanMin(float num) {
        return num >= min;
    }

    public void requireMin(float num) {
        if (!isGreatThanMin(num)) {
            throw new IllegalArgumentException("Number " + num + " is not min value " + min);
        }
    }
}

