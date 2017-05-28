package com.caipiao.data.listion;

import com.caipiao.data.open.MethodHemai;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TaskHemai
		implements Job
{
	private int isopen = 0;
	public String time = "0/15 * * * * ?";

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		if (this.isopen == 0) {
			this.isopen = 1;
			try {
				new MethodHemai().Instance();
			} finally {
				this.isopen = 0;
			}
		} else {
			System.out.println("合买验证任务正在运行。");
		}
	}
}