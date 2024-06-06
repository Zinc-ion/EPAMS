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
 * @TableName confirmation
 */
@TableName(value ="confirmation")
@Data
public class Confirmation implements Serializable {
    /**
     * AQI确认id
     */
    @TableId(type = IdType.AUTO)
    private Integer confId;

    /**
     * AQI反馈id
     */
    private Integer informationId;

    /**
     * 网格员姓名
     */
    private String inspectorName;

    /**
     * 监督员姓名
     */
    private String supervisorName;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 社区
     */
    private String community;

    /**
     * 污染等级
     */
    private String pollutionLevel;

    /**
     * 二氧化硫浓度
     */
    private Integer so2;

    /**
     * 一氧化碳浓度
     */
    private Integer co;

    /**
     * PM2.5浓度
     */
    private Integer pm25;

    /**
     * 确认日期
     */
    private Date data;

    /**
     * 确认时间
     */
    private Date time;

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
        Confirmation other = (Confirmation) that;
        return (this.getConfId() == null ? other.getConfId() == null : this.getConfId().equals(other.getConfId()))
            && (this.getInformationId() == null ? other.getInformationId() == null : this.getInformationId().equals(other.getInformationId()))
            && (this.getInspectorName() == null ? other.getInspectorName() == null : this.getInspectorName().equals(other.getInspectorName()))
            && (this.getSupervisorName() == null ? other.getSupervisorName() == null : this.getSupervisorName().equals(other.getSupervisorName()))
            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getCommunity() == null ? other.getCommunity() == null : this.getCommunity().equals(other.getCommunity()))
            && (this.getPollutionLevel() == null ? other.getPollutionLevel() == null : this.getPollutionLevel().equals(other.getPollutionLevel()))
            && (this.getSo2() == null ? other.getSo2() == null : this.getSo2().equals(other.getSo2()))
            && (this.getCo() == null ? other.getCo() == null : this.getCo().equals(other.getCo()))
            && (this.getPm25() == null ? other.getPm25() == null : this.getPm25().equals(other.getPm25()))
            && (this.getData() == null ? other.getData() == null : this.getData().equals(other.getData()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getConfId() == null) ? 0 : getConfId().hashCode());
        result = prime * result + ((getInformationId() == null) ? 0 : getInformationId().hashCode());
        result = prime * result + ((getInspectorName() == null) ? 0 : getInspectorName().hashCode());
        result = prime * result + ((getSupervisorName() == null) ? 0 : getSupervisorName().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getCommunity() == null) ? 0 : getCommunity().hashCode());
        result = prime * result + ((getPollutionLevel() == null) ? 0 : getPollutionLevel().hashCode());
        result = prime * result + ((getSo2() == null) ? 0 : getSo2().hashCode());
        result = prime * result + ((getCo() == null) ? 0 : getCo().hashCode());
        result = prime * result + ((getPm25() == null) ? 0 : getPm25().hashCode());
        result = prime * result + ((getData() == null) ? 0 : getData().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", confId=").append(confId);
        sb.append(", informationId=").append(informationId);
        sb.append(", inspectorName=").append(inspectorName);
        sb.append(", supervisorName=").append(supervisorName);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", community=").append(community);
        sb.append(", pollutionLevel=").append(pollutionLevel);
        sb.append(", so2=").append(so2);
        sb.append(", co=").append(co);
        sb.append(", pm25=").append(pm25);
        sb.append(", data=").append(data);
        sb.append(", time=").append(time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}