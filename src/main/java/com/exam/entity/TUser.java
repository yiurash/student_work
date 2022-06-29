package com.exam.entity;

/**
 * 比较全的表内信息 对应T_USER
 *
 */
public class TUser {
    public String yhid;
    public String yhxm;
    public String yhkl;
    public String yhxb;
    public String yhbm;
    public String csrq;
    public String sfjy;
    public String pxh;

    public String getYhid() {
        return yhid;
    }

    public void setYhid(String yhid) {
        this.yhid = yhid;
    }

    public String getYhxm() {
        return yhxm;
    }

    public void setYhxm(String yhxm) {
        this.yhxm = yhxm;
    }

    public String getYhkl() {
        return yhkl;
    }

    public void setYhkl(String yhkl) {
        this.yhkl = yhkl;
    }

    public String getYhxb() {
        return yhxb;
    }

    public void setYhxb(String yhxb) {
        this.yhxb = yhxb;
    }

    public String getYhbm() {
        return yhbm;
    }

    public void setYhbm(String yhbm) {
        this.yhbm = yhbm;
    }

    public String getCsrq() {
        return csrq;
    }

    public void setCsrq(String csrq) {
        this.csrq = csrq;
    }

    public String getSfjy() {
        return sfjy;
    }

    public void setSfjy(String sfjy) {
        this.sfjy = sfjy;
    }

    public String getPxh() {
        return pxh;
    }

    public void setPxh(String pxh) {
        this.pxh = pxh;
    }

    public TUser(String yhid, String yhxm, String yhkl, String yhxb, String yhbm, String csrq, String sfjy, String pxh) {
        this.yhid = yhid;
        this.yhxm = yhxm;
        this.yhkl = yhkl;
        this.yhxb = yhxb;
        this.yhbm = yhbm;
        this.csrq = csrq;
        this.sfjy = sfjy;
        this.pxh = pxh;
    }
    public TUser(){}
    @Override
    public String toString() {
        return "T_USER{" +
                "yhid='" + yhid + '\'' +
                ", yhxm='" + yhxm + '\'' +
                ", yhkl='" + yhkl + '\'' +
                ", yhxb='" + yhxb + '\'' +
                ", yhbm='" + yhbm + '\'' +
                ", csrq='" + csrq + '\'' +
                ", sfjy='" + sfjy + '\'' +
                ", pxh='" + pxh + '\'' +
                '}';
    }
}
