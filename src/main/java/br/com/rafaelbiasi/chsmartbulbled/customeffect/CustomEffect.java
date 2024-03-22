package br.com.rafaelbiasi.chsmartbulbled.effect;

import br.com.rafaelbiasi.chsmartbulbled.bulb.BulbDevice;
import br.com.rafaelbiasi.chsmartbulbled.bulb.Color;

import java.util.function.BiFunction;

public interface CustomEffect extends BiFunction<BulbDevice, Integer, Color> {
}
