//    Mobo360Config.java

package com.caipiao.pay.mobao;


public class Mobo360Config {

    public static final String SIGN_TYPE = "MD5";
    public static final String MBP_KEY = "4c59549eb1285ec1c7e38cf83edff7a2";
    public static final String PLATFORM_ID = "210001420004964";
    public static final String MERCHANT_ACC = "210001420004964";
    public static final String PFX_FILE = "D:/mobao/merchtest.pfx";
    public static final String CERT_FILE = "D:/mobao/mobaopay.cer";
    public static final String PASSWD = "merchtest";
    public static final String MOBAOPAY_GETWAY = "https://trade.mobaopay.com/cgi-bin/netpayment/pay_gate.cgi";
    public static final String MOBAOPAY_API_VERSION = "1.0.0.0";
    public static final String MOBAOPAY_APINAME_PAY = "WEB_PAY_B2C";
    public static final String MOBAOPAY_APINAME_QUERY = "MOBO_TRAN_QUERY";
    public static final String MOBAOPAY_APINAME_REFUND = "MOBO_TRAN_RETURN";

    public Mobo360Config() {
    }
}
