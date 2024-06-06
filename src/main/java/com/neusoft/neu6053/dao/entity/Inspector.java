package com.neusoft.neu6053.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName inspector
 */
@TableName(value ="inspector")
@Data
public class Inspector implements Serializable {
    /**
     * 网格员电话号码（作为主键）
     */
    @TableId(type = IdType.AUTO)
    private Integer telId;

    /**
     * 网格员账户
     */
    private String inspectorCode;

    /**
     * 网格员密码
     */
    private String password;

    /**
     * 网格员姓名
     */
    private String name;

    /**
     * 网格员所属社区
     */
    private String community;

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
        Inspector other = (Inspector) that;
        return (this.getTelId() == null ? other.getTelId() == null : this.getTelId().equals(other.getTelId()))
            && (this.getInspectorCode() == null ? other.getInspectorCode() == null : this.getInspectorCode().equals(other.getInspectorCode()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getCommunity() == null ? other.getCommunity() == null : this.getCommunity().equals(other.getCommunity()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTelId() == null) ? 0 : getTelId().hashCode());
        result = prime * result + ((getInspectorCode() == null) ? 0 : getInspectorCode().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getCommunity() == null) ? 0 : getCommunity().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", telId=").append(telId);
        sb.append(", inspectorCode=").append(inspectorCode);
        sb.append(", password=").append(password);
        sb.append(", name=").append(name);
        sb.append(", community=").append(community);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}