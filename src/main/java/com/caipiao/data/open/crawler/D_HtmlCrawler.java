package com.caipiao.data.open.crawler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;

public class D_HtmlCrawler {

    public static String getHtml(String url) {
        String html = null;
        DefaultHttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(10000));
        httpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(10000));
        HttpGet httpget = new HttpGet(url);
        httpget.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
        httpget.setHeader("Content-Type", "text/javascript; charset=UTF-8");
        try {
            HttpResponse responce = httpClient.execute(httpget);
            int resStatu = responce.getStatusLine().getStatusCode();
            if (resStatu == 200) {
                HttpEntity entity = responce.getEntity();
                if (entity != null)
                    html = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            System.out.println("异常描述" + e.getMessage());
            System.out.println("访问【" + url + "】出现异常!");
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        System.out.println("访问其他彩票网站【" + url + "】抓取号码成功!");
        return html;
    }

    public static String get360Html(String url) {
        String html = null;
        DefaultHttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(10000));
        httpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(10000));
        HttpGet httpget = new HttpGet(url);
        httpget.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
        httpget.setHeader("Content-Type", "text/javascript; charset=UTF-8");
        try {
            HttpResponse responce = httpClient.execute(httpget);
            int resStatu = responce.getStatusLine().getStatusCode();
            if (resStatu == 200) {
                HttpEntity entity = responce.getEntity();
                if (entity != null)
                    html = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            System.out.println("异常描述" + e.getMessage());
            System.out.println("访问360彩票网站【" + url + "】出现异常!");
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        System.out.println("访问360彩票网站【" + url + "】抓取号码成功!");
        return html;
    }

    public static String getWangyi163Html(String url) {
        String html = null;
        DefaultHttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(10000));
        httpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(10000));
        HttpGet httpget = new HttpGet(url);
        httpget.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
        httpget.setHeader("Content-Type", "text/javascript; charset=UTF-8");
        try {
            HttpResponse responce = httpClient.execute(httpget);
            int resStatu = responce.getStatusLine().getStatusCode();
            if (resStatu == 200) {
                HttpEntity entity = responce.getEntity();
                if (entity != null)
                    html = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            System.out.println("异常描述" + e.getMessage());
            System.out.println("访问网易彩票网站【" + url + "】出现异常!");
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        System.out.println("访问网易彩票网站【" + url + "】抓取号码成功!");
        return html;
    }

    public static HashMap getCP156HTML() {
        String url = GetOpenUrl.Bjpk10;
        String result = null;
        RequestConfig config = RequestConfig.custom()
                .setConnectionRequestTimeout(10000).setConnectTimeout(10000)
                .setSocketTimeout(10000).build();

        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(config).build();
        HashMap map = null;
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            int resStatu = response.getStatusLine().getStatusCode();
            if (resStatu == 200) {
                HttpEntity entity = response.getEntity();
                if (entity != null)
                    result = EntityUtils.toString(entity);
                JSONObject object = JSONObject.parseObject(result);
                JSONArray data = object.getJSONArray("data");
                if (data != null && data.size() > 0) {
                    map = new HashMap();
                    for (int i = 0; i < data.size(); i++){
                        JSONObject o = (JSONObject) data.get(i);
                        map.put(o.getString("expect"),o.getString("opencode"));
                    }
                }
                System.out.println("访问访问CP165网站网站【" + url + "】抓取号码成功!");
            }
        } catch (Exception e) {
            System.out.println("异常描述" + e.getMessage());
            System.out.println("访问CP165网站【" + url + "】出现异常!");
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return map;
    }


    public static void main(String[] args) {
        System.out.println(JSONObject.toJSONString(getCP156HTML()));
    }
}