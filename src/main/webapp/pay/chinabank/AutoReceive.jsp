<%@page contentType="text/html; charset=utf-8" language="java" %>
<%@page import="com.caipiao.entity.Bc_rech" %>
<%@page import="com.caipiao.entity.Bc_user" %>
<%@page import="com.caipiao.pay.chinabank.BankName" %>
<%@page import="com.caipiao.service.my.MyRechangeService" %>
<%@page import="com.caipiao.service.systeminit.UserStatic" %>
<%@page import="com.caipiao.utils.SystemSet" %>
<jsp:useBean id="MD5" scope="request" class="com.caipiao.pay.chinabank.MD5"/>
<%
    //****************************************	// MD5瀵嗛挜瑕佽窡璁㈠崟鎻愪氦椤电浉鍚岋紝濡係end.asp閲岀殑 key = "test" ,淇敼""鍙峰唴 test 涓烘偍鐨勫瘑閽?
    // 濡傛灉鎮ㄨ繕娌℃湁璁剧疆MD5瀵嗛挜璇风櫥闄嗘垜浠负鎮ㄦ彁渚涘晢鎴峰悗鍙帮紝鍦板潃锛歨ttps://merchant3.chinabank.com.cn/
    String key = SystemSet.paytype.getProperty("chinabank_key");        // 鐧婚檰鍚庡湪涓婇潰鐨勫鑸爮閲屽彲鑳芥壘鍒扳€滆祫鏂欑鐞嗏€濓紝鍦ㄨ祫鏂欑鐞嗙殑浜岀骇瀵艰埅鏍忛噷鏈夆€淢D5瀵嗛挜璁剧疆鈥?
    // 寤鸿鎮ㄨ缃竴涓?6浣嶄互涓婄殑瀵嗛挜鎴栨洿楂橈紝瀵嗛挜鏈€澶?4浣嶏紝浣嗚缃?6浣嶅凡缁忚冻澶熶簡
//****************************************
//鑾峰彇鍙傛暟
    request.setCharacterEncoding("gbk");
    String v_oid = request.getParameter("v_oid");        // 璁㈠崟鍙?
    String v_pmode = request.getParameter("v_pmode");        // 鏀粯鏂瑰紡涓枃璇存槑锛屽"涓闀垮煄淇＄敤鍗?
    String v_pstatus = request.getParameter("v_pstatus");    // 鏀粯缁撴灉锛?0鏀粯瀹屾垚锛?0鏀粯澶辫触锛?
    String v_pstring = request.getParameter("v_pstring");    // 瀵规敮浠樼粨鏋滅殑璇存槑锛屾垚鍔熸椂锛坴_pstatus=20锛変负"鏀粯鎴愬姛"锛屾敮浠樺け璐ユ椂锛坴_pstatus=30锛変负"鏀粯澶辫触"
    String v_amount = request.getParameter("v_amount");        // 璁㈠崟瀹為檯鏀粯閲戦
    String v_moneytype = request.getParameter("v_moneytype");    // 甯佺
    String v_md5str = request.getParameter("v_md5str");        // MD5鏍￠獙鐮?
    String remark1 = request.getParameter("remark1");        // 澶囨敞1
    String remark2 = request.getParameter("remark2");        // 澶囨敞2
    String text = v_oid + v_pstatus + v_amount + v_moneytype + key; //鎷煎噾鍔犲瘑涓?
    String v_md5 = MD5.getMD5ofStr(text).toUpperCase();
    if (v_md5str.equals(v_md5)) {
        out.print("ok"); //鍛婅瘔鏈嶅姟鍣ㄩ獙璇侀€氳繃锛屽仠姝㈠彂閫?
        if ("20".equals(v_pstatus)) {
            // 鏀粯鎴愬姛锛屽晢鎴?鏍规嵁鑷繁涓氬姟鍋氱浉搴旈€昏緫澶勭悊
            // 姝ゅ鍔犲叆鍟嗘埛绯荤粺鐨勯€昏緫澶勭悊锛堜緥濡傚垽鏂噾棰濓紝鍒ゆ柇鏀粯鐘舵€侊紝鏇存柊璁㈠崟鐘舵€佺瓑绛夛級......
            MyRechangeService dao = new MyRechangeService();
            Bc_rech en = dao.find(v_oid, remark1);
            if (null != en) {
                int sta = en.getRech_status();
                if (0 == sta) {
                    boolean up = dao.updateRech(en.getRech_id(), 1);
                    if (up) {
                        Bc_user uen = UserStatic.find(en.getUser_id());
                        UserStatic.AddMoney(uen, en.getRech_money() + en.getRech_give(), 0, v_oid, 2, BankName.GetName(v_pmode));
                    }
                }
            }
        }
    } else {
        out.print("error"); //鍛婅瘔鏈嶅姟鍣ㄩ獙璇佸け璐ワ紝璇锋眰閲嶅彂
    }
%>