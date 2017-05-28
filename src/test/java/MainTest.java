import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by liujie on 2017/2/15.
 */
public class MainTest {

    public static void main(String[] args) {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String orderNo = format.format(new Date()) + new Random().nextInt(1000000000);

        System.out.println(orderNo);
        System.out.println(Math.random());
        System.exit(1);


        try {
            String urlNameString = "http://103.250.4.76/pay_pay/huicaho/a.jsp";
            String params = new String();
            params = params + "?PayNO=" + "C0522102800003";
            params = params + "&PayJe=" + "1";
            params = params + "&PayMore=" + "woshijishu";
            params = params + "&key=5426487684";

//            String encodeUrlNameString = URLEncoder.encode("中国","UTF-8");
//            URLDecoder.decode()

            String url = urlNameString + params;
            System.out.println("url=" + url);
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection)realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();

            //获取响应状态
            int responseCode = connection.getResponseCode();
            if(200 == responseCode){
                System.out.println("请求成功 responseCode=" + responseCode);
            }else{
                System.out.println("请求上分网站失败responseCode=" + responseCode);
            }


        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {

        }
    }
}
