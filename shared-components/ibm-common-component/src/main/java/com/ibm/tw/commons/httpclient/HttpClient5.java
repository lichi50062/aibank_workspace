/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.ibm.tw.commons.httpclient;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.ChainElement;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.client5.http.ssl.TrustAllStrategy;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.ByteArrayEntity;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicHeader;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;
import org.apache.hc.core5.ssl.SSLContexts;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.JsonUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.ValidateParamUtils;

import io.micrometer.core.instrument.binder.httpcomponents.hc5.ObservationExecChainHandler;
import io.micrometer.observation.ObservationRegistry;

/**
 * <p>
 * 處理 HTTP Protocol 相關行為
 * </p>
 * <p>
 * Header attribute can see {@link org.apache.hc.core5.http.HttpHeaders}
 * </p>
 * 
 * @author Edward Tien
 */
public class HttpClient5 {

    private static final IBLog logger = IBLog.getLog(HttpClient5.class);

    private static final int DEFAULT_TIMEOUT = 60;

    private static ObservationRegistry observationRegistry = null;

    /**
     * 以 GET method 發送 HTTP 請求
     * 
     * @param urlString
     *            網址
     * @param headers
     *            標頭屬性
     * @param parameters
     *            參數
     * @param timeout
     *            逾時(秒)
     * @return
     * @throws ActionException
     */
    public static CustomHttpResponse doGet(String urlString, List<Header> headers, List<NameValuePair> parameters, int timeout) throws ActionException {

        urlString = ValidateParamUtils.validParam(urlString);
        HttpGet httpGet = new HttpGet(urlString);

        // 設置標頭屬性
        if (CollectionUtils.isNotEmpty(headers)) {
            for (Header header : headers) {
                httpGet.addHeader(header);
            }
        }

        // 設置參數
        if (CollectionUtils.isNotEmpty(parameters)) {
            // 增加參數到請求 URL 中
            try {
                URIBuilder uriBuilder = new URIBuilder(new URI(urlString));

                uriBuilder.addParameters(parameters);

                URI uri = uriBuilder.build();
                httpGet.setUri(uri);
            }
            catch (URISyntaxException e) {
                throw ExceptionUtils.getActionException(e);
            }
        }

        // 設置請求設定
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(timeout, TimeUnit.SECONDS).setResponseTimeout(timeout, TimeUnit.SECONDS).build();
        httpGet.setConfig(requestConfig);

        CustomHttpResponse response = null;

        CloseableHttpClient httpclient = getCloseableHttpClient(isHttps(urlString), requestConfig);

        try (httpclient) {
            response = httpclient.execute(httpGet, new CustomHttpClientResponseHandler());
        }
        catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw ExceptionUtils.getActionException(e);
        }
        return response;

    }

    /**
     * 以 GET method 發送 HTTP 請求
     * 
     * @param urlString
     *            網址
     * @param headers
     *            標頭屬性
     * @param parameters
     *            參數
     * @return {@link CustomHttpResponse}
     * @throws ActionException
     */
    public static CustomHttpResponse doGet(String urlString, List<Header> headers, List<NameValuePair> parameters) throws ActionException {
        return doGet(urlString, headers, parameters, DEFAULT_TIMEOUT);
    }

    /**
     * 以 GET method 發送 HTTP 請求
     * 
     * @param urlString
     *            網址
     * @param headers
     *            標頭屬性
     * @param parameters
     *            參數
     * @param timeout
     *            逾時(秒)
     * @return {@link CustomHttpResponse}
     * @throws ActionException
     */
    public static CustomHttpResponse doGet(String urlString, Map<String, String> headers, Map<String, String> parameters, int timeout) throws ActionException {
        return doGet(urlString, convert2HeaderList(headers), convert2NameValuePairList(parameters), timeout);
    }

    /**
     * 以 GET method 發送 HTTP 請求
     * 
     * @param urlString
     *            網址
     * @param headers
     *            標頭屬性
     * @param parameters
     *            參數
     * @return {@link CustomHttpResponse}
     * @throws ActionException
     */
    public static CustomHttpResponse doGet(String urlString, Map<String, String> headers, Map<String, String> parameters) throws ActionException {
        return doGet(urlString, convert2HeaderList(headers), convert2NameValuePairList(parameters), DEFAULT_TIMEOUT);
    }

    /**
     * 以 POST method 發送 HTTP 請求
     * 
     * @param urlString
     *            網址
     * @param headers
     *            標頭屬性
     * @param bodyText
     *            請求正文
     * @param timeout
     *            逾時(秒)
     * @return {@link CustomHttpResponse}
     * @throws ActionException
     */
    public static CustomHttpResponse doPost(String urlString, List<Header> headers, String bodyText, int timeout) throws ActionException {
        return doPost(urlString, headers, bodyText, timeout, timeout);
    }

    /**
     * 以 POST method 發送 HTTP 請求
     *
     * @param urlString
     *            網址
     * @param headers
     *            標頭屬性
     * @param bodyText
     *            請求正文
     * @param connTimeout
     *            連線逾時(秒)
     * @param socketTimeout
     *            連線逾時(秒)
     * @return {@link CustomHttpResponse}
     * @throws ActionException
     */
    public static CustomHttpResponse doPost(String urlString, List<Header> headers, String bodyText, int connTimeout, int socketTimeout) throws ActionException {

        HttpPost httpPost = new HttpPost(urlString);

        // 設置標頭屬性
        if (CollectionUtils.isNotEmpty(headers)) {
            for (Header header : headers) {
                httpPost.addHeader(header);
            }
        }

        // 設置 Request Body
        if (StringUtils.isNotBlank(bodyText)) {
            if (JsonUtils.isJson(bodyText)) {
                httpPost.setEntity(new StringEntity(bodyText, ContentType.APPLICATION_JSON));
            }
            else {
                httpPost.setEntity(new StringEntity(bodyText, ContentType.TEXT_PLAIN));
            }
        }

        // 設置設求設定
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(connTimeout, TimeUnit.SECONDS).setResponseTimeout(socketTimeout, TimeUnit.SECONDS).build();
        httpPost.setConfig(requestConfig);

        CustomHttpResponse response = null;

        CloseableHttpClient httpclient = getCloseableHttpClient(isHttps(urlString), requestConfig);

        try (httpclient) {
            response = httpclient.execute(httpPost, new CustomHttpClientResponseHandler());
        }
        catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw ExceptionUtils.getActionException(e);
        }
        return response;
    }

    /**
     * 以 POST method 發送 HTTP 請求
     *
     * @param urlString
     *            網址
     * @param headers
     *            標頭屬性
     * @param formParams
     *            請求正文:x-www-form-urlencoded 格式
     * @param connTimeout
     *            連線逾時(秒)
     * @param socketTimeout
     *            連線逾時(秒)
     * @return {@link CustomHttpResponse}
     * @throws ActionException
     */
    public static CustomHttpResponse doPost(String urlString, List<Header> headers, List<NameValuePair> formParams, int connTimeout, int socketTimeout) throws ActionException {

        HttpPost httpPost = new HttpPost(urlString);

        // 設置標頭屬性
        if (CollectionUtils.isNotEmpty(headers)) {
            for (Header header : headers) {
                httpPost.addHeader(header);
            }
        }

        // 設置 Request Body
        if (formParams != null && !formParams.isEmpty()) {
            // 创建一个 UrlEncodedFormEntity
            httpPost.setEntity(new UrlEncodedFormEntity(formParams));
        }

        // 設置設求設定
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(connTimeout, TimeUnit.SECONDS).setResponseTimeout(socketTimeout, TimeUnit.SECONDS).build();
        httpPost.setConfig(requestConfig);

        CustomHttpResponse response = null;

        CloseableHttpClient httpclient = getCloseableHttpClient(isHttps(urlString), requestConfig);

        try (httpclient) {
            response = httpclient.execute(httpPost, new CustomHttpClientResponseHandler());
        }
        catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw ExceptionUtils.getActionException(e);
        }
        return response;
    }

    /**
     * 以 POST method 發送 HTTP 請求
     * 
     * @param urlString
     *            網址
     * @param headers
     *            標頭屬性
     * @param bodyText
     *            請求正文
     * @return {@link CustomHttpResponse}
     * @throws ActionException
     */
    public static CustomHttpResponse doPost(String urlString, List<Header> headers, String bodyText) throws ActionException {
        return doPost(urlString, headers, bodyText, DEFAULT_TIMEOUT);
    }

    /**
     * 以 POST method 發送 HTTP 請求
     * 
     * @param urlString
     *            網址
     * @param headers
     *            標頭屬性
     * @param bodyText
     *            請求正文
     * @param timeout
     *            逾時(秒)
     * @return {@link CustomHttpResponse}
     * @throws ActionException
     */
    public static CustomHttpResponse doPost(String urlString, Map<String, String> headers, String bodyText, int timeout) throws ActionException {
        return doPost(urlString, convert2HeaderList(headers), bodyText, timeout);
    }

    /**
     * 以 POST method 發送 HTTP 請求
     * 
     * @param urlString
     *            網址
     * @param headers
     *            標頭屬性
     * @param bodyText
     *            請求正文
     * @return {@link CustomHttpResponse}
     * @throws ActionException
     */
    public static CustomHttpResponse doPost(String urlString, Map<String, String> headers, String bodyText) throws ActionException {
        return doPost(urlString, convert2HeaderList(headers), bodyText, DEFAULT_TIMEOUT);
    }

    /**
     * 以 POST method 發送 HTTP 請求
     * 
     * @param urlString
     *            網址
     * @param headers
     *            標頭屬性
     * @param formData
     *            表單資料
     * @param timeout
     *            逾時(秒)
     * @return {@link CustomHttpResponse}
     * @throws ActionException
     */
    public static CustomHttpResponse doPost(String urlString, List<Header> headers, Map<String, String> formData, int timeout) throws ActionException {

        HttpPost httpPost = new HttpPost(urlString);

        // 設置標頭屬性
        if (CollectionUtils.isNotEmpty(headers)) {
            for (Header header : headers) {
                httpPost.addHeader(header);
            }
        }

        // 設置 Request Body
        if (MapUtils.isNotEmpty(formData)) {
            httpPost.setEntity(new UrlEncodedFormEntity(convert2NameValuePairList(formData)));
        }

        // 設置設求設定
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(timeout, TimeUnit.SECONDS).setResponseTimeout(timeout, TimeUnit.SECONDS).build();
        httpPost.setConfig(requestConfig);

        CustomHttpResponse response = null;

        CloseableHttpClient httpclient = getCloseableHttpClient(isHttps(urlString), requestConfig);

        try (httpclient) {
            response = httpclient.execute(httpPost, new CustomHttpClientResponseHandler());
        }
        catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw ExceptionUtils.getActionException(e);
        }
        return response;
    }

    private static CloseableHttpClient getCloseableHttpClient(boolean isHTTPS, RequestConfig requestConfig) throws ActionException {
        try {
            HttpClientBuilder httpClientBuilder = HttpClients.custom().setDefaultRequestConfig(requestConfig);
            if (observationRegistry != null) {
                httpClientBuilder.addExecInterceptorAfter(ChainElement.RETRY.name(), "micrometer", new ObservationExecChainHandler(observationRegistry));
            }
            if (isHTTPS) {
                final SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustAllStrategy()).build();

                final SSLConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactoryBuilder.create().setSslContext(sslContext).setHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();

                final HttpClientConnectionManager cm = PoolingHttpClientConnectionManagerBuilder.create().setSSLSocketFactory(sslSocketFactory).build();

                return httpClientBuilder.setConnectionManager(cm).evictExpiredConnections().build();
            }
            else {
                return httpClientBuilder.build();
            }
        }
        catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
            throw ExceptionUtils.getActionException(e);
        }
    }

    /**
     * 將標頭(Map)轉換成 List<{@link Header}>
     * 
     * @param headers
     * @return
     */
    public static List<Header> convert2HeaderList(Map<String, String> headers) {
        List<Header> headerList = new ArrayList<>();
        if (MapUtils.isNotEmpty(headers)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                headerList.add(new BasicHeader(entry.getKey(), entry.getValue()));
            }
        }
        return headerList;
    }

    /**
     * 將參數(Map)轉換成 List<{@link NameValuePair}>
     * 
     * @param parameters
     * @return
     */
    public static List<NameValuePair> convert2NameValuePairList(Map<String, String> parameters) {
        List<NameValuePair> nvps = new ArrayList<>();
        if (MapUtils.isNotEmpty(parameters)) {
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        return nvps;
    }

    /**
     * 是否為 HTTPS protocol
     * 
     * @param urlString
     * @return
     * @throws ActionException
     */
    public static boolean isHttps(String urlString) throws ActionException {
        try {
            return isHttps(new URL(ValidateParamUtils.validParam(urlString)));
        }
        catch (MalformedURLException e) {
            logger.error("傳入的網址字串不是合法格式。");
            throw ExceptionUtils.getActionException(CommonErrorCode.UNKNOWN_EXCEPTION);
        }
    }

    /**
     * 是否為 HTTPS protocol
     * 
     * @param url
     * @return
     */
    public static boolean isHttps(URL url) {
        return url != null && StringUtils.equalsIgnoreCase(url.getProtocol(), "https");
    }

    public static CustomHttpResponse doPost(String url, List<Header> headers, ByteArrayEntity byteArrayEntity, int connTimeout, int socketTimeout) throws ActionException {
        HttpPost httpPost = new HttpPost(url);

        // 設置標頭屬性
        if (CollectionUtils.isNotEmpty(headers)) {
            for (Header header : headers) {
                httpPost.addHeader(header);
            }
        }

        httpPost.setEntity(byteArrayEntity);

        // 設置設求設定
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(connTimeout, TimeUnit.SECONDS).setResponseTimeout(socketTimeout, TimeUnit.SECONDS).build();
        httpPost.setConfig(requestConfig);

        CustomHttpResponse response = null;

        CloseableHttpClient httpclient = getCloseableHttpClient(isHttps(url), requestConfig);

        try (httpclient) {
            response = httpclient.execute(httpPost, new CustomHttpClientResponseHandler());
        }
        catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw ExceptionUtils.getActionException(e);
        }
        return response;
    }

    public static CloseableHttpResponse doGetFile(String urlString) throws ActionException {
        HttpGet httpGet = new HttpGet(urlString);
        // 設置請求設定
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(10000, TimeUnit.SECONDS).setResponseTimeout(10000, TimeUnit.SECONDS).build();
        httpGet.setConfig(requestConfig);
        CloseableHttpClient httpClient = getCloseableHttpClient(isHttps(urlString), requestConfig);

        try {
            return httpClient.execute(httpGet);
        }
        catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw ExceptionUtils.getActionException(e);
        }
    }

    public static void setObservationRegistry(ObservationRegistry observationRegistry) {
        HttpClient5.observationRegistry = observationRegistry;
    }
}
