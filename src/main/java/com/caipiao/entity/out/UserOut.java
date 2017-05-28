
//    UserOut.java

package com.caipiao.entity.out;

import com.caipiao.utils.UserSession;

public class UserOut
{

	private int User_id;
	private String User_name;
	private String User_pass;
	private String User_paypass;
	private double User_money;
	private double User_dong;
	private double User_red;
	private int User_point;
	private int User_level;
	private double User_show;
	private String User_word;
	private int User_sex;
	private String User_birth;
	private String User_regtime;
	private String User_lgtime;
	private String User_lgtimeold;
	private String User_lgip;
	private String User_lgipold;
	private String User_liveadd;
	private String User_address;
	private int User_phonecheck;
	private String User_phone;
	private int User_zipcheck;
	private String User_zip;
	private String User_realname;
	private String User_aqasking;
	private String User_aqanswer;
	private String User_email;
	private int User_emailcheck;
	private String User_qq;
	private String User_image;
	private int User_type;
	private int User_status;
	private int User_upid;
	private String User_upname;

	public UserOut()
	{
	}

	public double getUser_show()
	{
		return User_show;
	}

	public void setUser_show(double user_show)
	{
		User_show = user_show;
	}

	public int getUser_id()
	{
		return User_id;
	}

	public void setUser_id(int user_id)
	{
		User_id = user_id;
	}

	public String getUser_name()
	{
		return User_name;
	}

	public void setUser_name(String user_name)
	{
		User_name = user_name;
	}

	public String getUser_pass()
	{
		return User_pass;
	}

	public void setUser_pass(String user_pass)
	{
		User_pass = user_pass;
	}

	public String getUser_paypass()
	{
		return User_paypass;
	}

	public void setUser_paypass(String user_paypass)
	{
		User_paypass = user_paypass;
	}

	public double getUser_money()
	{
		return User_money;
	}

	public void setUser_money(double user_money)
	{
		User_money = user_money;
	}

	public double getUser_dong()
	{
		return User_dong;
	}

	public void setUser_dong(double user_dong)
	{
		User_dong = user_dong;
	}

	public double getUser_red()
	{
		return User_red;
	}

	public void setUser_red(double user_red)
	{
		User_red = user_red;
	}

	public int getUser_point()
	{
		return User_point;
	}

	public void setUser_point(int user_point)
	{
		User_point = user_point;
	}

	public int getUser_level()
	{
		return UserSession.getLevel(User_level);
	}

	public void setUser_level(int user_level)
	{
		User_level = user_level;
	}

	public String getUser_word()
	{
		return User_word;
	}

	public void setUser_word(String user_word)
	{
		User_word = user_word;
	}

	public int getUser_sex()
	{
		return User_sex;
	}

	public void setUser_sex(int user_sex)
	{
		User_sex = user_sex;
	}

	public String getUser_birth()
	{
		return User_birth;
	}

	public void setUser_birth(String user_birth)
	{
		User_birth = user_birth;
	}

	public String getUser_regtime()
	{
		return User_regtime;
	}

	public void setUser_regtime(String user_regtime)
	{
		User_regtime = user_regtime;
	}

	public String getUser_lgtime()
	{
		return User_lgtime;
	}

	public void setUser_lgtime(String user_lgtime)
	{
		User_lgtime = user_lgtime;
	}

	public String getUser_lgtimeold()
	{
		return User_lgtimeold;
	}

	public void setUser_lgtimeold(String user_lgtimeold)
	{
		User_lgtimeold = user_lgtimeold;
	}

	public String getUser_lgip()
	{
		return User_lgip;
	}

	public void setUser_lgip(String user_lgip)
	{
		User_lgip = user_lgip;
	}

	public String getUser_lgipold()
	{
		return User_lgipold;
	}

	public void setUser_lgipold(String user_lgipold)
	{
		User_lgipold = user_lgipold;
	}

	public String getUser_liveadd()
	{
		return User_liveadd;
	}

	public void setUser_liveadd(String user_liveadd)
	{
		User_liveadd = user_liveadd;
	}

	public String getUser_address()
	{
		return User_address;
	}

	public void setUser_address(String user_address)
	{
		User_address = user_address;
	}

	public int getUser_phonecheck()
	{
		return User_phonecheck;
	}

	public void setUser_phonecheck(int user_phonecheck)
	{
		User_phonecheck = user_phonecheck;
	}

	public String getUser_phone()
	{
		return User_phone;
	}

	public void setUser_phone(String user_phone)
	{
		User_phone = user_phone;
	}

	public int getUser_zipcheck()
	{
		return User_zipcheck;
	}

	public void setUser_zipcheck(int user_zipcheck)
	{
		User_zipcheck = user_zipcheck;
	}

	public String getUser_zip()
	{
		return User_zip;
	}

	public void setUser_zip(String user_zip)
	{
		User_zip = user_zip;
	}

	public String getUser_realname()
	{
		return User_realname;
	}

	public void setUser_realname(String user_realname)
	{
		User_realname = user_realname;
	}

	public String getUser_aqasking()
	{
		return User_aqasking;
	}

	public void setUser_aqasking(String user_aqasking)
	{
		User_aqasking = user_aqasking;
	}

	public String getUser_aqanswer()
	{
		return User_aqanswer;
	}

	public void setUser_aqanswer(String user_aqanswer)
	{
		User_aqanswer = user_aqanswer;
	}

	public String getUser_email()
	{
		return User_email;
	}

	public void setUser_email(String user_email)
	{
		User_email = user_email;
	}

	public int getUser_emailcheck()
	{
		return User_emailcheck;
	}

	public void setUser_emailcheck(int user_emailcheck)
	{
		User_emailcheck = user_emailcheck;
	}

	public String getUser_qq()
	{
		return User_qq;
	}

	public void setUser_qq(String user_qq)
	{
		User_qq = user_qq;
	}

	public String getUser_image()
	{
		return User_image;
	}

	public void setUser_image(String user_image)
	{
		User_image = user_image;
	}

	public int getUser_type()
	{
		return User_type;
	}

	public void setUser_type(int user_type)
	{
		User_type = user_type;
	}

	public int getUser_status()
	{
		return User_status;
	}

	public void setUser_status(int user_status)
	{
		User_status = user_status;
	}

	public int getUser_upid()
	{
		return User_upid;
	}

	public void setUser_upid(int user_upid)
	{
		User_upid = user_upid;
	}

	public String getUser_upname()
	{
		return User_upname;
	}

	public void setUser_upname(String user_upname)
	{
		User_upname = user_upname;
	}
}
