//    Mobo360Merchant.java

package com.caipiao.pay.mobao;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import sun.net.www.protocol.http.HttpURLConnection;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import java.io.ByteArrayOutputStream;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Map;

// Referenced classes of package com.caipiao.pay.mobao:
//			myX509TrustManager, myHostnameVerifier, Mobo360SignUtil

public class Mobo360Merchant {

    private Mobo360Merchant() {
    }

    public static String transact(String paramsStr, String serverUrl)
            throws Exception {
        if (StringUtils.isBlank(serverUrl) || StringUtils.isBlank(paramsStr))
            throw new NullPointerException("�����ַ����������Ϊ��!");
        myX509TrustManager xtm = new myX509TrustManager();
        myHostnameVerifier hnv = new myHostnameVerifier();
        ByteArrayOutputStream respData = new ByteArrayOutputStream();
        byte b[] = new byte[8192];
        String result = "";
        try {
            SSLContext sslContext = null;
            try {
                sslContext = SSLContext.getInstance("TLS");
                X509TrustManager xtmArray[] = {
                        xtm
                };
                sslContext.init(null, xtmArray, new SecureRandom());
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }
            if (sslContext != null)
                HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(hnv);
            URLConnection conn = null;
            if (serverUrl.toLowerCase().startsWith("https")) {
                HttpsURLConnection httpsUrlConn = (HttpsURLConnection) (new URL(serverUrl)).openConnection();
                httpsUrlConn.setRequestMethod("POST");
                conn = httpsUrlConn;
            } else {
                HttpURLConnection httpUrlConn = (HttpURLConnection) (new URL(serverUrl)).openConnection();
                httpUrlConn.setRequestMethod("POST");
                conn = httpUrlConn;
            }
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.8.1.1) Gecko/20061204 Firefox/2.0.0.3");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("connection", "close");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.getOutputStream().write(paramsStr.getBytes("utf-8"));
            conn.getOutputStream().flush();
            int len = 0;
            do
                try {
                    len = conn.getInputStream().read(b);
                    if (len <= 0) {
                        conn.getInputStream().close();
                        break;
                    }
                    respData.write(b, 0, len);
                } catch (SocketTimeoutException ee) {
                    throw new RuntimeException((new StringBuilder("��ȡ��Ӧ���ݳ���")).append(ee.getMessage()).toString());
                }
            while (true);
            result = respData.toString("utf-8");
            if (StringUtils.isBlank(result))
                throw new RuntimeException("���ز�������");
        } catch (Exception e) {
            throw new RuntimeException((new StringBuilder("����POST��������쳣��")).append(e.getMessage()).toString());
        }
        checkResult(result);
        return result;
    }

    private static void checkResult(String result)
            throws Exception {
        if (StringUtils.isBlank(result))
            throw new NullPointerException("��������Ϊ��!");
        try {
            Document resultDOM = DocumentHelper.parseText(result);
            Element root = resultDOM.getRootElement();
            String responseData = root.element("respData").asXML();
            String signMsg = root.element("signMsg").getStringValue();
            if (StringUtils.isBlank(responseData) || StringUtils.isBlank(signMsg))
                throw new Exception("����������ǩ��ԭ���ݴ���");
            if (!Mobo360SignUtil.verifyData(signMsg, responseData))
                throw new Exception("ϵͳЧ�鷵������ʧ�ܣ�");
        } catch (DocumentException e) {
            throw new RuntimeException((new StringBuilder("xml��������")).append(e).toString());
        }
    }

    public static String generatePayRequest(Map paramsMap)
            throws Exception {
        if (!paramsMap.containsKey("apiName") || StringUtils.isBlank((String) paramsMap.get("apiName")))
            throw new Exception("apiName����Ϊ��");
        if (!paramsMap.containsKey("apiVersion") || StringUtils.isBlank((String) paramsMap.get("apiVersion")))
            throw new Exception("apiVersion����Ϊ��");
        if (!paramsMap.containsKey("platformID") || StringUtils.isBlank((String) paramsMap.get("platformID")))
            throw new Exception("platformID����Ϊ��");
        if (!paramsMap.containsKey("merchNo") || StringUtils.isBlank("merchNo"))
            throw new Exception("merchNo����Ϊ��");
        if (!paramsMap.containsKey("orderNo") || StringUtils.isBlank("orderNo"))
            throw new Exception("orderNo����Ϊ��");
        if (!paramsMap.containsKey("tradeDate") || StringUtils.isBlank("tradeDate"))
            throw new Exception("tradeDate����Ϊ��");
        if (!paramsMap.containsKey("amt") || StringUtils.isBlank("amt"))
            throw new Exception("amt����Ϊ��");
        if (!paramsMap.containsKey("merchUrl") || StringUtils.isBlank("merchUrl"))
            throw new Exception("merchUrl����Ϊ��");
        if (!paramsMap.containsKey("merchParam"))
            throw new Exception("merchParam����Ϊ�գ���������ڣ�");
        if (!paramsMap.containsKey("tradeSummary") || StringUtils.isBlank("tradeSummary")) {
            throw new Exception("tradeSummary����Ϊ��");
        } else {
            String paramsStr = String.format("apiName=%s&apiVersion=%s&platformID=%s&merchNo=%s&orderNo=%s&tradeDate=%s&amt=%s&merchUrl=%s&merchParam=%s&tradeSummary=%s", new Object[]{
                    paramsMap.get("apiName"), paramsMap.get("apiVersion"), paramsMap.get("platformID"), paramsMap.get("merchNo"), paramsMap.get("orderNo"), paramsMap.get("tradeDate"), paramsMap.get("amt"), paramsMap.get("merchUrl"), paramsMap.get("merchParam"), paramsMap.get("tradeSummary")
            });
            return paramsStr;
        }
    }

    public static String generateQueryRequest(Map paramsMap)
            throws Exception {
        if (!paramsMap.containsKey("apiName") || StringUtils.isBlank((String) paramsMap.get("apiName")))
            throw new Exception("apiName����Ϊ��");
        if (!paramsMap.containsKey("apiVersion") || StringUtils.isBlank((String) paramsMap.get("apiVersion")))
            throw new Exception("apiVersion����Ϊ��");
        if (!paramsMap.containsKey("platformID") || StringUtils.isBlank((String) paramsMap.get("platformID")))
            throw new Exception("platformID����Ϊ��");
        if (!paramsMap.containsKey("merchNo") || StringUtils.isBlank((String) paramsMap.get("merchNo")))
            throw new Exception("merchNo����Ϊ��");
        if (!paramsMap.containsKey("orderNo") || StringUtils.isBlank((String) paramsMap.get("orderNo")))
            throw new Exception("orderNo����Ϊ��");
        if (!paramsMap.containsKey("tradeDate") || StringUtils.isBlank((String) paramsMap.get("tradeDate")))
            throw new Exception("tradeDate����Ϊ��");
        if (!paramsMap.containsKey("amt") || StringUtils.isBlank((String) paramsMap.get("amt"))) {
            throw new Exception("amt����Ϊ��");
        } else {
            String resultStr = String.format("apiName=%s&apiVersion=%s&platformID=%s&merchNo=%s&orderNo=%s&tradeDate=%s&amt=%s", new Object[]{
                    paramsMap.get("apiName"), paramsMap.get("apiVersion"), paramsMap.get("platformID"), paramsMap.get("merchNo"), paramsMap.get("orderNo"), paramsMap.get("tradeDate"), paramsMap.get("amt")
            });
            return resultStr;
        }
    }

    public static String generateRefundRequest(Map paramsMap)
            throws Exception {
        if (!paramsMap.containsKey("apiName") || StringUtils.isBlank((String) paramsMap.get("apiName")))
            throw new Exception("apiName����Ϊ��");
        if (!paramsMap.containsKey("apiVersion") || StringUtils.isBlank((String) paramsMap.get("apiVersion")))
            throw new Exception("apiVersion����Ϊ��");
        if (!paramsMap.containsKey("platformID") || StringUtils.isBlank((String) paramsMap.get("platformID")))
            throw new Exception("platformID����Ϊ��");
        if (!paramsMap.containsKey("merchNo") || StringUtils.isBlank((String) paramsMap.get("merchNo")))
            throw new Exception("merchNo����Ϊ��");
        if (!paramsMap.containsKey("orderNo") || StringUtils.isBlank((String) paramsMap.get("orderNo")))
            throw new Exception("orderNo����Ϊ��");
        if (!paramsMap.containsKey("tradeDate") || StringUtils.isBlank((String) paramsMap.get("tradeDate")))
            throw new Exception("tradeDate����Ϊ��");
        if (!paramsMap.containsKey("amt") || StringUtils.isBlank((String) paramsMap.get("amt")))
            throw new Exception("amt����Ϊ��");
        if (!paramsMap.containsKey("tradeSummary") || StringUtils.isBlank((String) paramsMap.get("tradeSummary"))) {
            throw new Exception("tradeSummary����Ϊ��");
        } else {
            String resultStr = String.format("apiName=%s&apiVersion=%s&platformID=%s&merchNo=%s&orderNo=%s&tradeDate=%s&amt=%s&tradeSummary=%s", new Object[]{
                    paramsMap.get("apiName"), paramsMap.get("apiVersion"), paramsMap.get("platformID"), paramsMap.get("merchNo"), paramsMap.get("orderNo"), paramsMap.get("tradeDate"), paramsMap.get("amt"), paramsMap.get("tradeSummary")
            });
            return resultStr;
        }
    }
}
