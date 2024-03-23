package br.com.rafaelbiasi.chsmartbulbled.command;

import br.com.rafaelbiasi.chsmartbulbled.util.StringUtil;

import java.nio.ByteBuffer;

public class RenameBulbCommand implements BulbCommand {
    public static final byte COMMAND_SIZE = 76;
    public static final int PREFIX_SIZE = 16;
    private final String newName;

    public RenameBulbCommand(String newName) {
        this.newName = newName;
    }

    @Override
    public byte[] getCommandBytes() {
        byte[] prefixCommand = new byte[PREFIX_SIZE];
        prefixCommand[0x0] = 0x01;
        prefixCommand[0x1] = (byte) 0xfe;
        prefixCommand[0x2] = 0x00;
        prefixCommand[0x3] = 0x00;
        prefixCommand[0x4] = 0x53;
        prefixCommand[0x5] = (byte) 0x80;
        prefixCommand[0x6] = COMMAND_SIZE; //Byte array size (16)
        prefixCommand[0x7] = 0x00; //Byte array size? Never used?
        prefixCommand[0x8] = 0x00;
        prefixCommand[0x9] = 0x00;
        prefixCommand[0xA] = 0x00;
        prefixCommand[0xB] = 0x00;
        prefixCommand[0xC] = 0x00;
        prefixCommand[0xD] = 0x00;
        prefixCommand[0xE] = 0x00;
        prefixCommand[0xF] = 0x00;
        byte[] prefixName = {0x03};
        byte[] nameBytes = newName.getBytes();
        return ByteBuffer.wrap(new byte[COMMAND_SIZE])
                .put(prefixCommand)
                .put(prefixName)
                .put(nameBytes)
                .array();
    }

    @Override
    public String toString() {
        return StringUtil.bytesToHex(newName.getBytes());
    }
}
