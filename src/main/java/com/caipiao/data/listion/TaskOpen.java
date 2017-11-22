package com.caipiao.data.listion;

import com.caipiao.data.open.OpenService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TaskOpen
        implements Job {
    private int isopen = 0;
    public String time = "0/10 * * * * ?";

    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        if (this.isopen == 0) {
            this.isopen = 1;
            try {
                new OpenService();
            } finally {
                this.isopen = 0;
            }
        } else {
            System.out.println("派奖任务正在运行。");
        }
    }
}