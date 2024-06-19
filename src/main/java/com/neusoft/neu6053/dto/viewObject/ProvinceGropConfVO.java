package com.neusoft.neu6053.dto.viewObject;

public class ProvinceGropConfVO {
    private int provinceId;

    private String provinceName;

    private int so2;

    private int co;

    private int pm25;

    private int aqi;

    public ProvinceGropConfVO(int provinceId, String provinceName, int so2, int co, int pm25, int aqi) {
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.so2 = so2;
        this.co = co;
        this.pm25 = pm25;
        this.aqi = aqi;
    }

    public ProvinceGropConfVO() {
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getSo2() {
        return so2;
    }

    public void setSo2(int so2) {
        this.so2 = so2;
    }

    public int getCo() {
        return co;
    }

    public void setCo(int co) {
        this.co = co;
    }

    public int getPm25() {
        return pm25;
    }

    public void setPm25(int pm25) {
        this.pm25 = pm25;
    }

    public int getAqi() {
        return aqi;
    }

    public void setAqi(int aqi) {
        this.aqi = aqi;
    }

    @Override
    public String toString() {
        return "ProvinceGropConfVO{" +
                "provinceId=" + provinceId +
                ", provinceName='" + provinceName + '\'' +
                ", so2=" + so2 +
                ", co=" + co +
                ", pm25=" + pm25 +
                ", aqi=" + aqi +
                '}';
    }


}
