//    AlipayNotify.java

package com.caipiao.pay.alipay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

// Referenced classes of package com.caipiao.pay.alipay:
//			AlipayCore, AlipayConfig, MD5

public class AlipayNotify {

    private static final String HTTPS_VERIFY_URL = "https://mapi.alipay.com/gateway.do?service=notify_verify&";

    public AlipayNotify() {
    }

    public static boolean verify(Map params) {
        String responseTxt = "true";
        if (params.get("notify_id") != null) {
            String notify_id = (String) params.get("notify_id");
            responseTxt = verifyResponse(notify_id);
        }
        String sign = "";
        if (params.get("sign") != null)
            sign = (String) params.get("sign");
        boolean isSign = getSignVeryfy(params, sign);
        return isSign && responseTxt.equals("true");
    }

    private static boolean getSignVeryfy(Map Params, String sign) {
        Map sParaNew = AlipayCore.paraFilter(Params);
        String preSignStr = AlipayCore.createLinkString(sParaNew);
        boolean isSign = false;
        if (AlipayConfig.sign_type.equals("MD5"))
            isSign = MD5.verify(preSignStr, sign, AlipayConfig.key, AlipayConfig.input_charset);
        return isSign;
    }

    private static String verifyResponse(String notify_id) {
        String partner = AlipayConfig.partner;
        String veryfy_url = (new StringBuilder("https://mapi.alipay.com/gateway.do?service=notify_verify&partner=")).append(partner).append("&notify_id=").append(notify_id).toString();
        return checkUrl(veryfy_url);
    }

    private static String checkUrl(String urlvalue) {
        String inputLine = "";
        try {
            URL url = new URL(urlvalue);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            inputLine = in.readLine().toString();
        } catch (Exception e) {
            e.printStackTrace();
            inputLine = "";
        }
        return inputLine;
    }
}
