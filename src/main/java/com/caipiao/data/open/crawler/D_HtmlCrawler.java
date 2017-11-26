package com.caipiao.data.open.crawler;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
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


    public static HashMap getPK10FromBWLC() {
        HashMap<String,String> map = new HashMap<String, String>();
        try {
            Document document = Jsoup.connect(GetOpenUrl.Bjpk10).timeout(10000).get();
            Element lott_cont = document.getElementsByClass("lott_cont").get(0);
            Elements elements = lott_cont.getElementsByTag("tr");
            for(Element element : elements){
                Elements tds = element.getElementsByTag("td");
                if(tds.size() > 0){
                    map.put(tds.get(0).text(),tds.get(1).text());
                }
            }
        } catch (IOException e) {
            System.out.println("异常描述" + e.getMessage());
            e.printStackTrace();
        }
        return map;
    }

    public static void main(String[] args) {
        System.out.println(JSONObject.toJSONString(getPK10FromBWLC()));
    }
}