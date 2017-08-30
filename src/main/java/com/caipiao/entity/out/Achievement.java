package com.caipiao.entity.out;

/**
 * Created by liujie on 2017/8/21.
 */
public class Achievement {

    private String agent;
    private String username;
    private String money;
    private Double rech;
    private Double give;
    private Double draw;
    private Double profit;
    private Double netProfit;

    public Achievement() {
    }

    public String getAgent() {
        return this.agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getMoney() {
        return this.money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Double getRech() {
        return this.rech;
    }

    public void setRech(Double rech) {
        this.rech = rech;
    }

    public Double getGive() {
        return this.give;
    }

    public void setGive(Double give) {
        this.give = give;
    }

    public Double getDraw() {
        return this.draw;
    }

    public void setDraw(Double draw) {
        this.draw = draw;
    }

    public Double getProfit() {
        return this.profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Double getNetProfit() {
        return this.netProfit;
    }

    public void setNetProfit(Double netProfit) {
        this.netProfit = netProfit;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
