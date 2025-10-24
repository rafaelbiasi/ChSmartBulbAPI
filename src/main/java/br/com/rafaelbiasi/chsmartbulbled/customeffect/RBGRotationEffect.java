package br.com.rafaelbiasi.chsmartbulbled.customeffect;

import br.com.rafaelbiasi.chsmartbulbled.bulb.BulbDevice;
import br.com.rafaelbiasi.chsmartbulbled.parameter.Color;

import java.time.LocalDateTime;

public class RBGRotationEffect implements CustomEffect {

    private final int angle;

    public RBGRotationEffect() {
        this.angle = 0;
    }

    public RBGRotationEffect(int angle) {
        this.angle = angle;
    }

    @Override
    public Color apply(BulbDevice bulbDevice, Long frame, LocalDateTime localDateTime) {
        return Color.color().hue(Math.floorMod(frame + angle + 1, 360), 1f, 1f);
    }
}
