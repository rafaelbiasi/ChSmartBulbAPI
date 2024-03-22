package br.com.rafaelbiasi.chsmartbulbled.effect;

public class CandleLightEffect extends FlickeringEffect {

    public static final int BASE_HUE = 30;
    public static final int HUE_VARIATION = 5;

    public CandleLightEffect() {
        super(BASE_HUE, HUE_VARIATION);
    }

}
