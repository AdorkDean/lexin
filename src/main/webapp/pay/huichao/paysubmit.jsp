<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@ page import="com.caipiao.pay.huichao.MD5" %>
<%@page import="com.caipiao.utils.TryStatic"%>
<%@page import="com.caipiao.entity.Bc_user"%>
<%@page import="com.caipiao.service.systeminit.UserStatic"%>
<%@page import="com.caipiao.service.my.MyRechangeService"%>
<%@page import="com.caipiao.pay.chinabank.BankName"%>
<%@page import="com.caipiao.utils.StaticItem"%>
<%@page import="com.caipiao.utils.TimeUtil"%>
<%!	String formatString(String text){ 
			if(text == null) {
				return ""; 
			}
			return text;
		}
%>
<html>
	<head><title>To HuiChao Page</title></head>
<%
	request.setCharacterEncoding("utf-8");
	String MD5key = "nanhenan";//MD5key值
    String MerNo = "28796";//商户ID
    String BillNo = StaticItem.GetRechitem();//订单编号
    String Amount = formatString(request.getParameter("p3_Amt"));  //支付金额
    String ReturnURL = "http://www.yt1018.cn/pay/huichao/payresult.jsp"; //返回地址	 

	String Remark = formatString(request.getParameter("pa_MP"));
	//[必填]返回数据给商户的地址(商户自己填写):::注意请在测试前将该地址告诉我方人员;否则测试通不过
//    ReturnURL = "http://192.168.1.108/ecpss/payresult.jsp";
out.print("Success");
    String md5src = MerNo +"&"+ BillNo +"&"+ Amount +"&"+ ReturnURL +"&"+ MD5key ;//加密字符串    
    MD5 md5 = new MD5();
	System.out.println("md5src="+md5src);
    String SignInfo = md5.getMD5ofStr(md5src);//MD5检验结果

	String AdviceURL ="http://www.yt1018.cn/pay/huichao/payresult.jsp";   //[必填]支付完成后，后台接收支付结果，可用来更新数据库值

	 //送货信息(方便维护，请尽量收集！如果没有以下信息提供，请传空值:'')
	 //因为关系到风险问题和以后商户升级的需要，如果有相应或相似的内容的一定要收集，实在没有的才赋空值,谢谢。

	//String  defaultBankNumber = "";
	String  defaultBankNumber = formatString(request.getParameter("bank"));//[选填]银行代码
	String  orderTime =TimeUtil.getToday("yyyyMMddHHmmss");  //[必填]交易时间：YYYYMMDDHHMMSS

	//账单地址选择传递
    String products=Remark+"为订单("+BillNo+")冲值";// '------------------物品信息
    
	double money = TryStatic.StrToInt(Amount);
	if (money>=50&&null!=Remark){
	    Bc_user find = UserStatic.find(Remark);
	    if (null!=find){
	    	int uid = find.getUser_id();
	    	//Remark = uid+"";
	    	MyRechangeService dao = new MyRechangeService();
	    	dao.Rech(find.getUser_name(), uid, BillNo, money, 0, 5, Remark);
	 		%>
			<body onLoad="javascript:document.yeepay.submit()">
				<form name="yeepay" action="http://www.nnhem.cn/huichao.php" method="POST">
					<table align="center">
    					<tr>
      						<td></td>
      						<td><input type="hidden" name="MerNo" value="<%=MerNo%>"></td>
    					</tr>
					    <tr>
					      <td></td>
					      <td><input type="hidden" name="BillNo" value="<%=BillNo%>"></td>
					    </tr>
					    <tr>
					      <td></td>
					      <td><input type="hidden" name="Amount" value="<%=Amount%>"></td>
					    </tr>
					    <tr>
					      <td></td>
					      <td><input type="hidden" name="ReturnURL" value="<%=ReturnURL%>" ></td>
					    </tr>
						 <tr>
					      <td></td>
					      <td><input type="hidden" name="AdviceURL" value="<%=AdviceURL%>" ></td>
					    </tr>
					    <tr>
					      <td></td>
					      <td><input type="hidden" name="SignInfo" value="<%=SignInfo%>"></td>
					    </tr>
					    <tr>
					      <td></td>
					      <td><input type="hidden" name="Remark" value="<%=Remark%>"></td>
					    </tr>
						 <tr>
					      <td></td>
					      <td><input type="hidden" name="defaultBankNumber" value="<%=defaultBankNumber%>"></td>
					    </tr>
						 <tr>
					      <td></td>
					      <td><input type="hidden" name="orderTime" value="<%=orderTime%>"></td>
					    </tr>
					    <tr>
					      <td></td>
					      <td><input type="hidden" name="products" value="<%=products%>"></td>
					    </tr>
  					</table>
				</form>
			</body>
			<%
	    }else{
	    	%><body>用户名不存在</body><%
	    }
	}else{
		%><body>用户名或者金额（最小50）错误</body><%
	}
%>
</html>
