package com.neusoft.neu6053.utils;

import java.time.LocalDate;
import java.time.LocalTime;

public class TimeUtil {

    public static java.sql.Date getCurrentSqlDate() {
        // 获取当前日期
        LocalDate localDate = LocalDate.now();

        // 将LocalDate对象转换为java.sql.Date对象
        return java.sql.Date.valueOf(localDate);
    }

    public static java.sql.Time getCurrentSqlTime() {
        // 获取当前时间
        LocalTime localTime = LocalTime.now();

        // 将LocalTime对象转换为java.sql.Time对象
        return java.sql.Time.valueOf(localTime);
    }
}
