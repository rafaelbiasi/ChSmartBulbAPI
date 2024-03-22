package br.com.rafaelbiasi.chsmartbulbled.customeffect;

public class TVEffect extends FlickeringEffect {

    public static final double MIN_BRIGHTNESS = 0.1;
    public static final double MAX_BRIGHTNESS = 0.9;
    public static final double MAX_FLICKER_INTENSITY = 0.15;
    public static final double MIN_FLICKER_INTENSITY2 = 0.85;
    public static final double MAX_FLICKER_HUE = 0.08;
    public static final double MIN_FLICKER_HUE = 0.04;
    public static final int BASE_HUE = 195;
    public static final int HUE_VARIATION = 30;
    public static final float SATURATION = 1.0f;

    public TVEffect() {
        super(BASE_HUE, HUE_VARIATION);
    }


}
