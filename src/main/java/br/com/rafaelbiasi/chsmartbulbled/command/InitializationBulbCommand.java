package br.com.rafaelbiasi.chsmartbulbled.command;

import br.com.rafaelbiasi.chsmartbulbled.util.StringUtil;

public class InitializationBulbCommand implements BulbCommand {

    @Override
    public byte[] getCommandBytes() {
        return new byte[]{
                0x30, //0
                0x31, //1
                0x32, //2
                0x33, //3
                0x34, //4
                0x35, //5
                0x36, //6
                0x37  //7
        };
    }

    @Override
    public String toString() {
        return StringUtil.bytesToHex(getCommandBytes());
    }
}
