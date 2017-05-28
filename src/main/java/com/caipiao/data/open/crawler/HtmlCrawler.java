package com.caipiao.data.open.crawler;

import com.caipiao.utils.http.HttpSender;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

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
        String url = "http://caipiao.163.com/award/getAwardNumberInfo.html?gameEn=ssc&periodNum=10";
        String html = HtmlCrawler.getHtml2(url);
        System.out.println(html);
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


}
