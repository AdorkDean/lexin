package com.caipiao.pay.mobao;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class QueryResponseEntity
{
	protected String respCode;
	protected String respDesc;
	protected String accDate;
	protected String accNo;
	protected String orderNo;
	protected String status;
	protected String signMsg;
	private static Map<String, String> ORDER_STATUS = new HashMap();

	static {
		ORDER_STATUS.put("0", "未支付");
		ORDER_STATUS.put("1", "成功");
		ORDER_STATUS.put("2", "失败");
		ORDER_STATUS.put("4", "部分退款");
		ORDER_STATUS.put("5", "全额退款");
		ORDER_STATUS.put("9", "退款处理中");
		ORDER_STATUS.put("10", "未支付");
		ORDER_STATUS.put("11", "订单过期");
	}

	public String getRespCode()
	{
		return this.respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespDesc() {
		return this.respDesc;
	}

	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}

	public String getAccDate() {
		return this.accDate;
	}

	public void setAccDate(String accDate) {
		this.accDate = accDate;
	}

	public String getAccNo() {
		return this.accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSignMsg() {
		return this.signMsg;
	}

	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}

	public void parse(String respStr)
			throws Exception
	{
		Map resultMap = new HashMap();
		UtilXMLParser.parse(respStr, resultMap);
		Document doc = DocumentHelper.parseText(respStr);
		Element root = doc.getRootElement();
		Element respData = root.element("respData");
		String srcData = respData.asXML();
		this.respCode = ((String)resultMap.get("/moboAccount/respData/respCode"));
		if (StringUtils.isBlank(this.respCode)) {
			throw new Exception("响应信息格式错误：不存在'respCode'节点。");
		}
		this.respDesc = ((String)resultMap.get("/moboAccount/respData/respDesc"));
		if (StringUtils.isBlank(this.respDesc)) {
			throw new Exception("响应信息格式错误：不存在'respDesc'节点");
		}
		if ("00".equalsIgnoreCase(this.respCode)) {
			this.accDate = ((String)resultMap.get("/moboAccount/respData/accDate"));
			if (StringUtils.isBlank(this.accDate)) {
				throw new Exception("响应信息格式错误：不存在'accDate'节点。");
			}
			this.accNo = ((String)resultMap.get("/moboAccount/respData/accNo"));
			if (StringUtils.isBlank(this.accNo)) {
				throw new Exception("响应信息格式错误：不存在'accNo'节点。");
			}
			this.orderNo = ((String)resultMap.get("/moboAccount/respData/orderNo"));
			if (StringUtils.isBlank(this.orderNo)) {
				throw new Exception("响应信息格式错误：不存在'orderNo'节点。");
			}
			this.status = ((String)resultMap.get("/moboAccount/respData/Status"));
			if (StringUtils.isBlank(this.status)) {
				throw new Exception("响应信息格式错误：不存在'status'节点。");
			}
			this.status = ((String)ORDER_STATUS.get(this.status));
		}

		this.signMsg = ((String)resultMap.get("/moboAccount/signMsg"));
		if (StringUtils.isBlank(this.signMsg)) {
			throw new Exception("响应信息格式错误：不存在'signMsg'节点");
		}
		if (!Mobo360SignUtil.verifyData(getSignMsg(), srcData))
		{
			throw new Exception("签名验证不通过");
		}
	}
}