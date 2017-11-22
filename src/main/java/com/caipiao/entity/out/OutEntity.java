//    OutEntity.java

package com.caipiao.entity.out;


public class OutEntity {

    private String Buy_item;
    private double Buy_money;
    private int Buylot_id;
    private int Buylot_status;
    private double Buylot_money;
    private int Buy_ishm;
    private String Buy_lot;
    private String Lot_etime;
    private String Buylot_qihao;

    public OutEntity() {
    }

    public String getBuylot_qihao() {
        return Buylot_qihao;
    }

    public void setBuylot_qihao(String buylot_qihao) {
        Buylot_qihao = buylot_qihao;
    }

    public String getBuy_lot() {
        return Buy_lot;
    }

    public void setBuy_lot(String buy_lot) {
        Buy_lot = buy_lot;
    }

    public String getLot_etime() {
        return Lot_etime;
    }

    public void setLot_etime(String lot_etime) {
        Lot_etime = lot_etime;
    }

    public int getBuy_ishm() {
        return Buy_ishm;
    }

    public void setBuy_ishm(int buy_ishm) {
        Buy_ishm = buy_ishm;
    }

    public double getBuy_money() {
        return Buy_money;
    }

    public void setBuy_money(double buy_money) {
        Buy_money = buy_money;
    }

    public double getBuylot_money() {
        return Buylot_money;
    }

    public void setBuylot_money(double buylot_money) {
        Buylot_money = buylot_money;
    }

    public String getBuy_item() {
        return Buy_item;
    }

    public void setBuy_item(String buy_item) {
        Buy_item = buy_item;
    }

    public int getBuylot_id() {
        return Buylot_id;
    }

    public void setBuylot_id(int buylot_id) {
        Buylot_id = buylot_id;
    }

    public int getBuylot_status() {
        return Buylot_status;
    }

    public void setBuylot_status(int buylot_status) {
        Buylot_status = buylot_status;
    }
}
