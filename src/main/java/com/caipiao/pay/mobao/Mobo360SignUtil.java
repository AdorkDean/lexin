package com.caipiao.pay.mobao;

import org.apache.commons.lang.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

public class Mobo360SignUtil {
    private static PrivateKey privateKey = null;
    private static X509Certificate cert = null;
    private static String signType = "MD5";
    private static String key = "";

    public static void init()
            throws Exception {
        if ("RSA".equals(signType))
            initRAS("D:/mobao/merchtest.pfx", "D:/mobao/mobaopay.cer",
                    "merchtest");
        else if ("MD5".equals(signType))
            initMD5();
    }

    public static void initMD5()
            throws Exception {
        key = "4c59549eb1285ec1c7e38cf83edff7a2";
    }

    public static void initRAS(String pfxFilePath, String certFilePath, String pfxPwd)
            throws Exception {
        if (StringUtils.isBlank(pfxFilePath)) {
            throw new Exception("私钥文件路径不能为空！");
        }
        if (StringUtils.isBlank(certFilePath)) {
            throw new Exception("公钥文件路径不能为空！");
        }
        if (StringUtils.isBlank(pfxPwd)) {
            throw new Exception("私钥密码不能为空！");
        }
        if ((privateKey == null) || (cert == null)) {
            InputStream is = null;
            try {
                KeyStore ks = KeyStore.getInstance("PKCS12");
                is = new FileInputStream(pfxFilePath);
                if (is == null) {
                    throw new Exception("证书文件路径不正确！");
                }
                String pwd = pfxPwd;
                ks.load(is, pwd.toCharArray());
                String alias = "";
                Enumeration e = ks.aliases();
                while (e.hasMoreElements()) {
                    alias = (String) e.nextElement();
                }
                privateKey = (PrivateKey) ks.getKey(alias, pwd.toCharArray());
                CertificateFactory cf = CertificateFactory.getInstance("X.509");
                is = new FileInputStream(certFilePath);
                if (is == null) {
                    throw new Exception("证书文件路径不正确！");
                }
                cert = (X509Certificate) cf.generateCertificate(is);
                is.close();
            } catch (Exception e) {
                throw new RuntimeException("签名初始化失败！" + e);
            } finally {
                if (is != null)
                    is.close();
            }
        }
    }

    public static String signData(String sourceData)
            throws Exception {
        String signStrintg = "";
        if ("RSA".equals(signType)) {
            if (privateKey == null) {
                throw new Exception("签名尚未初始化！");
            }
            if (StringUtils.isBlank(sourceData)) {
                throw new Exception("签名数据为空！");
            }
            Signature sign = Signature.getInstance("MD5withRSA");
            sign.initSign(privateKey);
            sign.update(sourceData.getBytes("utf-8"));
            byte[] signBytes = sign.sign();
            BASE64Encoder encoder = new BASE64Encoder();
            signStrintg = encoder.encode(signBytes);
        } else if ("MD5".equals(signType)) {
            signStrintg = signByMD5(sourceData, key);
        }
        signStrintg.replaceAll("\r", "").replaceAll("\n", "");
        return signStrintg;
    }

    public static boolean verifyData(String signData, String srcData)
            throws Exception {
        if ("RSA".equals(signType)) {
            if (cert == null) {
                throw new Exception("签名尚未初始化！");
            }
            if (StringUtils.isBlank(signData)) {
                throw new Exception("系统校验时：签名数据为空！");
            }
            if (StringUtils.isBlank(srcData)) {
                throw new Exception("系统校验时：原数据为空！");
            }
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = decoder.decodeBuffer(signData);
            Signature sign = Signature.getInstance("MD5withRSA");
            sign.initVerify(cert);
            sign.update(srcData.getBytes("utf-8"));
            return sign.verify(b);
        }
        if ("MD5".equals(signType)) {
            return signData.equalsIgnoreCase(signByMD5(srcData, key));
        }

        return false;
    }

    public static String signByMD5(String sourceData, String key)
            throws Exception {
        String data = sourceData + key;
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] sign = md5.digest(data.getBytes());

        return Bytes2HexString(sign).toUpperCase();
    }

    public static String Bytes2HexString(byte[] b) {
        StringBuffer ret = new StringBuffer(b.length);
        String hex = "";
        for (int i = 0; i < b.length; i++) {
            hex = Integer.toHexString(b[i] & 0xFF);

            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret.append(hex.toUpperCase());
        }
        return ret.toString();
    }
}