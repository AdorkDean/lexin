
//    XSSFilter.java

package com.sysbcjzh.utils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

// Referenced classes of package com.sysbcjzh.utils:
//			XSSRequestWrapper

public class XSSFilter
	implements Filter
{

	public XSSFilter()
	{
	}

	public void destroy()
	{
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
		throws IOException, ServletException
	{
		arg2.doFilter(new XSSRequestWrapper((HttpServletRequest)arg0), arg1);
	}

	public void init(FilterConfig filterconfig)
		throws ServletException
	{
	}
}
