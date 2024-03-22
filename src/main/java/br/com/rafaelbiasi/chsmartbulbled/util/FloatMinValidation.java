package br.com.rafaelbiasi.chsmartbulbled.util;

public class IntMinValidation {

    private final int min;

    public IntMinValidation(int min) {
        this.min = min;
    }

    public boolean isGreatThanMin(int num) {
        return num >= min;
    }

    public void requireMin(int num) {
        if (!isGreatThanMin(num)) {
            throw new IllegalArgumentException("Number " + num + " is not min value " + min);
        }
    }
}

