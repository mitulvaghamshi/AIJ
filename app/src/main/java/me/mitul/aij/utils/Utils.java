package me.mitul.aij.utils;

import java.io.InputStream;
import java.io.OutputStream;

public class Utils {
    public static void CopyStream(InputStream iStream, OutputStream oStream) {
        final int bufferSize = 1024;
        try {
            byte[] bytes = new byte[bufferSize];
            while (true) {
                int count = iStream.read(bytes, 0, bufferSize);
                if (count == -1) break;
                oStream.write(bytes, 0, count);
            }
        } catch (Exception ignored) {
        }
    }
}
