package br.com.rafaelbiasi.chsmartbulbled.command;

import br.com.rafaelbiasi.chsmartbulbled.parameter.Color;
import br.com.rafaelbiasi.chsmartbulbled.parameter.SceneEffect;
import br.com.rafaelbiasi.chsmartbulbled.parameter.Speed;

public class SceneBulbCommand extends LightBulbCommand {

	private final SceneEffect sceneEffect;
	private final Color color;
	private Speed speed;

	public SceneBulbCommand(SceneEffect sceneEffect, Color color, Speed speed) {
		this.sceneEffect = sceneEffect;
		this.color = color;
		this.speed = speed;
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
		return sceneEffect.color(color).getRedIntensity();
	}

	@Override
	protected byte getBlue() {
		return sceneEffect.color(color).getBlueIntensity();
	}

	@Override
	protected byte getGreen() {
		return sceneEffect.color(color).getGreenIntensity();
	}

	@Override
	protected byte getSpeed() {
		return speed.getSpeed();
	}

	@Override
	protected byte getEffect() {
		return sceneEffect.getEffect();
	}

	@Override
	protected byte getWhiteIntensity() {
		return sceneEffect.color(color).getWhiteIntensity();
	}

	@Override
	protected byte getYellowIntensity() {
		return sceneEffect.color(color).getYellowIntensity();
	}

	@Override
	protected byte getAutoClose() {
		return 0x00;
	}
}
