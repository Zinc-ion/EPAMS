package com.neusoft.neu6053.dao.viewObject;

import java.util.Objects;

public class AQIConfirmVO {
    private Integer confirmId;
    private Integer infoId;
    private String province;
    private String city;
    private String community;
    private String pollutionLevel;
    private String date;
    private String time;
    private String insName;
    private String insTel;
    private String supName;
    private String supTel;
    private String feedback;

    public AQIConfirmVO(Integer confirmId, Integer infoId, String province, String city, String community, String pollutionLevel, String date, String time, String insName, String insTel, String supName, String supTel, String feedback) {
        this.confirmId = confirmId;
        this.infoId = infoId;
        this.province = province;
        this.city = city;
        this.community = community;
        this.pollutionLevel = pollutionLevel;
        this.date = date;
        this.time = time;
        this.insName = insName;
        this.insTel = insTel;
        this.supName = supName;
        this.supTel = supTel;
        this.feedback = feedback;
    }

    public Integer getConfirmId() {
        return confirmId;
    }

    public void setConfirmId(Integer confirmId) {
        this.confirmId = confirmId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getPollutionLevel() {
        return pollutionLevel;
    }

    public void setPollutionLevel(String pollutionLevel) {
        this.pollutionLevel = pollutionLevel;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInsName() {
        return insName;
    }

    public void setInsName(String insName) {
        this.insName = insName;
    }

    public String getInsTel() {
        return insTel;
    }

    public void setInsTel(String insTel) {
        this.insTel = insTel;
    }

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public String getSupTel() {
        return supTel;
    }

    public void setSupTel(String supTel) {
        this.supTel = supTel;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "AQIConfirmVO{" +
                "confirmId=" + confirmId +
                ", infoId=" + infoId +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", community='" + community + '\'' +
                ", pollutionLevel='" + pollutionLevel + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", insName='" + insName + '\'' +
                ", insTel='" + insTel + '\'' +
                ", supName='" + supName + '\'' +
                ", supTel='" + supTel + '\'' +
                ", feedback='" + feedback + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AQIConfirmVO that = (AQIConfirmVO) o;
        return Objects.equals(confirmId, that.confirmId) && Objects.equals(infoId, that.infoId) && Objects.equals(province, that.province) && Objects.equals(city, that.city) && Objects.equals(community, that.community) && Objects.equals(pollutionLevel, that.pollutionLevel) && Objects.equals(date, that.date) && Objects.equals(time, that.time) && Objects.equals(insName, that.insName) && Objects.equals(insTel, that.insTel) && Objects.equals(supName, that.supName) && Objects.equals(supTel, that.supTel) && Objects.equals(feedback, that.feedback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(confirmId, infoId, province, city, community, pollutionLevel, date, time, insName, insTel, supName, supTel, feedback);
    }

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public AQIConfirmVO() {
    }
}
