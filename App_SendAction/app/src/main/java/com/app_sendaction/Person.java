package com.app_sendaction;

import java.io.Serializable;
import java.util.List;

public class Person implements Serializable {
    private String ten;
    private String cmnd;
    private String bangcap;
    private List<String> hobbies;
    private String infor;

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getBangcap() {
        return bangcap;
    }

    public void setBangcap(String bangcap) {
        this.bangcap = bangcap;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public String getInfor() {
        return infor;
    }

    public void setInfor(String infor) {
        this.infor = infor;
    }

    public Person(String ten, String cmnd, String bangcap, List<String> hobbies, String infor) {
        this.ten = ten;
        this.cmnd = cmnd;
        this.bangcap = bangcap;
        this.hobbies = hobbies;
        this.infor = infor;
    }
}
