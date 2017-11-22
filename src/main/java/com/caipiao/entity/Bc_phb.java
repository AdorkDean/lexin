//    Bc_phb.java

package com.caipiao.entity;


public class Bc_phb {

    private int Phb_id;
    private int User_id;
    private String User_name;
    private double Phb_total;
    private double Phb_all;
    private double Phb_hmall;
    private double Phb_month;
    private double Phb_hmmonth;
    private double Phb_day;
    private double Phb_hmday;
    private double Phb_total_c;
    private double Phb_all_c;
    private double Phb_hmall_c;
    private double Phb_month_c;
    private double Phb_hmmonth_c;
    private double Phb_day_c;
    private double Phb_hmday_c;
    private String Phb_type;

    public Bc_phb() {
    }

    public void setAll(double phb) {
        Phb_total = Phb_all = Phb_hmall = Phb_month = Phb_hmmonth = Phb_day = Phb_hmday = phb;
        Phb_total_c = Phb_all_c = Phb_hmall_c = Phb_month_c = Phb_hmmonth_c = Phb_day_c = Phb_hmday_c = phb;
    }

    public double getPhb_total_c() {
        return Phb_total_c;
    }

    public void setPhb_total_c(double phb_total_c) {
        Phb_total_c = phb_total_c;
    }

    public double getPhb_all_c() {
        return Phb_all_c;
    }

    public void setPhb_all_c(double phb_all_c) {
        Phb_all_c = phb_all_c;
    }

    public double getPhb_hmall_c() {
        return Phb_hmall_c;
    }

    public void setPhb_hmall_c(double phb_hmall_c) {
        Phb_hmall_c = phb_hmall_c;
    }

    public double getPhb_month_c() {
        return Phb_month_c;
    }

    public void setPhb_month_c(double phb_month_c) {
        Phb_month_c = phb_month_c;
    }

    public double getPhb_hmmonth_c() {
        return Phb_hmmonth_c;
    }

    public void setPhb_hmmonth_c(double phb_hmmonth_c) {
        Phb_hmmonth_c = phb_hmmonth_c;
    }

    public double getPhb_day_c() {
        return Phb_day_c;
    }

    public void setPhb_day_c(double phb_day_c) {
        Phb_day_c = phb_day_c;
    }

    public double getPhb_hmday_c() {
        return Phb_hmday_c;
    }

    public void setPhb_hmday_c(double phb_hmday_c) {
        Phb_hmday_c = phb_hmday_c;
    }

    public double getPhb_total() {
        return (0.0D + (double) (int) (Phb_total * 100D)) / 100D;
    }

    public void setPhb_total(double phb_total) {
        Phb_total = phb_total;
    }

    public double getPhb_hmall() {
        return (0.0D + (double) (int) (Phb_hmall * 100D)) / 100D;
    }

    public void setPhb_hmall(double phb_hmall) {
        Phb_hmall = phb_hmall;
    }

    public double getPhb_hmmonth() {
        return (0.0D + (double) (int) (Phb_hmmonth * 100D)) / 100D;
    }

    public void setPhb_hmmonth(double phb_hmmonth) {
        Phb_hmmonth = phb_hmmonth;
    }

    public double getPhb_hmday() {
        return (0.0D + (double) (int) (Phb_hmday * 100D)) / 100D;
    }

    public void setPhb_hmday(double phb_hmday) {
        Phb_hmday = phb_hmday;
    }

    public int getPhb_id() {
        return Phb_id;
    }

    public void setPhb_id(int phb_id) {
        Phb_id = phb_id;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }

    public String getUser_name() {
        return User_name;
    }

    public void setUser_name(String user_name) {
        User_name = user_name;
    }

    public double getPhb_all() {
        return (0.0D + (double) (int) (Phb_all * 100D)) / 100D;
    }

    public void setPhb_all(double phb_all) {
        Phb_all = phb_all;
    }

    public double getPhb_month() {
        return (0.0D + (double) (int) (Phb_month * 100D)) / 100D;
    }

    public void setPhb_month(double phb_month) {
        Phb_month = phb_month;
    }

    public double getPhb_day() {
        return (0.0D + (double) (int) (Phb_day * 100D)) / 100D;
    }

    public void setPhb_day(double phb_day) {
        Phb_day = phb_day;
    }

    public String getPhb_type() {
        return Phb_type;
    }

    public void setPhb_type(String phb_type) {
        Phb_type = phb_type;
    }
}
