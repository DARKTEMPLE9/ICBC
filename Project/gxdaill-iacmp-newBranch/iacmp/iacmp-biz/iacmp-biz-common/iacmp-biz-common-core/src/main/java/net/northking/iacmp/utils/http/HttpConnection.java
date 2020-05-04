package net.northking.iacmp.utils.http;

/**
 * @Author: wei.chen
 * @Date Created: in 2019-12-2 17:33
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class HttpConnection {
    private static final int BYTESSIZE = 512;
    private byte[] bytes = new byte[512];
    private int responseCode = 0;
    private long contentLength = 0L;
    private String myFiletype = null;
    private String location = null;
    private boolean connectionOpened = false;
    private String exception = null;
    private long openTime = 0L;
    private SocketProvider socketProvider = null;
    private static final String HTTP = " HTTP/1.1\r\n";
    private static final String HOST = "Host: ";
    private static final String CONTENT = "Content-Length: ";

    public boolean isConnectionOpened() {
        return this.connectionOpened;
    }

    public String getException() {
        return this.exception;
    }

    public HttpConnection() {
    }

    public HttpConnection(SocketProvider socketProvider) {
        this.socketProvider = socketProvider;
    }

    public void openConnection() throws IOException {
        if (this.socketProvider != null && !this.connectionOpened) {
            this.socketProvider.openConnection();
            this.connectionOpened = true;
            this.openTime = System.currentTimeMillis();
        } else {
            throw new IOException("No Socket Connection Exist!");
        }
    }

    public void closeConnection() throws IOException {
        if (this.socketProvider != null) {
            this.socketProvider.close();
            this.connectionOpened = false;
        } else {
            throw new IOException("No Socket Connection Exist!");
        }
    }

    public int getResponseCode() throws IOException {
        if (this.responseCode != -1) {
            return this.responseCode;
        } else {
            this.exception = null;
            this.responseCode = -1;
            this.contentLength = 0L;

            while (true) {
                ByteWrapper readLineByte = this.socketProvider.readLine();
                String line = readLineByte.getStrLine();
                if (line.trim().length() == 0) {
                    return this.responseCode;
                }

                String[] values;
                if (line.startsWith("HTTP/1")) {
                    values = line.split(" ");
                    if (values.length >= 2) {
                        this.responseCode = Integer.parseInt(values[1]);
                        if (this.responseCode != 200) {
                            this.exception = readLineByte.getStrLine();
                        }
                    }
                }

                values = line.trim().split(": ");
                if (values.length == 2) {
                    if ("Content-Length".equals(values[0])) {
                        this.contentLength = (long) Integer.parseInt(values[1]);
                    } else if ("My-Filetype".equals(values[0])) {
                        this.myFiletype = values[1].toLowerCase();
                    } else if ("Location".equals(values[0])) {
                        this.location = values[1];
                    } else if ("Exception".equals(values[0])) {
                        this.exception = readLineByte.getStrLine();
                    }
                }
            }
        }
    }

    public long getResponseText2OutputStream(OutputStream out, long flushSize) throws IOException {
        int bufferSizeInner = 512;
        if (512 < bufferSizeInner) {
            this.bytes = null;
            this.bytes = new byte[bufferSizeInner];
        } else {
            bufferSizeInner = 512;
        }

        int readSumBytes = 0;
        if (this.contentLength > 0L) {
            int tmpReadLen = 0;

            int readLen;
            for (boolean var7 = false; (long) readSumBytes < this.contentLength; readSumBytes += readLen) {
                readLen = this.socketProvider.read(this.bytes, 0, bufferSizeInner);
                if (readLen == -1 || readLen == 0) {
                    break;
                }

                out.write(this.bytes, 0, readLen);
                tmpReadLen += readLen;
                if ((long) tmpReadLen > flushSize) {
                    tmpReadLen = 0;
                    out.flush();
                }
            }

            out.flush();
        } else if (this.contentLength == 0L) {
            throw new IOException("服务器端没有返回 Content-Length,目前系统还无法处理!");
        }

        return (long) readSumBytes;
    }

    public static String bytes2HexString(byte[] b, int off, int len) {
        StringBuilder sb = new StringBuilder();

        for (int i = off; i < len; ++i) {
            String s = Integer.toHexString(b[i] & 255);
            if (s.length() == 1) {
                sb.append('0');
            }

            sb.append(s);
        }

        return sb.toString();
    }

    public long putData(InputStream in, int readBufferSize, int flushBufferSize) throws IOException {
        byte[] subBytes = new byte[readBufferSize];
        //int readLen = false;
        int writeLen = 0;
        long fileLen = 0L;

        int readLen;
        while ((readLen = in.read(subBytes, 0, readBufferSize)) >= 0) {
            this.socketProvider.write(subBytes, 0, readLen);
            writeLen += readLen;
            fileLen += (long) readLen;
            if (writeLen >= flushBufferSize) {
                writeLen = 0;
                this.socketProvider.flush();
            }
        }

        this.socketProvider.flush();
        return fileLen;
    }

    public void putData(byte[] bytes) throws IOException {
        this.socketProvider.write(bytes, 0, bytes.length);
        this.socketProvider.flush();
    }

    public ByteWrapper getResponseText2Buffer(int bufferSize) throws IOException {
        int readSumBytes = 0;
        byte[] retBytes = null;
        if (this.contentLength > 0L) {
            //int readLen = false;

            int readLen;
            for (retBytes = new byte[(int) this.contentLength]; (long) readSumBytes < this.contentLength; readSumBytes += readLen) {
                readLen = this.socketProvider.read(this.bytes, readSumBytes, bufferSize);
                if (readLen == -1 || readLen == 0) {
                    break;
                }
            }
        }

        return new ByteWrapper(retBytes, readSumBytes);
    }

    public void openGetURL(URL url) throws IOException {
        StringBuilder buff = new StringBuilder();
        buff.append("GET ");
        buff.append(url.getFile());
        buff.append(" HTTP/1.0\r\nHost: ");
        buff.append(url.getHost()).append(":").append(url.getPort());
        buff.append("\r\nAccept: */*;\r\nAccept-Encoding: gzip,deflate\r\n");
        buff.append("Keep-Alive: 30000\r\nConnection: keep-alive\r\n\r\n");
        buff.append("\r\n");
        this.responseCode = -1;
        this.socketProvider.write(buff.toString().getBytes("UTF-8"));
    }

    public void openGetURL(URL url, long contentLength) throws IOException {
        StringBuilder buff = new StringBuilder();
        buff.append("POST ");
        buff.append(url.getFile());
        buff.append(" HTTP/1.1\r\n");
        buff.append("Host: " + url.getHost()).append("\r\nPort: ").append(url.getPort() + "\r\n");
        buff.append("Content-Length: " + contentLength + "\r\n");
        buff.append("\r\nAccept: */*;\r\nAccept-Encoding: gzip,deflate\r\n");
        buff.append("Keep-Alive: 30000\r\nConnection: keep-alive\r\n\r\n");
        buff.append("\r\n");
        this.responseCode = -1;
        this.socketProvider.write(buff.toString().getBytes("UTF-8"));
    }

    public void openPutURL(URL url) throws IOException {
        StringBuilder buff = new StringBuilder();
        buff.append("PUT ");
        buff.append(url.getFile());
        buff.append(" HTTP/1.1\r\n");
        buff.append("Host: " + url.getHost()).append(":").append(url.getPort());
        buff.append("\r\nAccept: */*;\r\nAccept-Encoding: gzip,deflate\r\n");
        buff.append("Keep-Alive: 30000\r\nConnection: keep-alive\r\n\r\n");
        buff.append("Content-Length: ").append((buff.length() + Integer.toString(4)).length() + buff.length()).append("\r\n");
        buff.append("\r\n");
        this.responseCode = -1;
        this.socketProvider.write(buff.toString().getBytes("UTF-8"));
    }

    public void openPutURL(URL url, long contentLength) throws IOException {
        this.contentLength = contentLength;
        StringBuilder buff = new StringBuilder();
        buff.append("POST ");
        buff.append(url.getFile() + "&Content-Length=" + contentLength);
        buff.append(" HTTP/1.1\r\n");
        buff.append("Content-Length: " + contentLength + "\r\n");
        buff.append("Host: " + url.getHost()).append(":").append(url.getPort());
        buff.append("\r\nAccept: */*;\r\nAccept-Encoding: gzip,deflate\r\n");
        buff.append("Keep-Alive: 30000\r\nConnection: keep-alive\r\n\r\n");
        //buff.append("\r\n");
        this.responseCode = -1;
        this.socketProvider.write(buff.toString().getBytes("UTF-8"));
    }

    public long getContentLength() {
        return this.contentLength;
    }

    public String getHost() {
        return this.socketProvider.getSrvHost();
    }

    public int getPort() {
        return this.socketProvider.getPort();
    }

    public String getResponseText() throws IOException {
        byte[] subBytes = new byte[1024];
        StringBuilder sb = new StringBuilder();
        //int readlen = false;

        int readlen;
        for (int total = 0; (long) total < this.contentLength && (readlen = this.socketProvider.read(subBytes, 0, 1024)) != -1; total += readlen) {
            sb.append(new String(subBytes, 0, readlen, "UTF-8"));
        }

        return sb.toString();
    }

    public byte[] getResponseBytes() throws IOException {
        int len = (int) this.contentLength;
        byte[] subBytes = new byte[len];
        //int read = false;

        int read;
        for (int total = 0; total < len && (read = this.socketProvider.read(subBytes, total, len - total)) != -1; total += read) {
            ;
        }

        return subBytes;
    }

    public ByteWrapper getResponseLine() throws IOException {
        return this.socketProvider.readLine();
    }

    public void reopenConnection() throws IOException {
        this.closeConnection();
        this.openConnection();
    }

    public long getOpenTime() {
        return this.openTime;
    }

    public String getMyFileType() {
        return this.myFiletype;
    }

    public String getLocation() {
        return this.location;
    }
}

