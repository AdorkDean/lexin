package com.caipiao.data.service;

import com.caipiao.intface.Bc_phbIntface;
import com.caipiao.intfaceImpl.PhbIntfaceImpl;

public class DMService {

    static Bc_phbIntface phbdao = new PhbIntfaceImpl();

    public DMService() {
    }

    public static void DayInstance() {
        try {
            Thread.sleep(100000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        phbdao.updateinit(0);
    }

    public static void MonthInstance() {
        try {
            Thread.sleep(200000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        phbdao.updateinit(1);
    }

}
