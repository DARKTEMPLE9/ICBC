package net.northking.iacmp.http.chunk.util;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;

/**
 * http连接池
 *
 * @Author: wei.chen
 * @Version: 1.0
 * @Date Created: 2019-08-26
 */
public class HttpChunkClientPool {
    private static PoolingHttpClientConnectionManager cm = null;
    private static CloseableHttpClient httpClient = null;
    static final int timeOut = 1000 * 120;
    private static final Object syncLock = new Object();

    public static void config(HttpRequestBase httpRequestBase) {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(timeOut)
                .setConnectTimeout(timeOut)
                .setSocketTimeout(timeOut).build();
        httpRequestBase.setConfig(requestConfig);
    }

    public synchronized static CloseableHttpClient getHttpClient(String url) {
        String hostname = url.split("/")[2];
        int port = 80;
        if (hostname.contains(":")) {
            String[] arr = hostname.split(":");
            hostname = arr[0];
            port = Integer.parseInt(arr[1]);
        }
        if (httpClient == null) {
            httpClient = createHttpClient(200, 200, 100, hostname, port);
        }
        return httpClient;
    }

    public static CloseableHttpClient createHttpClient(
            int maxTotal, int maxPerRoute, int maxRoute, String hostname, int port) {
        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
        LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();

        Registry registry = RegistryBuilder.create().register("http", plainsf)
                .register("https", sslsf)
                .build();
        cm = new PoolingHttpClientConnectionManager(registry);

        cm.setMaxTotal(maxTotal);

        cm.setDefaultMaxPerRoute(maxPerRoute);
        //System.out.println(hostname + port);
        HttpHost httpHost = new HttpHost(hostname, port);

        cm.setMaxPerRoute(new HttpRoute(httpHost), maxRoute);

        HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                if (executionCount >= 5) {
                    return false;
                }
                if ((exception instanceof NoHttpResponseException)) {
                    return true;
                }
                if ((exception instanceof SSLHandshakeException)) {
                    return false;
                }
                if ((exception instanceof InterruptedIOException)) {
                    return false;
                }
                if ((exception instanceof UnknownHostException)) {
                    return false;
                }
                if ((exception instanceof ConnectTimeoutException)) {
                    return false;
                }
                if ((exception instanceof SSLException)) {
                    return false;
                }

                HttpClientContext clientContext = HttpClientContext.adapt(context);

                HttpRequest request = clientContext.getRequest();

                if (!(request instanceof HttpEntityEnclosingRequest)) {
                    return true;
                }
                return false;
            }
        };
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setRetryHandler(httpRequestRetryHandler)
                .build();

        return httpClient;
    }
}