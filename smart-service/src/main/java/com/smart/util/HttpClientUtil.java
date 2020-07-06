package com.smart.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Http 请求工具类
 *
 * @author Kong, created on 2020-06-12T14:02.
 * @since 1.0.0-SNAPSHOT
 */
@Log4j2
public class HttpClientUtil {

    private static PoolingHttpClientConnectionManager cm;
    private static final String EMPTY_STR = "";
    private static final String UTF_8 = "UTF-8";
    private static SSLConnectionSocketFactory sslsf = null;
    private static SSLContextBuilder builder = null;
    private static final String HTTP = "http";
    private static final String HTTPS = "https";

    public static final String APPLICATION_JSON = "application/json";
    public static final String APPLICATION_URLENCODED = "application/x-www-form-urlencoded";

    /**
     * 通过连接池获取HttpClient
     *
     * @return
     */
    private static CloseableHttpClient getHttpClient() throws Exception {
        // 创建Http请求配置参数
        RequestConfig requestConfig = RequestConfig.custom()
                // 获取连接超时时间
                .setConnectionRequestTimeout(5000)
                // 请求超时时间
                .setConnectTimeout(5000)
                // 响应超时时间
                .setSocketTimeout(8000)
                .build();
        // 为防止因HTTPS证书认证失败造成API调用失败,需要先忽略证书信任问题
        CloseableHttpClient client = HttpClients.custom()
                .setSSLContext(new SSLContextBuilder().loadTrustMaterial(null,
                        (x509CertChain, authType) -> true).build())
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .setDefaultRequestConfig(requestConfig)
                .build();
        return client;
    }

    public static String httpGetRequest(String url) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        return getResult(httpGet);
    }

    public static String httpGetRequest(String url, Map<String, Object> params) throws Exception {
        URIBuilder ub = new URIBuilder(url);
        if (null != params) {
            List<NameValuePair> pairs = covertParams2NVPS(params);
            ub.setParameters(pairs);
        }

        HttpGet httpGet = new HttpGet(ub.build());
        return getResult(httpGet);
    }

    public static String httpGetRequest(String url, Map<String, Object> headers, Map<String, Object> params)
            throws Exception {
        URIBuilder ub = new URIBuilder(url);
        if (null != params) {
            List<NameValuePair> pairs = covertParams2NVPS(params);
            ub.setParameters(pairs);
        }

        HttpGet httpGet = new HttpGet(ub.build());
        if (null != headers) {
            for (Map.Entry<String, Object> param : headers.entrySet()) {
                httpGet.addHeader(param.getKey(), String.valueOf(param.getValue()));
            }
        }
        return getResult(httpGet);
    }

    public static String httpPostRequest(String url) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        return getResult(httpPost);
    }


    public static String httpPostRequest(String url, Map<String, Object> params) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        if (null != params) {
            List<NameValuePair> pairs = covertParams2NVPS(params);
            httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
        }
        return getResult(httpPost);
    }

    public static String httpPostRequest(String url, Map<String, Object> headers, Map<String, Object> params, String contentType)
            throws Exception {
        String jsonStr = JSON.toJSONString(params);
        return httpPostRequest(url, headers, jsonStr, contentType);
    }

    public static String httpPostRequest(String url, Map<String, Object> headers, String jsonParams)
            throws Exception {
        return httpPostRequest(url, headers, jsonParams, APPLICATION_JSON);
    }


    public static String httpPostRequest(String url, Map<String, Object> headers, String jsonParams, String contentType)
            throws Exception {
        HttpPost httpPost = new HttpPost(url);

        if (null != headers) {
            for (Map.Entry<String, Object> param : headers.entrySet()) {
                httpPost.addHeader(param.getKey(), String.valueOf(param.getValue()));
            }
        }
        if (StringUtil.isNoneBlank(jsonParams)) {
            StringEntity stringEntity = new StringEntity(jsonParams, UTF_8);
            stringEntity.setContentEncoding(UTF_8);
            stringEntity.setContentType(contentType);
            httpPost.setEntity(stringEntity);
        }
        return getResult(httpPost);
    }

    public static String httpPostRequest(String url, Map<String, Object> headers, Map<String, Object> params) throws Exception {
        return httpPostRequest(url, headers, params, APPLICATION_JSON);
    }

    public static String httpPostRequest(String url, String jsonStr) throws Exception {
        return httpPostRequest(url, jsonStr, APPLICATION_JSON);
    }

    public static String httpPostRequest(String url, String jsonStr, String contentType) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(jsonStr, UTF_8);
        stringEntity.setContentEncoding(UTF_8);
        stringEntity.setContentType(contentType);
        httpPost.setEntity(stringEntity);
        return getResult(httpPost);
    }

    /**
     * 处理Http请求
     *
     * @param request
     * @return
     */
    private static String getResult(HttpRequestBase request) throws Exception {
        CloseableHttpClient httpClient = getHttpClient();
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
            if (null != response) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    return EntityUtils.toString(entity);
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            httpClient.close();
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                    response.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
        return EMPTY_STR;
    }

    private static List<NameValuePair> covertParams2NVPS(Map<String, Object> params) {
        List<NameValuePair> pairs = new ArrayList<>();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            pairs.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
        }
        return pairs;
    }
}
