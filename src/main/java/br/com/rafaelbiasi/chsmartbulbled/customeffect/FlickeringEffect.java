package br.com.rafaelbiasi.chsmartbulbled.customeffect;

import br.com.rafaelbiasi.chsmartbulbled.bulb.BulbDevice;
import br.com.rafaelbiasi.chsmartbulbled.parameter.Color;

public class FlickeringEffect implements CustomEffect {

    public static final double MIN_BRIGHTNESS = 0.1;
    public static final double MAX_BRIGHTNESS = 0.9;
    public static final double MAX_FLICKER_INTENSITY = 0.15;
    public static final double MIN_FLICKER_INTENSITY2 = 0.85;
    public static final double MAX_FLICKER_HUE = 0.08;
    public static final double MIN_FLICKER_HUE = 0.04;
    public static final float SATURATION = 1.0f;
    private final int baseHue;
    private final int hueVariation;

    public FlickeringEffect(int baseHue, int hueVariation) {
        this.baseHue = baseHue;
        this.hueVariation = hueVariation;
    }

    @Override
    public Color apply(BulbDevice bulbDevice, Long frame) {
        double flickerIntensity = (Math.random() * MAX_FLICKER_INTENSITY) + MIN_FLICKER_INTENSITY2;
        double flickerHue = (Math.random() * MAX_FLICKER_HUE) - MIN_FLICKER_HUE;
        double hue = (double) baseHue + flickerHue + (Math.sin(frame / ((Math.random() * 10) + 55)) * (double) hueVariation);
        float brightness = (float) (flickerIntensity * MAX_BRIGHTNESS + MIN_BRIGHTNESS);
        return Color.color().hue((int) hue, brightness, SATURATION);

    }
}
