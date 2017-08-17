package com.caipiao.data.open.crawler;

import com.caipiao.utils.http.HttpSender;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.ObjectInput;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by nicholas.liu on 2016/8/8.
 */
public class HtmlCrawler {

    private static final String UA = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";

    public static String getHtml2(String url){

        HttpSender httpSender = new HttpSender(url);
        httpSender.setCharset("UTF-8");
        httpSender.putHeader("Content-Type","application/json; charset=UTF-8");
        httpSender.send();
        String lastResponseContent = httpSender.getLastResponseContent();

        System.out.println(lastResponseContent);

        return lastResponseContent;
    }

    public static void main(String[] args) throws IOException {
//        String url = "http://caipiao.163.com/award/getAwardNumberInfo.html?gameEn=ssc&periodNum=10";
//        String html = HtmlCrawler.getHtml2(url);
//        System.out.println(html);

        String url = "http://api.kaijiangtong.com/lottery/?name=hnkw&format=json&uid=475733&token=afc528e8ebfa3e95ab1e6cc632b55d1998f728cf";
        String content = HtmlCrawler.getCaipiaokongYnssc(url);
        System.out.println(content);

//        HashMap result = new HashMap();
//
//        JSONObject jsonObject = JSONObject.fromObject(content);
//        JSONArray names = jsonObject.names();
//        Iterator iterator = names.iterator();
//
//        while (iterator.hasNext()){
//            String qihao = iterator.next().toString();
//            Object o = jsonObject.get(qihao);
//            JSONObject haoma = JSONObject.fromObject(o);
//            String number = haoma.get("number").toString();
//            result.put(qihao,number);
//            System.out.println(qihao + ":" + number);
//        }
//
//        System.out.println(result.size());

    }

    public static String getWangyi163Html(String url)  {
        String html = null;
        CloseableHttpClient httpClient =HttpClients.createDefault();
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

        }catch (Exception e) {
            System.out.println("异常描述"+ e.getMessage());
            System.out.println("访问网易彩票网站【" + url + "】出现异常!");
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                System.out.println("关闭连接失败");
            }
        }
        System.out.println("访问网易彩票网站【" + url + "】抓取号码成功!");
        return html;
    }


    public static String getCaipiaokongYnssc(String url)  {
        try {
            Thread.sleep(1000 * 5);
            System.out.println("休息5秒，以便符合彩票控网站的规则");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String html = null;
        CloseableHttpClient httpClient =HttpClients.createDefault();
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

        }catch (Exception e) {
            System.out.println("异常描述"+ e.getMessage());
            System.out.println("访问彩票控网站【" + url + "】出现异常!");
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                System.out.println("关闭连接失败");
            }
        }
        System.out.println("访问彩票控网站【" + url + "】抓取号码成功!");
        return html;
    }



}
