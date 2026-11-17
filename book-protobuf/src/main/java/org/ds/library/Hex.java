package org.ds.library;

/** Small helper that prints bytes as hex, like a hex dump. */
public final class Hex {

    private Hex() {
    }

    public static String format(byte[] bytes, int perLine) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            if (i > 0) {
                sb.append(i % perLine == 0 ? System.lineSeparator() : " ");
            }
            sb.append(String.format("%02x", bytes[i] & 0xff));
        }
        return sb.toString();
    }
}
