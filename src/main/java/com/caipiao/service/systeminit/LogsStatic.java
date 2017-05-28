
//    LogsStatic.java

package com.caipiao.service.systeminit;

import com.caipiao.entity.Bc_logs;
import com.caipiao.intface.Bc_logsIntface;
import com.caipiao.intfaceImpl.LogsIntfaceImpl;
import com.caipiao.utils.TimeUtil;
import java.util.List;

public class LogsStatic
{

	static Bc_logsIntface dao = new LogsIntfaceImpl();

	public LogsStatic()
	{
	}

	public static void AddLogs(int userid, String name, int type, int level, String ip, String des)
	{
		Bc_logs en = new Bc_logs();
		en.setLogs_desc(des);
		en.setLogs_ip(ip);
		en.setLogs_level(level);
		en.setLogs_time(TimeUtil.getToday("yyyy-MM-dd HH:mm:ss"));
		en.setLogs_type(type);
		en.setUser_id(userid);
		en.setUser_name(name);
		dao.add(en);
	}

	public static int FindError(int userid, int level)
	{
		return dao.findError(userid, level);
	}

	public static List findsLogs(int userid, String btime, String etime, int type, int level, int start, int limit)
	{
		return dao.finds(userid, btime, etime, type, level, start, limit);
	}

	public static int findsLogsCount(int userid, String btime, String etime, int type, int level)
	{
		return dao.findscount(userid, btime, etime, type, level);
	}

}
