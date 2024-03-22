package br.com.rafaelbiasi.chsmartbulbled.parameter;

import br.com.rafaelbiasi.chsmartbulbled.util.FloatRangeValidation;
import br.com.rafaelbiasi.chsmartbulbled.util.IntRangeValidation;
import br.com.rafaelbiasi.chsmartbulbled.util.StringUtil;

public class Color {
    public static final IntRangeValidation RGBW_VALIDATION = new IntRangeValidation(0, 255);
    public static final IntRangeValidation HUE_VALIDATION = new IntRangeValidation(0, 360);
    private static final FloatRangeValidation FLOAT_VALIDATION = new FloatRangeValidation(0, 1);
    private byte red;
    private byte green;
    private byte blue;
    private byte white;

    public Color rgb(int red, int green, int blue) {
        RGBW_VALIDATION.requireInRange(red);
        RGBW_VALIDATION.requireInRange(green);
        RGBW_VALIDATION.requireInRange(blue);
        this.red(red);
        this.green(green);
        this.blue(blue);
        return this;
    }

    public Color rgb(int red, int green, int blue, int white) {
        RGBW_VALIDATION.requireInRange(white);
        this.rgb(red, green, blue);
        this.white(white);
        return this;
    }

    public Color rgb(int... rgb) {
        this.rgb(rgb[0], rgb[1], rgb[2]);
        if (rgb.length == 4) {
            RGBW_VALIDATION.requireInRange(rgb[3]);
            this.white(rgb[3]);
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
        HUE_VALIDATION.requireInRange(hue);
        rgb(hueToRGB(hue));
        return this;
    }

    public Color hue(int hue, int white) {
        RGBW_VALIDATION.requireInRange(white);
        hue(hue);
        this.white(white);
        return this;
    }

    public Color hue(int hue, float brightness, float saturation) {
        HUE_VALIDATION.requireInRange(hue);
        FLOAT_VALIDATION.requireInRange(brightness, "brightness");
        FLOAT_VALIDATION.requireInRange(saturation, "saturation");
        rgb(hueToRGB(hue, brightness, saturation));
        return this;
    }

    public Color hue(int hue, float brightness, float saturation, int white) {
        RGBW_VALIDATION.requireInRange(white);
        hue(hue, brightness, saturation);
        this.white(white);
        return this;
    }

    public Color red(int intensity) {
        RGBW_VALIDATION.requireInRange(intensity);
        this.red = (byte) intensity;
        return this;
    }

    public Color green(int intensity) {
        RGBW_VALIDATION.requireInRange(intensity);
        this.green = (byte) intensity;
        return this;
    }

    public Color blue(int intensity) {
        RGBW_VALIDATION.requireInRange(intensity);
        this.blue = (byte) intensity;
        return this;
    }

    public Color red() {
        this.red(255);
        return this;
    }

    public Color green() {
        this.green(255);
        return this;
    }

    public Color blue() {
        this.blue(255);
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

    public Color off(){
        this.rgb(0,0,0,0);
        return this;
    }

    public Color whiteRGB() {
        this.rgb(255,255,255);
        return this;
    }

    public Color white(int intensity) {
        RGBW_VALIDATION.requireInRange(intensity);
        this.white = (byte) intensity;
        return this;
    }

    public Color white() {
        this.white(255);
        return this;
    }

    public Color fullWhite() {
        this.whiteRGB();
        this.white();
        return this;
    }

    public byte getRed() {
        return red;
    }

    public byte getGreen() {
        return green;
    }

    public byte getBlue() {
        return blue;
    }

    public byte[] getGBR() {
        return new byte[]{green, blue, red};
    }

    public byte[] getRGB() {
        return new byte[]{red, green, blue};
    }

    public byte[] getGBRW() {
        return new byte[]{green, blue, red, white};
    }

    public byte[] getRGBW() {
        return new byte[]{red, green, blue, white};
    }

    public byte getWhite() {
        return white;
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
        return "#" + StringUtil.bytesToHex(this.getRGB()) + " " + StringUtil.bytesToHex(white);
    }
}
