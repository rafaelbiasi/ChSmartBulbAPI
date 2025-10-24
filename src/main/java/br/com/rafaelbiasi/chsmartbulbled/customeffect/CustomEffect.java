package br.com.rafaelbiasi.chsmartbulbled.customeffect;

import br.com.rafaelbiasi.chsmartbulbled.bulb.BulbDevice;
import br.com.rafaelbiasi.chsmartbulbled.parameter.Color;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public interface CustomEffect {

	Color apply(BulbDevice bulbDevice, Long frame, LocalDateTime localDateTime);
}
