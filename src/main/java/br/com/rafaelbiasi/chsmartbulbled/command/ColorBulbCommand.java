package br.com.rafaelbiasi.chsmartbulbled.command;

import br.com.rafaelbiasi.chsmartbulbled.parameter.Color;

public class ColorBulbCommand implements BulbCommand {
    public static final byte COMMAND_SIZE = 16;
    private final Color color;

    public ColorBulbCommand(Color color) {
        this.color = color;
    }

    @Override
    public byte[] getCommandBytes() {
        byte[] command = new byte[COMMAND_SIZE];
        command[0x0] = 0x01;
        command[0x1] = (byte) 0xfe;
        command[0x2] = 0x00;
        command[0x3] = 0x00;
        command[0x4] = 0x53;
        command[0x5] = (byte) 0x83;
        command[0x6] = COMMAND_SIZE; //Byte array size (16)
        command[0x7] = 0x00; //Byte array size? Never used?
        command[0x8] = color.getGreen();
        command[0x9] = color.getBlue();
        command[0xA] = color.getRed();
        command[0xB] = 0x00;
        command[0xC] = 0x50;
        command[0xD] = color.getWhite();
        command[0xE] = 0x00;
        command[0xF] = 0x00;
        return command;
    }

    @Override
    public String toString() {
        return color.toString();
    }
}
