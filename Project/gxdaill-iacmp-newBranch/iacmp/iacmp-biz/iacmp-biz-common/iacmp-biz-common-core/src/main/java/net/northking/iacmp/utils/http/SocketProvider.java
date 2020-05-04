package net.northking.iacmp.utils.http;

/**
 * @Author: wei.chen
 * @Date Created: in 2019-12-2 17:35
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketProvider {
    private Socket sock = null;
    private String srvHost;
    private int port = 0;
    private OutputStream out = null;
    private InputStream in = null;

    public SocketProvider(int port, String srvHost) {
        this.port = port;
        this.srvHost = srvHost;
    }

    public void openConnection() throws IOException {
        this.sock = new Socket();
        this.sock.connect(new InetSocketAddress(this.srvHost, this.port));
        this.out = this.sock.getOutputStream();
        this.in = this.sock.getInputStream();
    }

    public ByteWrapper readLine() throws IOException {
        ByteWrapper byteWrapper = new ByteWrapper();
        byteWrapper.readLine(this.in);
        return byteWrapper;
    }

    public int read(byte[] bytes, int offset, int len) throws IOException {
        return this.in.read(bytes, offset, len);
    }

    public void write(byte[] bytes) throws IOException {
        this.out.write(bytes);
    }

    public void write(byte[] bytes, int offset, int len) throws IOException {
        this.out.write(bytes, offset, len);
    }

    public void flush() throws IOException {
        this.out.flush();
    }

    public void close() throws IOException {
        String errDes = "";

        try {
            this.in.close();
        } catch (IOException var5) {
            errDes = var5.getMessage();
        }

        try {
            this.out.close();
        } catch (IOException var4) {
            errDes = var4.getMessage();
        }

        try {
            this.sock.close();
        } catch (IOException var3) {
            errDes = var3.getMessage();
        }

        this.sock = null;
        this.in = null;
        this.out = null;
        if (errDes.length() > 0) {
            throw new IOException(errDes);
        }
    }

    public Socket getSock() {
        return this.sock;
    }

    public void setSock(Socket sock) {
        this.sock = sock;
    }

    public String getSrvHost() {
        return this.srvHost;
    }

    public void setSrvHost(String srvHost) {
        this.srvHost = srvHost;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public OutputStream getOut() {
        return this.out;
    }

    public InputStream getIn() {
        return this.in;
    }
}

