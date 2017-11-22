//    RefundResponseEntity.java

package com.caipiao.pay.mobao;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.caipiao.pay.mobao:
//			UtilXMLParser, Mobo360SignUtil

public class RefundResponseEntity {

    protected String respCode;
    protected String respDesc;
    protected String signMsg;

    public RefundResponseEntity() {
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

    public String getSignMsg() {
        return signMsg;
    }

    public void setSignMsg(String signMsg) {
        this.signMsg = signMsg;
    }

    public void parse(String respStr)
            throws Exception {
        Map resultMap = new HashMap();
        UtilXMLParser.parse(respStr, resultMap);
        Document doc = DocumentHelper.parseText(respStr);
        Element root = doc.getRootElement();
        Element respData = root.element("respData");
        String srcData = respData.asXML();
        respCode = (String) resultMap.get("/moboAccount/respData/respCode");
        if (StringUtils.isBlank(respCode))
            throw new Exception("��Ӧ��Ϣ��ʽ���󣺲�����'respCode'�ڵ㡣");
        respDesc = (String) resultMap.get("/moboAccount/respData/respDesc");
        if (StringUtils.isBlank(respDesc))
            throw new Exception("��Ӧ��Ϣ��ʽ���󣺲�����'respDesc'�ڵ�");
        signMsg = (String) resultMap.get("/moboAccount/signMsg");
        if (StringUtils.isBlank(signMsg))
            throw new Exception("��Ӧ��Ϣ��ʽ���󣺲�����'signMsg'�ڵ�");
        if (!Mobo360SignUtil.verifyData(getSignMsg(), srcData))
            throw new Exception("ǩ����֤��ͨ��");
        else
            return;
    }
}
