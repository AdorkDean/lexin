
//    EmailUtils.java

package com.sysbcjzh.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EmailUtils
{

	public static final String HOST = "smtp.exmail.qq.com";
	public static final String PROTOCOL = "smtp";
	public static final int PORT = 25;
	public static final String FROM = "123456@qq.com";
	public static final String PWD = "725a347bcjzh520q";

	public EmailUtils()
	{
	}

	public static void SendEmail(String toEmail, String title, String content)
	{
		Session getSession = GetSession();
		MimeMessage msg = new MimeMessage(getSession);
		try
		{
			msg.setFrom(new InternetAddress("123456@qq.com"));
			InternetAddress add[] = {
				new InternetAddress(toEmail)
			};
			msg.setRecipients(Message.RecipientType.TO, add);
			msg.setSubject(title);
			msg.setSentDate(new Date());
			msg.setContent(content, "text/html;charset=utf-8");
			Transport.send(msg);
		}
		catch (MessagingException e)
		{
			e.printStackTrace();
		}
	}

	private static Session GetSession()
	{
		Properties p = new Properties();
		p.put("mail.smtp.host", "smtp.exmail.qq.com");
		p.put("mail.store.protocol", "smtp");
		p.put("mail.smtp.port", Integer.valueOf(25));
		p.put("mail.smtp.auth", Boolean.valueOf(true));
		Authenticator auth = new Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication("123456@qq.com", "725a347bcjzh520q");
			}

		};
		Session session = Session.getDefaultInstance(p, auth);
		return session;
	}

	public static void main(String args[])
	{
		SendEmail("123456@qq.com", "title", "cs");
	}
}
