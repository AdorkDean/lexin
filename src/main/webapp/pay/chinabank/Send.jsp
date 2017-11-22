<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!--浠ヤ笅淇℃伅涓烘爣鍑嗙殑 HTML 鏍煎紡 + JAVA 璇█ 鎷煎噾鑰屾垚鐨?缃戦摱鍦ㄧ嚎 鏀粯鎺ュ彛鏍囧噯婕旂ず椤甸潰 -->
<html>
<body onLoad="javascript:document.E_FORM.submit()">
<form action="https://pay3.chinabank.com.cn/PayGate?encoding=UTF-8" method="POST" name="E_FORM">
    <!--浠ヤ笅鍑犻」涓虹綉涓婃敮浠橀噸瑕佷俊鎭紝淇℃伅蹇呴』姝ｇ‘鏃犺锛屼俊鎭細褰卞搷鏀粯杩涜锛?->
  <input type="hidden" name="v_md5info"    value="${v_md5info}" size="100">
  <input type="hidden" name="v_mid"        value="${v_mid}">
  <input type="hidden" name="v_oid"        value="${v_oid}">
  <input type="hidden" name="v_amount"     value="${v_amount}">
  <input type="hidden" name="v_moneytype"  value="${v_moneytype}">
  <input type="hidden" name="v_url"        value="${v_url}">
  <input type="hidden" name="pmode_id"        value="${v_pmode}">
  <input type="hidden" name="remark1"        value="${remark1}">
</form>
</body>
</html>
