package com.caipiao.utils.http;


import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author liujie
 */
public class HttpClientWrapper {

    private static final Logger LOG = LoggerFactory.getLogger(HttpClientWrapper.class);

    private static final HttpClient HTTP_CLIENT;

    public static final int MAX_CONNECTIONS_PER_HOST = 100;

    static {
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();

        connectionManager.getParams().setMaxConnectionsPerHost(
                HostConfiguration.ANY_HOST_CONFIGURATION, MAX_CONNECTIONS_PER_HOST);

        HTTP_CLIENT = new HttpClient(connectionManager);
    }

    /**
     * 发送 HTTP 请求
     *
     * @param url        地址
     * @param parameters 参数
     * @param post       是否 POST
     *
     * @return 回应结果
     */
    public static String send(String url, Map<String, List<String>> parameters, boolean post, String charset) {

        HttpMethod method;

        if (post) {
            PostMethod postMethod = new PostMethod(url);

            if (parameters != null && !parameters.isEmpty()) {
                for (String paramName : parameters.keySet()) {
                    for (String paramValue : parameters.get(paramName)) {
                        postMethod.addParameter(paramName, paramValue);
                    }
                }
            }

            method = postMethod;
        } else {
            String completeUrl = HttpSender.appendParams(url, parameters, charset);
            method = new GetMethod(completeUrl);
        }

        try {
            HTTP_CLIENT.executeMethod(method);
        } catch (IOException e) {
            LOG.error("", e);
        }

        try {
            return HttpSender.read(method.getResponseBodyAsStream(), charset);
        } catch (IOException e) {
            LOG.error("", e);
            return "";
        } finally {
            method.releaseConnection();
        }
    }
}
