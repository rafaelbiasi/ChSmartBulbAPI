package br.com.rafaelbiasi.chsmartbulbled.command;

import br.com.rafaelbiasi.chsmartbulbled.parameter.Color;
import br.com.rafaelbiasi.chsmartbulbled.parameter.SceneEffect;

public class ColorBulbCommand extends LightBulbCommand {

	private final Color color;
	private final SceneEffect sceneEffect = SceneEffect.FIXED;

	public ColorBulbCommand(Color color) {
		this.color = sceneEffect.color(color);
	}

	@Override
	protected byte getSegmentType() {
		return 0x00;
	}

	@Override
	protected byte getSegmentIndex() {
		return 0x00;
	}

	@Override
	protected byte getRed() {
		return color.getRedIntensity();
	}

	@Override
	protected byte getBlue() {
		return color.getBlueIntensity();
	}

	@Override
	protected byte getGreen() {
		return color.getGreenIntensity();
	}

	@Override
	protected byte getSpeed() {
		return sceneEffect.calculateSpeed((byte) 255);
	}

	@Override
	protected byte getEffect() {
		return sceneEffect.getEffect();
	}

	@Override
	protected byte getWhiteIntensity() {
		return color.getWhiteIntensity();
	}

	@Override
	protected byte getYellowIntensity() {
		return color.getYellowIntensity();
	}

	@Override
	protected byte getAutoClose() {
		return 0x00;
	}

	@Override
	public String toString() {
		return color.toString();
	}
}
