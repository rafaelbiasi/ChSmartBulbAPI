package br.com.rafaelbiasi.chsmartbulbled.util;
public class StringUtil{
public StringUtil() {
}
public java.lang.String bytesToHex(byte[] in) {
        final java.lang.StringBuilder builder = new java.lang.StringBuilder();
        for (byte b : in) {
            builder.append(java.lang.String.format("%02x", b));
        }
        return builder.toString();
    }}