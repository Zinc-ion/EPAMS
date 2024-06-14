package com.neusoft.neu6053.dao.viewObject;

import java.util.Objects;

public class AQIElseDataVO {
    int total;
    int goodAirQualityCount;

    String provincialCapitalCoverRate;

    String metropolisCoverRate;

    @Override
    public String toString() {
        return "AQIElseDataVO{" +
                "total=" + total +
                ", goodAirQualityCount=" + goodAirQualityCount +
                ", provincialCapitalCoverRate='" + provincialCapitalCoverRate + '\'' +
                ", metropolisCoverRate='" + metropolisCoverRate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AQIElseDataVO that = (AQIElseDataVO) o;
        return total == that.total && goodAirQualityCount == that.goodAirQualityCount && Objects.equals(provincialCapitalCoverRate, that.provincialCapitalCoverRate) && Objects.equals(metropolisCoverRate, that.metropolisCoverRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, goodAirQualityCount, provincialCapitalCoverRate, metropolisCoverRate);
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getGoodAirQualityCount() {
        return goodAirQualityCount;
    }

    public void setGoodAirQualityCount(int goodAirQualityCount) {
        this.goodAirQualityCount = goodAirQualityCount;
    }

    public String getProvincialCapitalCoverRate() {
        return provincialCapitalCoverRate;
    }

    public void setProvincialCapitalCoverRate(String provincialCapitalCoverRate) {
        this.provincialCapitalCoverRate = provincialCapitalCoverRate;
    }

    public String getMetropolisCoverRate() {
        return metropolisCoverRate;
    }

    public void setMetropolisCoverRate(String metropolisCoverRate) {
        this.metropolisCoverRate = metropolisCoverRate;
    }

    public AQIElseDataVO() {
    }

    public AQIElseDataVO(int total, int goodAirQualityCount, String provincialCapitalCoverRate, String metropolisCoverRate) {
        this.total = total;
        this.goodAirQualityCount = goodAirQualityCount;
        this.provincialCapitalCoverRate = provincialCapitalCoverRate;
        this.metropolisCoverRate = metropolisCoverRate;
    }
}
