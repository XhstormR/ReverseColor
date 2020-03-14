package com.xhstormr.reversecolor;

import java.io.*;

public class ReverseColor {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: ReverseColor <in> <out>");
            return;
        }

        try (InputStream in = new BufferedInputStream(new FileInputStream(args[0]));
             OutputStream out = new BufferedOutputStream(new FileOutputStream(args[1]))) {
            final byte[] data = new byte[6];

            int i;
            while ((i = in.read()) != -1) {
                out.write(i);

/*
                // for notepad2-mod
                if (i == '#') {
                    in.read(data);

                    if (data[0] == ' ') {
                        out.write(data); // 不是 HEX Color
                    } else {
                        out.write(reverse(data));
                    }
                }
*/
                // for notepad-plus-plus
                if (i == '"') {
                    in.read(data);
                    i = in.read();

                    if (i == '"' && RGB.isHEXColor(new String(data))) {
                        out.write(reverse(data));
                    } else {
                        out.write(data); // 不是 HEX Color
                    }
                    out.write(i);
                }
            }
        }
    }

    private static byte[] reverse(byte[] data) {
        return RGB.reverse(new String(data)).getBytes(); // Charset.defaultCharset() => UTF-8
    }
}
