package com.neusoft.neu6053.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("supervisor")
public class Supervisor {
    @TableId(value="tel_id",type= IdType.AUTO)
    String telId;

    @TableField("password")
    String password;

    @TableField("real_name")
    String realName;

    @TableField("birthday")
    String birthday;

    @TableField("sex")
    int sex;

    @TableField("remarks")
    String remarks;

    public String getTelId() {
        return telId;
    }

    public void setTelId(String telId) {
        this.telId = telId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Supervisor{" +
                "telId='" + telId + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", birthday='" + birthday + '\'' +
                ", sex=" + sex +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
