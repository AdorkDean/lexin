//    Mobo360Merchant.java

package com.caipiao.pay.mobao;

import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

class myX509TrustManager
        implements X509TrustManager {

    myX509TrustManager() {
    }

    public void checkClientTrusted(X509Certificate ax509certificate[], String s) {
    }

    public void checkServerTrusted(X509Certificate ax509certificate[], String s) {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
