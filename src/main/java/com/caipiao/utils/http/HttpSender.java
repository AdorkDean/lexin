package com.caipiao.utils.http;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 发送 HTTP 请求并返回结果。每个 HttpSender
 * 对象用于向一个地址发送，每次发送可以带不同的参数。
 *
 * @author liujie
 */
public class HttpSender {

    static final Logger log = LoggerFactory.getLogger(HttpSender.class);

    private static final int TIMEOUT = 60000;

    public static final String DEFAULT_CHATSET = "UTF-8";

    /////////////////////////////////////////////////////////

    private String url;

    private String lastResponseContent = "";

    private Map<String, List<String>> parameters = new HashMap<String, List<String>>();

    private Map<String, String> headers = new HashMap<String, String>();

    private int timeout = TIMEOUT;

    private String charset = DEFAULT_CHATSET;

    private boolean useWrapper = true;

    public void setUseWrapper(boolean useWrapper) {
        this.useWrapper = useWrapper;
    }

    // 是否启用 commons-httpclient 而不是 URLConnection
    public boolean isUseWrapper() {
        return useWrapper;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getUrl() {
        return url;
    }

    public String getLastResponseContent() {
        return lastResponseContent;
    }

    /**
     * 构造函数
     *
     * @param url 要发送请求的地址
     */
    public HttpSender(String url) {
        this.url = url;
        this.timeout = TIMEOUT;
    }

    /**
     * 构造函数
     *
     * @param url     要发送请求的地址
     * @param timeout 连接超时时间
     */
    public HttpSender(String url, int timeout) {
        this.url = url;
        this.timeout = timeout;
    }

    public HttpSender put(String name, Object... values) {
        if (values != null) {
            List<String> params = parameters.get(name);
            if (params == null) {
                params = new ArrayList<String>();
                parameters.put(name, params);
            }

            for (Object value : values) {
                if (value != null) {
                    params.add(String.valueOf(value));
                }
            }
        }
        return this;
    }

    public HttpSender putHeader(String name, String value) {
        this.headers.put(name, value);
        return this;
    }

    public HttpSender send() throws HttpSenderException {
//        if (commonsHttpClientAvailable() && useWrapper) {
//            sendWithHttpClientWrapper(false);
//        } else {
            sendSinglePartRequest(false);
//        }
        return null;
    }

    public void sendPost() throws HttpSenderException {
        if (commonsHttpClientAvailable() && useWrapper) {
            sendWithHttpClientWrapper(true);
        } else {
            sendSinglePartRequest(true);
        }
    }

    // 检查是否可以使用 apache commons http-client
    private boolean commonsHttpClientAvailable() {
        try {
            Class.forName("org.apache.commons.httpclient.HttpClient");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    private void sendWithHttpClientWrapper(boolean post) {
        // Map<String, List<String>> parameters = encodeParameters(this.parameters);
        this.lastResponseContent = HttpClientWrapper.send(this.url, this.parameters, post, charset);
    }

    // 将所有参数转换成 URL encoded
    private Map<String, List<String>> encodeParameters(Map<String, List<String>> params) {
        Map<String, List<String>> parameters = new HashMap<String, List<String>>(params);

        for (String key : parameters.keySet()) {
            List<String> encodedValues = new ArrayList<String>();

            for (String value : parameters.get(key)) {
                encodedValues.add(urlEncode(value, charset));
            }

            parameters.put(key, encodedValues);
        }
        return parameters;
    }

    private void sendSinglePartRequest(boolean post) {
        InputStream inputStream = null;
        OutputStream outputStream = null;

        String _url;
        if (post) {
            _url = url;
        } else {
            _url = appendParams(url, parameters, charset);
            log.debug("请求地址 " + _url);
        }

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(_url).openConnection();
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);

            for (String headerName : headers.keySet()) {
                String headerValue = headers.get(headerName);
                connection.setRequestProperty(headerName, headerValue);
            }

            if (post) {
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.connect();

                String params = appendParams("", parameters, charset);
                if (params.startsWith("?")) {
                    params = params.substring(1);
                }

                params += "\n\n";

                connection.getOutputStream().write(params.getBytes());
                connection.getOutputStream().flush();
            } else {
                connection.setRequestMethod("GET");
                connection.connect();
            }

            try {
                inputStream = connection.getInputStream();
                this.lastResponseContent = read(inputStream, charset);
            } catch (IOException e) {
                if (connection.getErrorStream() != null) {
                    this.lastResponseContent = read(connection.getErrorStream(), charset);
                }
                throw new HttpSenderException("请求 " + _url + " 失败: "
                        + e.getMessage(), e);
            }
        } catch (IOException e) {
            throw new HttpSenderException("连接 " + _url + " 失败: "
                    + e.getMessage(), e);
        } finally {
            CloseableUtils.close(inputStream, outputStream);
        }
    }

    public static String appendParams(String url, Map<String, List<String>> params, String charset) {
        if (params == null || params.isEmpty()) {
            return url;
        }

        String string = "";
        for (String key : params.keySet()) {
            List<String> values = params.get(key);
            for (String value : values) {
                if (value != null) {
                    string += key + "=" + urlEncode(value, charset) + "&";
                }
            }
        }
        string = StringUtils.removeEnd(string, "&");

        if (url.contains("?")) {
            if (url.endsWith("?")) {
                return url + string;
            } else {
                return url + "&" + string;
            }
        } else {
            return url + "?" + string;
        }
    }

    public Map<String, List<String>> getParameters() {
        return parameters;
    }

    public static String urlEncode(String str, String charset) {
        if (str == null) {
            return null;
        }

        try {
            return URLEncoder.encode(str, charset);
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    public static String read(InputStream inputStream, String charset) throws IOException {
        if (inputStream == null) {
            return "";
        }

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, charset));

        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }
}
