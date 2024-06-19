package com.neusoft.neu6053.dto.viewObject;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@Schema(title = "AQIFeedBackVO", description = "AQI反馈信息VO")
public class AQIFeedBackVO {
    /**
     * AQI反馈信息id
     */
    private Integer feedbackId;

    private String supName;

    /**
     * 公众监督员性别(1:男;0:女)
     */
    private Integer supSex;

    /**
     * 公众监督员出生日期
     */
    private String birthday;

    /**
     * 公众监督员电话
     */
    private String supTel;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 社区详细地址
     */
    private String community;

    /**
     * 反馈信息
     */
    private String feedback;

    /**
     * 预估污染水平
     */
    private String pollutionLevel;

    /**
     * 日期
     */
    private String date;

    /**
     * 时间
     */
    private String time;

    @Override
    public String toString() {
        return "AQIFeedBackVO{}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AQIFeedBackVO that = (AQIFeedBackVO) o;
        return Objects.equals(feedbackId, that.feedbackId) && Objects.equals(supName, that.supName) && Objects.equals(supSex, that.supSex) && Objects.equals(birthday, that.birthday) && Objects.equals(supTel, that.supTel) && Objects.equals(province, that.province) && Objects.equals(city, that.city) && Objects.equals(community, that.community) && Objects.equals(feedback, that.feedback) && Objects.equals(pollutionLevel, that.pollutionLevel) && Objects.equals(date, that.date) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackId, supName, supSex, birthday, supTel, province, city, community, feedback, pollutionLevel, date, time);
    }

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public Integer getSupSex() {
        return supSex;
    }

    public void setSupSex(Integer supSex) {
        this.supSex = supSex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSupTel() {
        return supTel;
    }

    public void setSupTel(String supTel) {
        this.supTel = supTel;
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

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
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

    public AQIFeedBackVO() {
    }

    public AQIFeedBackVO(Integer feedbackId, String supName, Integer supSex, String birthday, String supTel, String province, String city, String community, String feedback, String pollutionLevel, String date, String time) {
        this.feedbackId = feedbackId;
        this.supName = supName;
        this.supSex = supSex;
        this.birthday = birthday;
        this.supTel = supTel;
        this.province = province;
        this.city = city;
        this.community = community;
        this.feedback = feedback;
        this.pollutionLevel = pollutionLevel;
        this.date = date;
        this.time = time;
    }
}
