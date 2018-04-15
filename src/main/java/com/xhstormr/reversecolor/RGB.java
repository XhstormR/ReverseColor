package com.xhstormr.reversecolor;

public class RGB {
    public static int[] Int2ARGB(int rgb) { // ARGB
        final int[] ints = new int[4];

        ints[0] = 0xFF & (rgb >> 24);//A
        ints[1] = 0xFF & (rgb >> 16);//R
        ints[2] = 0xFF & (rgb >> 8); //G
        ints[3] = 0xFF & (rgb >> 0); //B

        return ints;
    }

    public static int[] Int2RGB(int rgb) { // RGB
        final int[] ints = new int[3];

        ints[0] = 0xFF & (rgb >> 16);//R
        ints[1] = 0xFF & (rgb >> 8); //G
        ints[2] = 0xFF & (rgb >> 0); //B

        return ints;
    }

    public static int RGB2Int(int[] rgb) { // RGB、ARGB 通用
        final int length = rgb.length;
        int i = 0;

        for (int j = 0; j < length; j++) {
            i |= (0xFF & rgb[j]) << (length - j - 1) * 8;
        }

        return i;
    }

    /**/

    public static String RGB2HEX(int[] rgb) { // RGB、ARGB 通用
        final StringBuilder str = new StringBuilder();

        for (int i : rgb) {
            String s = Integer.toHexString(i);
            if (s.length() < 2) {
                str.append('0');
            }
            str.append(s);
        }

        return str.toString().toUpperCase();
    }

    public static int[] HEX2RGB(String rgb) { // RGB、ARGB 通用
        final int length = rgb.length();
        final int[] ints = new int[length / 2];

        for (int i = 0; i < length; i += 2) {
            ints[i / 2] = Integer.parseUnsignedInt(rgb.substring(i, i + 2), 16);
        }

        return ints;
    }
}
