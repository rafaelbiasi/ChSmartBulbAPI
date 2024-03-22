package br.com.rafaelbiasi.chsmartbulbled.command;

import br.com.rafaelbiasi.chsmartbulbled.parameter.Color;

public class ColorBulbCommand implements BulbCommand {
    private final Color color;

    public ColorBulbCommand(Color color) {
        this.color = color;
    }

    @Override
    public byte[] getCommandBytes() {
        return new byte[]{
                0x01,
                (byte) 0xfe,
                0x00,
                0x00,
                0x53,
                (byte) 0x83,
                0x10,
                0x00,
                color.getGreen(),
                color.getBlue(),
                color.getRed(),
                0x00,
                0x50,
                color.getWhite(),
                0x00,
                0x00
        };
    }

    @Override
    public String toString() {
        return color.toString();
    }
}
