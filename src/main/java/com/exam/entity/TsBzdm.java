package com.exam.entity;

/**
 * Ts_Bzdm 对应表
 */
public class TsBzdm {
    private String kind;
    private String bt;
    private String code;
    private String mc;
    private String sfjy;
    private Integer pxh;

    public TsBzdm() {
    }

    /**
     * @param kind
     * @param bt
     * @param code
     * @param mc
     * @param sfjy
     * @param pxh
     */
    public TsBzdm(String kind, String bt, String code, String mc, String sfjy, Integer pxh) {
        this.kind = kind;
        this.bt = bt;
        this.code = code;
        this.mc = mc;
        this.sfjy = sfjy;
        this.pxh = pxh;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getBt() {
        return bt;
    }

    public void setBt(String bt) {
        this.bt = bt;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getSfjy() {
        return sfjy;
    }

    public void setSfjy(String sfjy) {
        this.sfjy = sfjy;
    }

    public Integer getPxh() {
        return pxh;
    }

    public void setPxh(Integer pxh) {
        this.pxh = pxh;
    }

    @Override
    public String toString() {
        return "Bzdm{" +
                "kind='" + kind + '\'' +
                ", bt='" + bt + '\'' +
                ", code='" + code + '\'' +
                ", mc='" + mc + '\'' +
                ", sfjy='" + sfjy + '\'' +
                ", pxh=" + pxh +
                '}';
    }


}
