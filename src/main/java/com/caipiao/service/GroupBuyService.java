//    GroupBuyService.java

package com.caipiao.service;

import com.caipiao.intface.Bc_buyIntface;
import com.caipiao.intfaceImpl.BuyIntfaceImpl;

import java.util.List;

public class GroupBuyService {

    Bc_buyIntface buydao;

    public GroupBuyService() {
        buydao = new BuyIntfaceImpl();
    }

    public List findsHM(String name, String lot, int isbao, int istake, int status, int start, int limit) {
        return buydao.findsBuyForHM(name, lot, isbao, istake, status, start, limit);
    }

    public int findsHMCount(String name, String lot, int isbao, int istake, int status) {
        return buydao.findsBuyForHMCount(name, lot, isbao, istake, status);
    }
}
