package com.caipiao.activity;


public class Activity {

    private int Act_id;
    private int Act_status;
    private String Acr_type;
    private String Acr_name;
    private String Acr_url;
    private String Acr_img;

    public Activity() {
    }

    public String getAcr_img() {
        return Acr_img;
    }

    public void setAcr_img(String acr_img) {
        Acr_img = acr_img;
    }

    public String getAcr_url() {
        return Acr_url;
    }

    public void setAcr_url(String acr_url) {
        Acr_url = acr_url;
    }

    public String getAcr_name() {
        return Acr_name;
    }

    public void setAcr_name(String acr_name) {
        Acr_name = acr_name;
    }

    public int getAct_id() {
        return Act_id;
    }

    public void setAct_id(int act_id) {
        Act_id = act_id;
    }

    public int getAct_status() {
        return Act_status;
    }

    public void setAct_status(int act_status) {
        Act_status = act_status;
    }

    public String getAcr_type() {
        return Acr_type;
    }

    public void setAcr_type(String acr_type) {
        Acr_type = acr_type;
    }
}
