
//    UserIntfaceImpl.java

package com.caipiao.intfaceImpl;

import com.caipiao.entity.*;
import com.caipiao.entity.out.UserOut;
import com.caipiao.intface.Bc_userIntface;
import com.sysbcjzh.mysql.Mysql;
import com.sysbcjzh.utils.StringUtils;
import java.util.*;

public class UserIntfaceImpl
	implements Bc_userIntface
{

	Mysql dao;

	public UserIntfaceImpl()
	{
		dao = Mysql.getInstance();
	}

	public boolean add(Bc_user en)
	{
		return dao.add(en);
	}

	public boolean add(Bc_detail en)
	{
		return dao.add(en);
	}

	public boolean add(Bc_point en)
	{
		return dao.add(en);
	}

	public boolean add(Bc_comm en)
	{
		return dao.add(en);
	}

	public boolean add(Bc_phb en)
	{
		return dao.add(en);
	}

	public boolean delete(int User_id)
	{
		return dao.delete("delete from Bc_user where User_id=?", new Object[] {
			Integer.valueOf(User_id)
		});
	}

	public boolean delete(String User_name)
	{
		return dao.delete("delete from Bc_user where User_name=?", new Object[] {
			User_name
		});
	}

	public boolean update(int User_id, Map map)
	{
		return dao.updateMap("update Bc_user set ", " where User_id=?", map, new Object[] {
			Integer.valueOf(User_id)
		});
	}

	public boolean update(String User_name, Map map)
	{
		return dao.updateMap("update Bc_user set ", " where User_name=?", map, new Object[] {
			User_name
		});
	}

	public Bc_user find(int User_id)
	{
		return (Bc_user)dao.find("select * from Bc_user where User_id=?", Bc_user.class, new Object[] {
			Integer.valueOf(User_id)
		});
	}

	public Bc_user find(String user_name)
	{
		return (Bc_user)dao.find("select * from Bc_user where User_name=?", Bc_user.class, new Object[] {
			user_name
		});
	}

	public boolean EmailIsExist(String email)
	{
		return dao.isExist("select count(User_email) from Bc_user where User_email=?", new Object[] {
			email
		});
	}

	public boolean NameIsExist(String name)
	{
		return dao.isExist("select count(User_name) from Bc_user where User_name=?", new Object[] {
			name
		});
	}

	public boolean MoneyToDongjie(int user_id, double money)
	{
		return dao.update("update Bc_user set User_money=User_money-?,User_dong=User_dong+? where User_id=?", new Object[] {
			Double.valueOf(money), Double.valueOf(money), Integer.valueOf(user_id)
		});
	}

	public boolean addMoney(int user_id, double money, int red, int point, double show)
	{
		ArrayList list = new ArrayList();
		String sql = "update Bc_user set ";
		if (money != 0.0D)
		{
			sql = (new StringBuilder(String.valueOf(sql))).append("User_money=User_money+?,").toString();
			list.add(Double.valueOf(money));
		}
		if (red != 0)
		{
			sql = (new StringBuilder(String.valueOf(sql))).append("User_red=User_red+?,").toString();
			list.add(Integer.valueOf(red));
		}
		if (point != 0)
		{
			sql = (new StringBuilder(String.valueOf(sql))).append("User_point=User_point+?,").toString();
			list.add(Integer.valueOf(point));
		}
		if (show > 0.0D)
		{
			sql = (new StringBuilder(String.valueOf(sql))).append("User_show=User_show+?,").toString();
			list.add(Double.valueOf(show));
		}
		sql = sql.substring(0, sql.length() - 1);
		sql = (new StringBuilder(String.valueOf(sql))).append(" where User_id=?").toString();
		list.add(Integer.valueOf(user_id));
		return dao.update(sql, list.toArray());
	}

	public boolean addMoney(int user_id, double money, int point)
	{
		ArrayList list = new ArrayList();
		String sql = "update Bc_user set User_money=User_money+?,User_level=User_level+?,";
		list.add(Double.valueOf(money));
		list.add(Integer.valueOf((int)money / 10));
		if (point != 0)
		{
			sql = (new StringBuilder(String.valueOf(sql))).append("User_point=User_point+?,").toString();
			list.add(Integer.valueOf(point));
		}
		sql = sql.substring(0, sql.length() - 1);
		sql = (new StringBuilder(String.valueOf(sql))).append(" where User_id=?").toString();
		list.add(Integer.valueOf(user_id));
		return dao.update(sql, list.toArray());
	}

	public boolean DongSub(int user_id, double money)
	{
		return dao.update("update Bc_user set User_dong=User_dong-? where User_id=?", new Object[] {
			Double.valueOf(money), Integer.valueOf(user_id)
		});
	}

	public boolean DongToMoney(int user_id, double money)
	{
		return dao.update("update Bc_user set User_money=User_money+?,User_dong=User_dong-? where User_id=?", new Object[] {
			Double.valueOf(money), Double.valueOf(money), Integer.valueOf(user_id)
		});
	}

	public List findlist(String name, String realname, double yue, int type, int stauts, int uid, 
			String btime, String etime, int strat, int limit)
	{
		String sql = "select a.*,b.User_name as User_upname";
		sql = (new StringBuilder(String.valueOf(sql))).append(" from Bc_user a LEFT JOIN Bc_user b on a.User_upid=b.User_id where").toString();
		ArrayList list = new ArrayList();
		if (yue > 0.0D)
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and a.User_money>=?").toString();
			list.add(Double.valueOf(yue));
		}
		if (StringUtils.isNotBlank(name))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and a.User_name like ?").toString();
			list.add((new StringBuilder("%")).append(name).append("%").toString());
		}
		if (StringUtils.isNotBlank(realname))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and a.User_realname like ?").toString();
			list.add((new StringBuilder("%")).append(realname).append("%").toString());
		}
		if (-1 != type)
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and a.User_type=?").toString();
			list.add(Integer.valueOf(type));
		}
		if (-1 != stauts)
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and a.User_status=?").toString();
			list.add(Integer.valueOf(stauts));
		}
		if (uid > 0)
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and a.User_upid=?").toString();
			list.add(Integer.valueOf(uid));
		}
		if (StringUtils.isNotEmptyAll(new String[] {
	btime, etime
}))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and a.User_regtime>? and a.User_regtime<?").toString();
			list.add((new StringBuilder(String.valueOf(btime))).append(" 00:00:00").toString());
			list.add((new StringBuilder(String.valueOf(etime))).append(" 24:00:00").toString());
		}
		sql = (new StringBuilder(String.valueOf(sql))).append(" order by a.User_regtime desc limit ?,?").toString();
		list.add(Integer.valueOf(strat));
		list.add(Integer.valueOf(limit));
		sql = sql.replace("where and", "where");
		sql = sql.replace("where order", "order");
		return dao.finds(sql, UserOut.class, list.toArray());
	}

	public int findlistCount(String name, String realname, double yue, int type, int stauts, int uid, 
			String btime, String etime)
	{
		String sql = "select count(User_id) from Bc_user where";
		ArrayList list = new ArrayList();
		if (yue > 0.0D)
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and User_money>=?").toString();
			list.add(Double.valueOf(yue));
		}
		if (StringUtils.isNotBlank(name))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and User_name like ?").toString();
			list.add((new StringBuilder("%")).append(name).append("%").toString());
		}
		if (StringUtils.isNotBlank(realname))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and User_realname like ?").toString();
			list.add((new StringBuilder("%")).append(realname).append("%").toString());
		}
		if (-1 != type)
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and User_type=?").toString();
			list.add(Integer.valueOf(type));
		}
		if (-1 != stauts)
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and User_status=?").toString();
			list.add(Integer.valueOf(stauts));
		}
		if (uid > 0)
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and User_upid=?").toString();
			list.add(Integer.valueOf(uid));
		}
		if (StringUtils.isNotEmptyAll(new String[] {
	btime, etime
}))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and User_regtime>? and User_regtime<?").toString();
			list.add((new StringBuilder(String.valueOf(btime))).append(" 00:00:00").toString());
			list.add((new StringBuilder(String.valueOf(etime))).append(" 24:00:00").toString());
		}
		sql = sql.replace("where and", "where");
		if (sql.endsWith("where"))
			sql = sql.replace(" where", "");
		return dao.getCount(sql, list.toArray());
	}

	public boolean updatetimeip(int user_id, String time, String ip)
	{
		return dao.update("update Bc_user set User_lgtimeold=User_lgtime,User_lgipold=User_lgip,User_lgip=?,User_lgtime=? where User_id=?", new Object[] {
			ip, time, Integer.valueOf(user_id)
		});
	}

	public boolean updateUserPass(int user_id, String pass)
	{
		return dao.update("update Bc_user set User_pass=?,User_paypass=? where User_id=?", new Object[] {
			pass, pass, Integer.valueOf(user_id)
		});
	}

	public List findInlist(int User_id, int cus, String btime, String etime, int start, int limit)
	{
		String sql = "select * from Bc_user where User_upid=?";
		ArrayList list = new ArrayList();
		list.add(Integer.valueOf(User_id));
		if (cus == 0)
			sql = (new StringBuilder(String.valueOf(sql))).append(" and User_money>0").toString();
		else
		if (1 == cus)
			sql = (new StringBuilder(String.valueOf(sql))).append(" and User_money<=0").toString();
		if (StringUtils.isNotEmptyAll(new String[] {
	btime, etime
}))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and User_regtime>? and User_regtime<?").toString();
			list.add((new StringBuilder(String.valueOf(btime))).append(" 00:00:00").toString());
			list.add((new StringBuilder(String.valueOf(etime))).append(" 24:00:00").toString());
		}
		sql = (new StringBuilder(String.valueOf(sql))).append(" order by User_regtime desc limit ?,?").toString();
		list.add(Integer.valueOf(start));
		list.add(Integer.valueOf(limit));
		return dao.finds(sql, Bc_user.class, list.toArray());
	}

	public int findInlistCount(int User_id, int cus, String btime, String etime)
	{
		String sql = "select count(User_id) from Bc_user where User_upid=?";
		ArrayList list = new ArrayList();
		list.add(Integer.valueOf(User_id));
		if (cus == 0)
			sql = (new StringBuilder(String.valueOf(sql))).append(" and User_money>0").toString();
		else
		if (1 == cus)
			sql = (new StringBuilder(String.valueOf(sql))).append(" and User_money<=0").toString();
		if (StringUtils.isNotEmptyAll(new String[] {
	btime, etime
}))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and User_regtime>? and User_regtime<?").toString();
			list.add((new StringBuilder(String.valueOf(btime))).append(" 00:00:00").toString());
			list.add((new StringBuilder(String.valueOf(etime))).append(" 24:00:00").toString());
		}
		return dao.getCount(sql, list.toArray());
	}
}
