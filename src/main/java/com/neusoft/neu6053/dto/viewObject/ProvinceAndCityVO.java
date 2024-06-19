package com.neusoft.neu6053.dto.viewObject;

import java.util.List;
import java.util.Objects;

public class ProvinceAndCityVO {
    private String provinceName;
    private List<String> cityName;

    public ProvinceAndCityVO() {
    }

    @Override
    public String toString() {
        return "ProvinceAndCityVO{" +
                "provinceName='" + provinceName + '\'' +
                ", cityName=" + cityName +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProvinceAndCityVO that = (ProvinceAndCityVO) o;
        return Objects.equals(provinceName, that.provinceName) && Objects.equals(cityName, that.cityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(provinceName, cityName);
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public List<String> getCityName() {
        return cityName;
    }

    public void setCityName(List<String> cityName) {
        this.cityName = cityName;
    }

    public ProvinceAndCityVO(String provinceName, List<String> cityName) {
        this.provinceName = provinceName;
        this.cityName = cityName;
    }
}
