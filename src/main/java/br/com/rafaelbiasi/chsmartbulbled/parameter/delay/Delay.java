package br.com.rafaelbiasi.chsmartbulbled.parameter.delay;

public interface Delay {

    default void prepareDelay() {
    }

    default void waitDelay() {
        waitDelay(0);
    }

    void waitDelay(long frame);

    default void delay() {
        prepareDelay();
        waitDelay();
    }

    long calcDelay(long frame);
}
