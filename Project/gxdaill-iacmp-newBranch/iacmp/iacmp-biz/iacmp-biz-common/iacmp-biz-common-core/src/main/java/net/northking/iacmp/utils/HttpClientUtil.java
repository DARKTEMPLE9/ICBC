package net.northking.iacmp.utils;

import net.northking.iacmp.execption.HttpTransException;
import net.northking.iacmp.utils.http.HttpClientPool;
import net.northking.iacmp.utils.http.HttpConnection;
import net.northking.iacmp.utils.http.SocketProvider;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * http连接工具类
 *
 * @Author: wei.chen
 * @Version: 1.0
 * @Date Created: 2019-08-26
 */
public class HttpClientUtil {
    private static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * 建立http连接
     *
     * @param host      连接host
     * @param port      连接端口
     * @param url       连接url
     * @param lFilePath 本地文件路径
     * @param lFilename 本地文件名
     * @return 结果
     * @throws Exception
     */
    public static String sendByHttp(
            String host, int port, String url, String lFilePath, String lFilename) throws Exception {
        //int bufferSize, int flushSize, int reTryCount) throws Exception{
        String x = "";
        log.info("sendByHttp方法");
        log.info(" host=" + host);
        log.info(" port=" + port);
        log.info(" url=" + url);
        log.info(" lFilePath=" + lFilePath);
        log.info(" lFilename=" + lFilename);

//        CloseableHttpResponse response = null;
        HttpPost post = new HttpPost("http://" + host + ":" + port + url);
        HttpClientPool.config(post);
//        response = HttpClientPool.getHttpClient("http://" + host + ":" + port + url).execute(post,
//                HttpClientContext.create());
        try (CloseableHttpResponse response = HttpClientPool.getHttpClient("http://" + host + ":" + port + url).execute(post,
                HttpClientContext.create())) {
            HttpEntity entity = response.getEntity();
            if (url.indexOf("download") > 0) {
                InputStream inputStream = entity.getContent();
                OutputStream out = null;
                try {
                    byte[] buff = new byte[1024];
                    File file = new File(lFilePath + File.separator + lFilename);
                    File fileParent = file.getParentFile();
                    if (fileParent != null && !fileParent.exists()) {
                        fileParent.mkdirs();
                    }
                    out = new FileOutputStream(file);
                    int length = 0;
                    while ((length = inputStream.read(buff, 0, 1024)) != -1) {
                        out.write(buff, 0, length);
                    }
                } catch (IOException e) {
                    throw new HttpTransException("server exception: " + e);
                } finally {
                    if (null != out) {
                        out.close();
                    }
                    if (response != null) {
                        response.close();
                    }
                }
            } else if (url.indexOf("flushInitParm") > 0) {
                String result = EntityUtils.toString(entity, "utf-8");
                EntityUtils.consume(entity);
                if (result.indexOf("操作成功！！！") > -1) {
                    return "操作成功！！！";
                } else {
                    return "操作失败";
                }
            } else {
                String result = EntityUtils.toString(entity, "utf-8");
                EntityUtils.consume(entity);

                if (result.indexOf("OK-upload\r\n") > -1 && result.indexOf("FilePath=") > -1 && result.indexOf("ReceiveFileLen=") > -1) {
                    log.info("上传成功");
                } else {
//                    if (response != null) {
//                        response.close();
//                    }
                    throw new HttpTransException("server exception: " + result);
                }
            }
        } catch (IOException e) {
            throw new HttpTransException("server exception: " + e);
        }
        return x;
    }

    public static void sendByHttp(
            String host, int port, String url, String lFilePath, String lFilename,
            int bufferSize, int flushSize, int reTryCount) throws Exception {

        sendByHttp(host, port, url, lFilePath, lFilename);
    }

    /**
     * 建立http连接
     *
     * @param host 连接host
     * @param port 连接端口
     * @param url  连接url
     * @return 结果
     * @throws Exception
     */
    public static long sendByHttp(
            String host, int port, String url, InputStream in,
            int bufferSize, int flushSize) throws HttpTransException, IOException {
        log.info("sendByHttp方法");
        log.info(" host=" + host);
        log.info(" port=" + port);
        log.info(" url=" + url);
        log.info(" in=" + in);
        log.info(" bufferSize=" + bufferSize);
        log.info(" flushSize=" + flushSize);

        HttpConnection httpConnection = null;
        long readFileLength = 0L;

        try {
            httpConnection = new HttpConnection(new SocketProvider(port, host));
            int fileRealLength = in.available();
            httpConnection.openConnection();
            log.info("-------------开始开启一个http请求--------------");
            httpConnection.openPutURL(new URL("http://" + host + ":" + port + url), (long) fileRealLength);
            readFileLength = httpConnection.putData(in, bufferSize, flushSize);
            log.info("---------------------------");
            if ((long) fileRealLength != readFileLength) {
                throw new HttpTransException("Trans data Error!");
            }

            int responseCode = httpConnection.getResponseCode();
            log.info("Response Code:" + responseCode);
            if (responseCode != 200 && responseCode != -1) {
                log.info("Response Code:" + responseCode + " Description:" + httpConnection.getException());
                throw new HttpTransException("Response Code:" + responseCode + " Description:" + httpConnection.getException());
            }

            if (httpConnection.getException() != null) {
                throw new HttpTransException(httpConnection.getException());
            }

            String s = httpConnection.getResponseText();
            if (s.indexOf("OK-upload\r\n") <= -1) {
                throw new HttpTransException("server exception: " + s);
            }

            int indexStart = s.indexOf("ReceiveFileLen=") + "ReceiveFileLen=".length();
            int indexEnd = s.indexOf("\r\n", indexStart);
            if (s.indexOf("ReceiveFileLen=") <= -1) {
                throw new HttpTransException("server receive length not equal file length");
            }
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Throwable var23) {
                    ;
                }
            }

            if (httpConnection != null && httpConnection.isConnectionOpened()) {
                try {
                    httpConnection.closeConnection();
                } catch (Throwable var22) {
                    ;
                }
            }

        }

        return readFileLength;
    }


    /**
     * 建立http连接
     *
     * @param host 连接host
     * @param port 连接端口
     * @param url  连接url
     * @return 结果
     * @throws Exception
     */
    public static long sendByHttp(String host, int port, String url, OutputStream out, int bufferSize, int flushSize) throws IOException, HttpTransException {
        log.info("sendByHttp方法");
        log.info(" host=" + host);
        log.info(" port=" + port);
        log.info(" url=" + url);
        log.info(" out=" + out);
        log.info(" bufferSize=" + bufferSize);
        log.info(" flushSize=" + flushSize);

        long readFileLength = 0L;
        HttpConnection httpConnection = new HttpConnection(new SocketProvider(port, host));
        httpConnection.openConnection();
        httpConnection.openGetURL(new URL("http://" + host + ":" + port + url));
        int responseCode = httpConnection.getResponseCode();
        if (responseCode == 200) {
            if (httpConnection.getException() != null) {
                httpConnection.closeConnection();
                throw new HttpTransException(httpConnection.getException());
            } else {
                readFileLength = httpConnection.getResponseText2OutputStream(out, (long) flushSize);
                if (readFileLength != httpConnection.getContentLength()) {
                    httpConnection.closeConnection();
                    throw new HttpTransException("Trans data Error: Trans " + readFileLength + " not equal " + httpConnection.getContentLength() + " or equal 0!");
                } else {
                    return readFileLength;
                }
            }
        } else if (responseCode == 302) {
            URL location = new URL(httpConnection.getLocation());
            httpConnection.closeConnection();
            return sendByHttp(location.getHost(), location.getPort(), location.getFile(), out, bufferSize, flushSize);
        } else {
            httpConnection.closeConnection();
            throw new HttpTransException("Response Code:" + responseCode);
        }
    }
}
