<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.caipiao.pay.huichao.MD5"%>
<%@page import="com.caipiao.service.my.MyRechangeService"%>
<%@page import="com.caipiao.entity.Bc_rech"%>
<%@page import="com.caipiao.utils.TryStatic"%>
<%@page import="com.caipiao.entity.Bc_user"%>
<%@page import="com.caipiao.service.systeminit.UserStatic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
    //字符编码
    String CharacterEncoding = "UTF-8";
	request.setCharacterEncoding(CharacterEncoding);

    String MD5key = "zzwfKzvS";
    String BillNo = request.getParameter("BillNo");
    String Amount = request.getParameter("Amount");
    String Succeed = request.getParameter("Succeed");
    String Result = request.getParameter("Result");
    String SignMD5info = request.getParameter("SignMD5info");
    String Remark = request.getParameter("Remark");
    //String MerNo = "29238";//商户ID
    
    MD5 md5 = new MD5();
    String md5src = BillNo+"&"+Amount+"&"+Succeed+"&"+MD5key;
    String md5sign; //MD5加密后的字符串
    md5sign = md5.getMD5ofStr(md5src);//MD5检验结果

    String tradeOrder = request.getParameter("tradeOrder");
  
%>

<html>
<head><title>Payment Result</title></head>

<body>
<!-- 请加上你们网站的框架。就是你们网站的头部top，左部left等。还有字体等你们都要做调整。 -->

 <%
 if (SignMD5info.equals(md5sign)){
 %>
 <!-- MD5验证成功 -->
	<table width="728" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td  align="right" valign="top" width="200">您的支付订单：</td>
    <td  align="left" valign="top"><%= BillNo%></td>
  </tr>
    <tr>
    <td  align="right" valign="top">支付的金额：</td>
    <td  align="left" valign="top"><%= Amount%></td>
  </tr>
    <tr>
    <td  align="right" valign="top">Payment result：</td>
	<%if (Succeed.equals("88")){
		MyRechangeService dao =new MyRechangeService();
		Bc_rech en= dao.find(BillNo);
		double rechmoney = TryStatic.StrToDouble(Amount);
		if(null!=en){
			int sta = en.getRech_status();
			if(0==sta){
				double money = en.getRech_money();
				if(rechmoney==money){
					double newgive = 0;
					double xf = 1;
					int tk=0;
					try{
						tk = dao.findUserDrawCount(en.getUser_id());
					}finally{}
					//+++++++++++++++++++++++++++++++注释原有的充值规则
					//if(tk==0){
						//if(money>=5000){
							//newgive = money*0.05;
						//}else if(money>=100){
							//newgive = money*0.03;
						//}
						//newgive = newgive>=5000?5000:newgive;//赠送金额最高5000
					//}
					//+++++++++++++++++++++++++++++++注释原有的充值规则
					//+++++++++++++++++++++++++++++++添加新的充值规则
					if(money>=100){
						newgive = money*0.1;
					}
					newgive = newgive>=6000?6000:newgive;//赠送金额最高6000
					//+++++++++++++++++++++++++++++++添加新的充值规则
					boolean up = dao.updateRech(en.getRech_id(),1,newgive);
					if(up){
						Bc_user uen = UserStatic.find(en.getUser_id());
						double cm =  money+newgive;
						UserStatic.AddMoney(uen,cm,0,BillNo,2,"汇潮支付",cm*xf); 
					}
					}
				}
			}
		}
	%><!-- 可修改订单状态为正在付款中 -->
	<!-- 提交支付信息成功，返回绿色的提示信息 -->
	<td  align="left" valign="top" style="color:green;"><%= Result%></td>
	<%
	}
	else
	{
	%><!-- 提交支付信息失败，返回红色的提示信息 -->
    <td  align="left" valign="top" style="color:red;"><%= Result%>&nbsp;&nbsp;&nbsp;&nbsp;<%= Succeed%></td>
	<%
	}%>
  </tr>
  
</table>
<%
}else{
%>
 <!-- MD5验证失败 -->
<table width="728" border="0" cellspacing="0" cellpadding="0" align="center">
 <tr>
    <td  align="center" valign="top" style="color:red;">Validation failed!</td>
	</tr>
	</table>
<%	
}
 %>
</body>
</html>

