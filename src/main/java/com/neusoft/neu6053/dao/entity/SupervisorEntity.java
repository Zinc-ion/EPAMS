package com.neusoft.neu6053.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName supervisor
 */
@TableName(value ="supervisor")
@Data
public class SupervisorEntity implements Serializable {
    /**
     * 公众监督员编号(即手机号码)
     */
    @TableId
    private String telId;

    /**
     * 公众监督员登录密码
     */
    private String password;

    /**
     * 公众监督员真实姓名
     */
    private String realName;

    /**
     * 公众监督员出生日期
     */
    private String birthday;

    /**
     * 公众监督员性别(1:男;0:女)
     */
    private Integer sex;

    /**
     * 备注
     */
    private String remarks;

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
        SupervisorEntity other = (SupervisorEntity) that;
        return (this.getTelId() == null ? other.getTelId() == null : this.getTelId().equals(other.getTelId()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getRealName() == null ? other.getRealName() == null : this.getRealName().equals(other.getRealName()))
            && (this.getBirthday() == null ? other.getBirthday() == null : this.getBirthday().equals(other.getBirthday()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTelId() == null) ? 0 : getTelId().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getRealName() == null) ? 0 : getRealName().hashCode());
        result = prime * result + ((getBirthday() == null) ? 0 : getBirthday().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", telId=").append(telId);
        sb.append(", password=").append(password);
        sb.append(", realName=").append(realName);
        sb.append(", birthday=").append(birthday);
        sb.append(", sex=").append(sex);
        sb.append(", remarks=").append(remarks);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}