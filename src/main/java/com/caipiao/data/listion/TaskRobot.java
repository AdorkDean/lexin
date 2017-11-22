package com.caipiao.data.listion;

import com.caipiao.service.lottery.BuyLotService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 任务机器人
 * <p>
 * 专门针对合买不能成单的合买，通过机器人撮合成功，从而提高成单率，提高收益。
 * <p>
 * Created by nicholas on 2017/7/20.
 */
public class TaskRobot implements Job {

    private int isopen = 0;
    public String time = "0/5 * * * * ?";

    private BuyLotService buyLotService = new BuyLotService();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        if (this.isopen == 0) {
            this.isopen = 1;
            try {


            } finally {
                this.isopen = 0;
            }
        } else {
            System.out.println("机器人正在运行。");
        }

    }
}
