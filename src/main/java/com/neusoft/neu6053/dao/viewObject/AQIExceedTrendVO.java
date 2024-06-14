package com.neusoft.neu6053.dao.viewObject;

import java.util.Date;
import java.util.Objects;

public class AQIExceedTrendVO {
    int count;

    String yearAndMonth;

    int exceedCount;

    @Override
    public String toString() {
        return "AQIExceedTrendVO{" +
                "count=" + count +
                ", yearAndMonth=" + yearAndMonth +
                ", exceedCount=" + exceedCount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AQIExceedTrendVO that = (AQIExceedTrendVO) o;
        return count == that.count && exceedCount == that.exceedCount && Objects.equals(yearAndMonth, that.yearAndMonth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, yearAndMonth, exceedCount);
    }

    public AQIExceedTrendVO() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getYearAndMonth() {
        return yearAndMonth;
    }

    public void setYearAndMonth(String yearAndMonth) {
        this.yearAndMonth = yearAndMonth;
    }

    public int getExceedCount() {
        return exceedCount;
    }

    public void setExceedCount(int exceedCount) {
        this.exceedCount = exceedCount;
    }

    public AQIExceedTrendVO(int count, String yearAndMonth, int exceedCount) {
        this.count = count;
        this.yearAndMonth = yearAndMonth;
        this.exceedCount = exceedCount;
    }
}
