package com.xhstormr.reversecolor;

import java.io.*;

public class ReverseColor {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: ReverseColor <in> <out>");
            return;
        }

        try (InputStream in = new FileInputStream(args[0]);
             OutputStream out = new FileOutputStream(args[1])) {
            final byte[] data = new byte[6];

            int i;
            while ((i = in.read()) != -1) {
                out.write(i);

                if (i == '#') {
                    in.read(data);

                    if (data[0] == ' ') {
                        out.write(data); // 不是 HEX Color
                    } else {
                        out.write(reverse(data));
                    }
                }
            }
        }
    }

    private static byte[] reverse(byte[] data) {
        final int[] rgb = RGB.HEX2RGB(new String(data));

        for (int i = 0; i < rgb.length; i++) {
            rgb[i] = 255 - rgb[i];
        }

        return RGB.RGB2HEX(rgb).getBytes(); // Charset.defaultCharset() => UTF-8
    }
}
