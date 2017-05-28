

package com.caipiao.data.listion;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class OnlineUserListener
	implements HttpSessionAttributeListener
{

	public OnlineUserListener()
	{
	}

	public void attributeAdded(HttpSessionBindingEvent se)
	{
		System.out.println((new StringBuilder("getSession:")).append(se.getSession()).append(",getName:").append(se.getName()).append(",getSource:").append(se.getSource()).append(",getValue:").append(se.getValue()).toString());
		HttpSession session = se.getSession();
		System.out.println((new StringBuilder("session+1:id=")).append(session.getId()).append(",getCreatime").append(session.getCreationTime()).append(",getAName:").append(session.getAttributeNames()).toString());
	}

	public void attributeRemoved(HttpSessionBindingEvent se) 
	{
		System.out.println((new StringBuilder("getSession:")).append(se.getSession()).append(",getName:").append(se.getName()).append(",getSource:").append(se.getSource()).append(",getValue:").append(se.getValue()).toString());
		HttpSession session = se.getSession();
		System.out.println((new StringBuilder("session-1:id=")).append(session.getId()).append(",getCreatime").append(session.getCreationTime()).append(",getAName:").append(session.getAttributeNames()).toString());
	}

	public void attributeReplaced(HttpSessionBindingEvent se)
	{
		System.out.println((new StringBuilder("getSession:")).append(se.getSession()).append(",getName:").append(se.getName()).append(",getSource:").append(se.getSource()).append(",getValue:").append(se.getValue()).toString());
		HttpSession session = se.getSession();
		System.out.println((new StringBuilder("session-replace:id=")).append(session.getId()).append(",getCreatime").append(session.getCreationTime()).append(",getAName:").append(session.getAttributeNames()).toString());
	}
}
