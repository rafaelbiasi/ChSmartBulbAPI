package br.com.rafaelbiasi.chsmartbulbled.customeffect;

import br.com.rafaelbiasi.chsmartbulbled.bulb.BulbDevice;
import br.com.rafaelbiasi.chsmartbulbled.parameter.Color;

import java.util.function.BiFunction;

public interface CustomEffect extends BiFunction<BulbDevice, Long, Color> {
}
