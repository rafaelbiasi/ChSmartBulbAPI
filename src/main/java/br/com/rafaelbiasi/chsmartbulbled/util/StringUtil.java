package br.com.rafaelbiasi.chsmartbulbled.util;

public class StringUtil {

	private static final char[] HEX = "0123456789abcdef".toCharArray();
	
    public static String bytesToHex(byte... in) {
		if (in == null || in.length == 0) return "";
		StringBuilder sb = new StringBuilder(in.length * 3 - 1);
		for (int i = 0; i < in.length; i++) {
			if (i > 0) sb.append(':');
			int v = in[i] & 0xFF;
			sb.append(HEX[v >>> 4]);
			sb.append(HEX[v & 0x0F]);
		}
		return sb.toString();
    }

    public static byte[] hexToBytes(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // Usando Integer.parseInt com base 16 para converter de hexadecimal para inteiro
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}