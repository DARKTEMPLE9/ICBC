package net.northking.iacmp.utils.http;

/**
 * @Author: wei.chen
 * @Date Created: in 2019-12-2 17:31
 */

import java.io.IOException;
import java.io.InputStream;

public class ByteWrapper {
    private byte[] bytes = new byte[2048];
    private int length = 0;
    private int readDataLength = 0;

    public ByteWrapper() {
    }

    public ByteWrapper(byte[] bytes, int length) {
        this.bytes = bytes;
        this.length = length;
    }

    public String getStrLine() {
        return this.bytes == null ? null : new String(this.bytes, 0, this.length);
    }

    public int getReadDataLength() {
        return this.readDataLength;
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    public int length() {
        return this.length;
    }

    public void readLine(InputStream in) throws IOException {
        int index = 0;
        int iReturn = 0;
        byte[] lineBytes = this.bytes;
        long t1 = System.currentTimeMillis();

        while (index < 2048) {
            long t2 = System.currentTimeMillis();
            int len = in.read(lineBytes, index, 1);
            if (len == 1 && lineBytes[index++] == 13) {
                ++iReturn;
                len = in.read(lineBytes, index, 1);
                if (len == 1 && lineBytes[index++] == 10) {
                    ++iReturn;
                    break;
                }
            }

            if (t2 - t1 > 10000L) {
                break;
            }
        }

        this.readDataLength = index;
        this.length = index - iReturn;
    }
}

