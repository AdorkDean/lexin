package com.caipiao.data.listion;

import com.caipiao.data.open.NumberService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TaskCrawler
		implements Job
{
	private int isopen = 0;
	public String time = "0/15 * * * * ?";

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		if (this.isopen == 0) {
			this.isopen = 1;
			try {
				new NumberService().Instance();
			} finally {
				this.isopen = 0;
			}
		} else {
			System.out.println("开奖号码任务正在运行。");
		}
	}
}