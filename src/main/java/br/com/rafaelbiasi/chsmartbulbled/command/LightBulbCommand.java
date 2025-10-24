package br.com.rafaelbiasi.chsmartbulbled.command;

import br.com.rafaelbiasi.chsmartbulbled.util.StringUtil;

public abstract class LightBulbCommand implements BulbCommand {

	private static final byte LIGHT_OP = (byte) 0x83;
	private static final byte COMMAND_SIZE = 0x10; //16 bytes
	private static final byte SET_OP = 0x53;

	@Override
	public byte[] getCommandBytes() {
		byte[] command = new byte[COMMAND_SIZE];
		command[0x0] = 0x01;
		command[0x1] = (byte) 0xfe;
		command[0x2] = getSegmentType();
		command[0x3] = getSegmentIndex();
		command[0x4] = SET_OP;
		command[0x5] = LIGHT_OP;
		command[0x6] = COMMAND_SIZE; //Byte array size (16) little-endian
		command[0x7] = 0x00; //Byte array size (16) little-endian
		command[0x8] = getGreen();
		command[0x9] = getBlue();
		command[0xA] = getRed();
		command[0xB] = getSpeed();
		command[0xC] = getEffect();
		command[0xD] = getWhiteIntensity();
		command[0xE] = getYellowIntensity();
		command[0xF] = getAutoClose();
		return command;
	}

	protected abstract byte getSegmentType();

	protected abstract byte getSegmentIndex();

	protected abstract byte getRed();

	protected abstract byte getBlue();

	protected abstract byte getGreen();

	protected abstract byte getSpeed();

	protected abstract byte getEffect();

	protected abstract byte getWhiteIntensity();

	protected abstract byte getYellowIntensity();

	protected abstract byte getAutoClose();

	@Override
	public String toString() {
		return StringUtil.bytesToHex(getCommandBytes());
	}
}
