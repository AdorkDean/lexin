
//    HttpRequest.java

package com.caipiao.pay.alipay.httpClient;

import org.apache.commons.httpclient.NameValuePair;

// Referenced classes of package com.caipiao.pay.alipay.httpClient:
//			HttpResultType

public class HttpRequest
{

	public static final String METHOD_GET = "GET";
	public static final String METHOD_POST = "POST";
	private String url;
	private String method;
	private int timeout;
	private int connectionTimeout;
	private NameValuePair parameters[];
	private String queryString;
	private String charset;
	private String clientIp;
	private HttpResultType resultType;

	public HttpRequest(HttpResultType resultType)
	{
		url = null;
		method = "POST";
		timeout = 0;
		connectionTimeout = 0;
		parameters = null;
		queryString = null;
		charset = "GBK";
		this.resultType = HttpResultType.BYTES;
		this.resultType = resultType;
	}

	public String getClientIp()
	{
		return clientIp;
	}

	public void setClientIp(String clientIp)
	{
		this.clientIp = clientIp;
	}

	public NameValuePair[] getParameters()
	{
		return parameters;
	}

	public void setParameters(NameValuePair parameters[])
	{
		this.parameters = parameters;
	}

	public String getQueryString()
	{
		return queryString;
	}

	public void setQueryString(String queryString)
	{
		this.queryString = queryString;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getMethod()
	{
		return method;
	}

	public void setMethod(String method)
	{
		this.method = method;
	}

	public int getConnectionTimeout()
	{
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout)
	{
		this.connectionTimeout = connectionTimeout;
	}

	public int getTimeout()
	{
		return timeout;
	}

	public void setTimeout(int timeout)
	{
		this.timeout = timeout;
	}

	public String getCharset()
	{
		return charset;
	}

	public void setCharset(String charset)
	{
		this.charset = charset;
	}

	public HttpResultType getResultType()
	{
		return resultType;
	}

	public void setResultType(HttpResultType resultType)
	{
		this.resultType = resultType;
	}
}
