<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="/WEB-INF/definefn.tld" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>财务中心_业绩统计</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css"
			href="/adminsqwe/css/admin.css">
		<script language="javascript" type="text/javascript"
			src="/js/jquery-1.7.2.min.js"></script>
		<script language="javascript" type="text/javascript"
			src="/js/My97DatePicker/WdatePicker.js"></script>
	</head>

	<body>
		<table class="mtb" cellspacing="0" style="width: 98%; margin: auto">
			<caption>
				业绩统计查询
			</caption>
			<tr>
				<td >
					<form action="/admin/AdminCaiwu!achievement.jzh" method="GET">
						<table cellspacing="0" style="width: 100%; margin: auto;">
							<tr >
								<td width="7%" align="right" style="white-space: nowrap;">用户名：</td>
								<td width="18%" style="white-space: nowrap;">
									<input value="${username}" name="username" type="text"/>
								</td>
								<td width="7%" align="right" style="white-space: nowrap;">开始时间：</td>
								<td width="18%" style="white-space: nowrap;"><input style="width: 90%" name="btime" id="btime" value="${btime}" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></td>
								<td width="7%" align="right" style="white-space: nowrap;">~结束时间：</td>
								<td width="18%" style="white-space: nowrap;"><input style="width: 90%" name="etime" id="etime" value="${etime}" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></td>
								<td width="7%" align="right" style="white-space: nowrap;">代理：</td>
								<td width="18%" style="white-space: nowrap;">
									<select style="width: 100%;" name="agent" >
										<option value="" <c:if test="${agent == ''}">selected="selected"</c:if>>全网</option>
										<c:forEach items="${fn:loadAgent()}" var="im">
    										<option value="${im}" <c:if test="${agent == im}">selected="selected"</c:if>>${im}</option>
    									</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td colspan="8" align="center">
									<input type="submit" value=" 查  询 "/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="reset" value=" 清  空 "/>
								</td>
							</tr>
						</table>	
					</form>
				</td>
			</tr>
			<c:if test="${null!=page}">
				<tr>
					<td align="center">
						<div class="my_page">
							<div id="page_wrapper" class="page">
								${page}
							</div>
						</div>
					</td>
				</tr>
			</c:if>
			<tr>
				<td>
					<table cellspacing="0" style="width: 100%; margin: auto;">
						<thead>
							<tr class="tit">
								<td style="white-space: nowrap; text-align: center;" width="12.5%">
									上级代理
								</td>
								<td style="white-space: nowrap; text-align: center;" width="12.5%">
									用户
								</td>
								<td style="white-space: nowrap; text-align: center;" width="12.5%">
									用户余额
								</td>
								<td style="white-space: nowrap; text-align: center;" width="12.5%">
									充值金额
								</td>
								<td style="white-space: nowrap; text-align: center;" width="12.5%">
									赠送金额
								</td>
								<td style="white-space: nowrap; text-align: center;" width="12.5%">
									提款金额
								</td>
								<td style="white-space: nowrap; text-align: center;" width="12.5%">
									盈利(含余额)
								</td>
								<td style="white-space: nowrap; text-align: center;" width="12.5%">
									净盈利(不含余额)
								</td>
							</tr>
						<thead>
						<tbody>
							<c:set var="totalMoney" value="0"></c:set>
							<c:set var="totalRech" value="0"></c:set>
							<c:set var="totalGive" value="0"></c:set>
							<c:set var="totalDraw" value="0"></c:set>
							<c:set var="totalProfit" value="0"></c:set>
							<c:set var="totalNetProfit" value="0"></c:set>
							<c:if test="${not empty list}">
								<c:forEach var="c" items="${list}">
									<c:set var="totalMoney" value="${totalMoney + c.money}"></c:set>
									<c:set var="totalRech" value="${totalRech + c.rech}"></c:set>
									<c:set var="totalGive" value="${totalGive + c.give}"></c:set>
									<c:set var="totalDraw" value="${totalDraw + c.draw}"></c:set>
									<c:set var="totalProfit" value="${totalProfit + c.profit}"></c:set>
									<c:set var="totalNetProfit" value="${totalNetProfit + c.netProfit}"></c:set>
									<tr>
										<td style="white-space: nowrap; ">${c.agent}</td>
										<td style="white-space: nowrap; ">${c.username}</td>
										<td style="white-space: nowrap; text-align: right;"><fmt:formatNumber value="${c.money}" type="currency"/></td>
										<td style="white-space: nowrap; text-align: right;"><fmt:formatNumber value="${c.rech}" type="currency"/></td>
										<td style="white-space: nowrap; text-align: right;"><fmt:formatNumber value="${c.give}" type="currency"/></td>
										<td style="white-space: nowrap; text-align: right;"><fmt:formatNumber value="${c.draw}" type="currency"/></td>
										<td style="white-space: nowrap; text-align: right;">
											<c:choose>
												<c:when test="${c.profit < 0}">
													<font color="red"><fmt:formatNumber value="${c.profit}" type="currency"/></font>
												</c:when>
												<c:otherwise>
													<font color="blue"><fmt:formatNumber value="${c.profit}" type="currency"/></font>
												</c:otherwise>
											</c:choose>
										</td>
										<td style="white-space: nowrap; text-align: right;">
											<c:choose>
												<c:when test="${c.netProfit < 0}">
													<font color="red"><fmt:formatNumber value="${c.netProfit}" type="currency"/></font>
												</c:when>
												<c:otherwise>
													<font color="blue"><fmt:formatNumber value="${c.netProfit}" type="currency"/></font>
												</c:otherwise>
											</c:choose>
										</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${ empty list}">
								<tr><td colspan="8">没有您查询的记录!</td></tr>
							</c:if>
						</tbody>
						<tfoot>
							<tr>
								<td style="white-space: nowrap; text-align: right; color:black;" colspan="2">当前页小计： </td>
								<td style="white-space: nowrap; text-align: right; color:black;"><fmt:formatNumber value="${totalMoney}" type="currency"/></td>
								<td style="white-space: nowrap; text-align: right; color:black;"><fmt:formatNumber value="${totalRech}" type="currency"/></td>
								<td style="white-space: nowrap; text-align: right; color:black;"><fmt:formatNumber value="${totalGive}" type="currency"/></td>
								<td style="white-space: nowrap; text-align: right; color:black;"><fmt:formatNumber value="${totalDraw}" type="currency"/></td>
								<td style="white-space: nowrap; text-align: right; color:black;"><fmt:formatNumber value="${totalProfit}" type="currency"/></td>
								<td style="white-space: nowrap; text-align: right; color:black;"><fmt:formatNumber value="${totalNetProfit}" type="currency"/></td>
							</tr>
							<tr>
								<td style="white-space: nowrap; text-align: right; color:black;" colspan="2">合计：</td>
								<td style="white-space: nowrap; text-align: right; color:red;"><fmt:formatNumber value="${achie.money}" type="currency"/></td>
								<td style="white-space: nowrap; text-align: right; color:red;"><fmt:formatNumber value="${achie.rech}" type="currency"/></td>
								<td style="white-space: nowrap; text-align: right; color:red;"><fmt:formatNumber value="${achie.give}" type="currency"/></td>
								<td style="white-space: nowrap; text-align: right; color:red;"><fmt:formatNumber value="${achie.draw}" type="currency"/></td>
								<td style="white-space: nowrap; text-align: right; color:red;"><fmt:formatNumber value="${achie.profit}" type="currency"/></td>
								<td style="white-space: nowrap; text-align: right; color:red;"><fmt:formatNumber value="${achie.netProfit}" type="currency"/></td>
							</tr>
							<tr class="tit">
								<td style="white-space: nowrap; text-align: center;" >
									上级代理
								</td>
								<td style="white-space: nowrap; text-align: center;" >
									用户
								</td>
								<td style="white-space: nowrap; text-align: center;" >
									用户余额
								</td>
								<td style="white-space: nowrap; text-align: center;" >
									充值金额
								</td>
								<td style="white-space: nowrap; text-align: center;" >
									赠送金额
								</td>
								<td style="white-space: nowrap; text-align: center;" >
									提款金额
								</td>
								<td style="white-space: nowrap; text-align: center;" >
									盈利(含余额)
								</td>
								<td style="white-space: nowrap; text-align: center;" >
									净盈利(不含余额)
								</td>
							</tr>
						</tfoot>
					</table>
				</td>
			</tr>
			<c:if test="${null!=page}">
				<tr>
					<td align="center">
						<div class="my_page">
							<div id="page_wrapper" class="page">
								${page}
							</div>
						</div>
					</td>
				</tr>
			</c:if>
		</table>
	</body>
</html>
