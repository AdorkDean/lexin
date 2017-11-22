//    Mobo360Merchant.java

package com.caipiao.pay.mobao;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

class myHostnameVerifier
        implements HostnameVerifier {

    myHostnameVerifier() {
    }

    public boolean verify(String hostname, SSLSession session) {
        return true;
    }
}
