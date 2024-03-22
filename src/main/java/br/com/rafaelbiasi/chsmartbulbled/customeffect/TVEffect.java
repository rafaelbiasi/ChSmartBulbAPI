package br.com.rafaelbiasi.chsmartbulbled.effect;

import br.com.rafaelbiasi.chsmartbulbled.bulb.BulbDevice;
import br.com.rafaelbiasi.chsmartbulbled.bulb.Color;

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

    @Override
    public Color apply(BulbDevice bulbDevice, Integer frame) {
        double flickerIntensity = (Math.random() * MAX_FLICKER_INTENSITY) + MIN_FLICKER_INTENSITY2;
        double flickerHue = (Math.random() * MAX_FLICKER_HUE) - MIN_FLICKER_HUE;
        double hue = (double) BASE_HUE + flickerHue + (Math.sin(frame / ((Math.random() * 10) + 15)) * (double) HUE_VARIATION);
        float brightness = (float) (flickerIntensity * MAX_BRIGHTNESS + MIN_BRIGHTNESS);
        return Color.color().hue((int) hue, brightness, SATURATION);

    }
}
