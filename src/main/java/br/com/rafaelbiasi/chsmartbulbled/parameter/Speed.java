package br.com.rafaelbiasi.chsmartbulbled.parameter;

import br.com.rafaelbiasi.chsmartbulbled.util.IntRangeValidation;

public class Speed {

	private static final IntRangeValidation SPEED_VALIDATION = new IntRangeValidation(0, 255);

	private final byte speed;

	public Speed(byte speed) {
		this.speed = speed;
	}

	public static Speed speed(int speed) {
		SPEED_VALIDATION.requireInRange(speed, "speed");
		return new Speed((byte) speed);
	}

	public byte getSpeed() {
		return speed;
	}
}
