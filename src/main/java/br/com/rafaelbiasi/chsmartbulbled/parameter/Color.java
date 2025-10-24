package br.com.rafaelbiasi.chsmartbulbled.parameter;

import br.com.rafaelbiasi.chsmartbulbled.util.FloatRangeValidation;
import br.com.rafaelbiasi.chsmartbulbled.util.IntRangeValidation;
import br.com.rafaelbiasi.chsmartbulbled.util.StringUtil;

public class Color {

	private static final IntRangeValidation RGBWY_VALIDATION = new IntRangeValidation(0, 255);
	private static final IntRangeValidation HUE_VALIDATION = new IntRangeValidation(0, 360);
	private static final FloatRangeValidation FLOAT_VALIDATION = new FloatRangeValidation(0, 1);

	private byte redIntensity;
	private byte greenIntensity;
	private byte blueIntensity;
	private byte whiteIntensity;
	private byte yellowIntensity;

	public Color rgb(int redIntensity, int greenIntensity, int blueIntensity) {
		RGBWY_VALIDATION.requireInRange(redIntensity, "redIntensity");
		RGBWY_VALIDATION.requireInRange(greenIntensity, "greenIntensity");
		RGBWY_VALIDATION.requireInRange(blueIntensity, "blueIntensity");
		this.redIntensity(redIntensity);
		this.greenIntensity(greenIntensity);
		this.blueIntensity(blueIntensity);
		return this;
	}

	public Color rgb(int redIntensity, int greenIntensity, int blueIntensity, int whiteIntensity, int yellowIntensity) {
		RGBWY_VALIDATION.requireInRange(whiteIntensity, "whiteIntensity");
		this.rgb(redIntensity, greenIntensity, blueIntensity);
		this.white(whiteIntensity);
		this.yellowIntensity(yellowIntensity);
		return this;
	}

	public Color rgb(int... rgb) {
		this.rgb(rgb[0], rgb[1], rgb[2]);
		if (rgb.length == 4) {
			RGBWY_VALIDATION.requireInRange(rgb[3], "RGBW");
			this.white(rgb[3]);
		}
		if (rgb.length == 5) {
			RGBWY_VALIDATION.requireInRange(rgb[3], "RGBWY");
			this.white(rgb[3]);
			this.yellowIntensity(rgb[4]);
		}
		return this;
	}

	public Color rgb(String rgbw) {
		String cleanedInput = rgbw.startsWith("#") ? rgbw.substring(1) : rgbw;
		int red = Integer.parseInt(cleanedInput.substring(0, 2), 16);
		int green = Integer.parseInt(cleanedInput.substring(2, 4), 16);
		int blue = Integer.parseInt(cleanedInput.substring(4, 6), 16);
		this.rgb(red, green, blue);
		if (cleanedInput.length() == 8) {
			int white = Integer.parseInt(cleanedInput.substring(6, 8), 16);
			this.white(white);
		}
		return this;
	}

	public Color hue(int hue) {
		HUE_VALIDATION.requireInRange(hue, "hue");
		rgb(hueToRGB(hue));
		return this;
	}

	public Color hue(int hue, int white) {
		RGBWY_VALIDATION.requireInRange(white, "whiteIntensity");
		hue(hue);
		this.white(white);
		return this;
	}

	public Color hue(int hue, float brightness, float saturation) {
		HUE_VALIDATION.requireInRange(hue, "hue");
		FLOAT_VALIDATION.requireInRange(brightness, "brightness");
		FLOAT_VALIDATION.requireInRange(saturation, "saturation");
		rgb(hueToRGB(hue, brightness, saturation));
		return this;
	}

	public Color hue(int hue, float brightness, float saturation, int whiteIntensity) {
		RGBWY_VALIDATION.requireInRange(whiteIntensity, "whiteIntensity");
		hue(hue, brightness, saturation);
		this.white(whiteIntensity);
		return this;
	}

	public Color hue(int hue, float brightness, float saturation, int whiteIntensity, int yellowIntensity) {
		RGBWY_VALIDATION.requireInRange(whiteIntensity, "whiteIntensity");
		hue(hue, brightness, saturation);
		this.white(whiteIntensity);
		this.yellowIntensity(yellowIntensity);
		return this;
	}

	public Color redIntensity(int intensity) {
		RGBWY_VALIDATION.requireInRange(intensity, "redIntensity");
		this.redIntensity = (byte) intensity;
		return this;
	}

	public Color greenIntensity(int intensity) {
		RGBWY_VALIDATION.requireInRange(intensity, "greenIntensity");
		this.greenIntensity = (byte) intensity;
		return this;
	}

	public Color blueIntensity(int intensity) {
		RGBWY_VALIDATION.requireInRange(intensity, "blueIntensity");
		this.blueIntensity = (byte) intensity;
		return this;
	}

	public Color red() {
		this.redIntensity(255);
		return this;
	}

	public Color green() {
		this.greenIntensity(255);
		return this;
	}

	public Color blue() {
		this.blueIntensity(255);
		return this;
	}

	public Color cyan() {
		this.green();
		this.blue();
		return this;
	}

	public Color magenta() {
		this.red();
		this.blue();
		return this;
	}

	public Color yellow() {
		this.red();
		this.green();
		return this;
	}

	public Color off() {
		this.rgb(0, 0, 0, 0, 0);
		return this;
	}

	public Color whiteRGB() {
		this.rgb(255, 255, 255);
		return this;
	}

	public Color white(int intensity) {
		RGBWY_VALIDATION.requireInRange(intensity, "whiteIntensity");
		this.whiteIntensity = (byte) intensity;
		return this;
	}

	public Color yellowIntensity(int intensity) {
		RGBWY_VALIDATION.requireInRange(intensity, "yellowIntensity");
		this.yellowIntensity = (byte) intensity;
		return this;
	}

	public Color white() {
		this.white(255);
		return this;
	}

	public Color fullWhite() {
		this.whiteRGB();
		this.white();
		this.yellowIntensity(255);
		return this;
	}

	public Color fullBlack() {
		this.off();
		return this;
	}

	public byte getRedIntensity() {
		return redIntensity;
	}

	public byte getGreenIntensity() {
		return greenIntensity;
	}

	public byte getBlueIntensity() {
		return blueIntensity;
	}

	public byte getWhiteIntensity() {
		return whiteIntensity;
	}

	public byte getYellowIntensity() {
		return yellowIntensity;
	}

	public byte[] getRGBWY() {
		return new byte[]{redIntensity, greenIntensity, blueIntensity, whiteIntensity, yellowIntensity};
	}

	public static Color color() {
		return new Color();
	}

	private static int[] hueToRGB(int hue) {
		return hueToRGB(hue, 1.0f, 1.0f);
	}

	private static int[] hueToRGB(int hue, float brightness, float saturation) {
		float h = (hue % 360) / 60f;
		int i = (int) Math.floor(h);
		float f = h - i;
		float p = brightness * (1 - saturation);
		float q = brightness * (1 - saturation * f);
		float t = brightness * (1 - saturation * (1 - f));

		float red = 0, green = 0, blue = 0;
		switch (i) {
			case 0:
				red = brightness;
				green = t;
				blue = p;
				break;
			case 1:
				red = q;
				green = brightness;
				blue = p;
				break;
			case 2:
				red = p;
				green = brightness;
				blue = t;
				break;
			case 3:
				red = p;
				green = q;
				blue = brightness;
				break;
			case 4:
				red = t;
				green = p;
				blue = brightness;
				break;
			case 5:
				red = brightness;
				green = p;
				blue = q;
				break;
		}
		return new int[]{(int) (red * 255), (int) (green * 255), (int) (blue * 255)};
	}

	@Override
	public String toString() {
		return "#" + StringUtil.bytesToHex(this.getRGBWY());
	}
}
