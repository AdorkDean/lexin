package com.caipiao.data.open;

import com.caipiao.entity.Bc_lottery;
import com.caipiao.intface.Bc_lotteryIntface;
import com.caipiao.intfaceImpl.LotteryIntfaceImpl;
import com.sysbcjzh.utils.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class OpenService {

    static final int open = 1;
    Bc_lotteryIntface dao;

    public OpenService() {
        dao = new LotteryIntfaceImpl();
        List list = dao.findHaveWithOpen(0);
        if (list != null) {
            for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
                Bc_lottery b = (Bc_lottery) iterator.next();
                String lot_name = b.getLot_name();
                String lot_qihao = b.getLot_qihao();
                String lot_haoma = b.getLot_haoma();
                UpLotteryStatus(lot_name, lot_qihao);
                if (StringUtils.isNotEmptyAll(new String[]{
                        lot_haoma, lot_name, lot_qihao
                })) {
                    OpenThread openThread = new OpenThread(lot_name, lot_qihao, lot_haoma);
                    openThread.start();
                }
            }

        }
    }

    /**
     * 修改彩票状态为1 开奖状态
     *
     * @param lot
     * @param qihao
     */
    private void UpLotteryStatus(String lot, String qihao) {
        HashMap map = new HashMap();
        map.put("Lot_isopen", Integer.valueOf(1));
        dao.update(lot, qihao, map);
    }
}
