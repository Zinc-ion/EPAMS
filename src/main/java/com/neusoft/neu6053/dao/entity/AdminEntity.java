package com.neusoft.neu6053.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName admins
 */
@TableName(value ="admins")
@Data
public class AdminEntity implements Serializable {
    /**
     * 系统管理员编号
     */
    @TableId(type = IdType.AUTO)
    private Integer adminId;

    /**
     * 系统管理员登陆编号
     */
    private String adminCode;

    /**
     * 系统管理员登陆密码
     */
    private String password;

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
        AdminEntity other = (AdminEntity) that;
        return (this.getAdminId() == null ? other.getAdminId() == null : this.getAdminId().equals(other.getAdminId()))
            && (this.getAdminCode() == null ? other.getAdminCode() == null : this.getAdminCode().equals(other.getAdminCode()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAdminId() == null) ? 0 : getAdminId().hashCode());
        result = prime * result + ((getAdminCode() == null) ? 0 : getAdminCode().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", adminId=").append(adminId);
        sb.append(", adminCode=").append(adminCode);
        sb.append(", password=").append(password);
        sb.append(", remarks=").append(remarks);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}