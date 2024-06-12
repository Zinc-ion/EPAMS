package com.neusoft.neu6053.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName information
 */
@TableName(value ="information")
@Data
public class Information implements Serializable {
    /**
     * AQI信息id
     */
    @TableId(type = IdType.AUTO)
    private Integer informationId;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 详细地址
     */
    private String community;

    /**
     * 预估污染水平（1~6数字）
     */
    private String pollutionLevel;

    /**
     * 日期
     */
    private Date date;

    /**
     * 时间
     */
    private Date time;

    /**
     * 反馈表状态（0未委派 1已委派 2已完成）
     */
    private Integer state;

    /**
     * 监督员id
     */
    private String supervisorId;

    /**
     * 网格员id
     */
    private String inspectorId;

    /**
     * 反馈信息
     */
    private String feedback;

    /**
     * 逻辑删除（0未删除 1已删除）
     */
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Information other = (Information) that;
        return (this.getInformationId() == null ? other.getInformationId() == null : this.getInformationId().equals(other.getInformationId()))
            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getCommunity() == null ? other.getCommunity() == null : this.getCommunity().equals(other.getCommunity()))
            && (this.getPollutionLevel() == null ? other.getPollutionLevel() == null : this.getPollutionLevel().equals(other.getPollutionLevel()))
            && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getSupervisorId() == null ? other.getSupervisorId() == null : this.getSupervisorId().equals(other.getSupervisorId()))
            && (this.getInspectorId() == null ? other.getInspectorId() == null : this.getInspectorId().equals(other.getInspectorId()))
            && (this.getFeedback() == null ? other.getFeedback() == null : this.getFeedback().equals(other.getFeedback()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getInformationId() == null) ? 0 : getInformationId().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getCommunity() == null) ? 0 : getCommunity().hashCode());
        result = prime * result + ((getPollutionLevel() == null) ? 0 : getPollutionLevel().hashCode());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getSupervisorId() == null) ? 0 : getSupervisorId().hashCode());
        result = prime * result + ((getInspectorId() == null) ? 0 : getInspectorId().hashCode());
        result = prime * result + ((getFeedback() == null) ? 0 : getFeedback().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", informationId=").append(informationId);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", community=").append(community);
        sb.append(", pollutionLevel=").append(pollutionLevel);
        sb.append(", date=").append(date);
        sb.append(", time=").append(time);
        sb.append(", state=").append(state);
        sb.append(", supervisorId=").append(supervisorId);
        sb.append(", inspectorId=").append(inspectorId);
        sb.append(", feedback=").append(feedback);
        sb.append(", deleted=").append(deleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}