package com.caipiao.data.listion;

import com.caipiao.utils.TimeUtil;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.ParseException;
import java.util.Date;

public class QuartzManager
{

	private static String JOB_GROUP_NAME = "group1";
	private static String TRIGGER_GROUP_NAME = "trigger1";
	private static SchedulerFactory sf = new StdSchedulerFactory();

	public QuartzManager()
	{
	}

	public static void addJob(String jobName, Job job, String time)
		throws SchedulerException, ParseException
	{
		System.out.println((new StringBuilder(String.valueOf(time))).append(" ").append(jobName).append(" 定时任务开启").toString());
		Scheduler sched = sf.getScheduler();
		JobDetail jobDetail = new JobDetail(jobName, JOB_GROUP_NAME, job.getClass());
		CronTrigger trigger = new CronTrigger(jobName, TRIGGER_GROUP_NAME);
		trigger.setCronExpression(time);
		sched.scheduleJob(jobDetail, trigger);
		if (!sched.isShutdown())
			sched.start();
	}

	public static void addSimpleJob(String jobName, Job job, long btime, long etime, int ms)
		throws SchedulerException, ParseException
	{
		System.out.println((new StringBuilder(String.valueOf(TimeUtil.getToday("yyyy-MM-dd HH:mm:ss")))).append(" ").append(jobName).append(" 定时任务开启").toString());
		Scheduler sched = sf.getScheduler();
		JobDetail jobDetail = new JobDetail(jobName, JOB_GROUP_NAME, job.getClass());
		Date bt = null;
		Date et = null;
		if (btime != 0L)
			bt = new Date(btime);
		if (etime != 0L)
			et = new Date(etime);
		SimpleTrigger trigger = new SimpleTrigger("myTrigger", null, bt, et, -1, (long)ms * 1000L);
		sched.scheduleJob(jobDetail, trigger);
		if (!sched.isShutdown())
			sched.start();
	}

	public static void modifyJobTime(String jobName, String time)
		throws SchedulerException, ParseException
	{
		Scheduler sched = sf.getScheduler();
		Trigger trigger = sched.getTrigger(jobName, TRIGGER_GROUP_NAME);
		if (trigger != null)
		{
			CronTrigger ct = (CronTrigger)trigger;
			ct.setCronExpression(time);
			sched.resumeTrigger(jobName, TRIGGER_GROUP_NAME);
		}
	}

	public static void restartJobTime(JobDetail jobDetail, String time)
		throws SchedulerException, ParseException
	{
		Scheduler sched = sf.getScheduler();
		Trigger trigger = sched.getTrigger(jobDetail.getName(), TRIGGER_GROUP_NAME);
		if (trigger != null)
		{
			CronTrigger ct = (CronTrigger)trigger;
			sched.deleteJob(jobDetail.getName(), jobDetail.getGroup());
			ct.setCronExpression(time);
			System.out.println((new StringBuilder("CronTrigger getName ")).append(ct.getJobName()).toString());
			sched.scheduleJob(jobDetail, ct);
		}
	}

	public static void removeJob(String jobName)
		throws SchedulerException
	{
		Scheduler sched = sf.getScheduler();
		sched.pauseTrigger(jobName, TRIGGER_GROUP_NAME);
		sched.unscheduleJob(jobName, TRIGGER_GROUP_NAME);
		sched.deleteJob(jobName, JOB_GROUP_NAME);
	}

	public static void startJobs()
	{
		try
		{
			Scheduler sched = sf.getScheduler();
			sched.start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static void shutdownJobs()
	{
		try
		{
			Scheduler sched = sf.getScheduler();
			if (!sched.isShutdown())
				sched.shutdown();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
