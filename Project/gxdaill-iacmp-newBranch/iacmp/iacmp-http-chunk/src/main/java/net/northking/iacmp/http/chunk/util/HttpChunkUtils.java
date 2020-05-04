package net.northking.iacmp.http.chunk.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 通用http发送方法
 *
 * @author wxy
 */
public class HttpChunkUtils {
    private static final Logger log = LoggerFactory.getLogger(HttpChunkUtils.class);

    /**
     * 建立http连接
     *
     * @param url     连接url
     * @param jsonStr json串
     * @return 结果
     * @throws IOException
     */
    public static String sendHttpJson(String url, String jsonStr) throws IOException {
        log.info("Sending post [{}] to [{}]...", jsonStr, url);

        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/json;charset=UTF-8");
        StringEntity reqEntity = new StringEntity(jsonStr, "UTF-8");
        reqEntity.setContentType("text/json");
        post.setEntity(reqEntity);
        HttpChunkClientPool.config(post);
        HttpEntity entity;
        try {
            CloseableHttpClient httpClient = HttpChunkClientPool.getHttpClient(url);
            CloseableHttpResponse response = httpClient.execute(post, HttpClientContext.create());
            entity = response.getEntity();
        } catch (IOException e) {
            throw new IOException(e.getMessage(), e);
        } finally {

        }

        return EntityUtils.toString(entity, "UTF-8");
    }
}