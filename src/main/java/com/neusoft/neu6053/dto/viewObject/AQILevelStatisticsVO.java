package com.neusoft.neu6053.dto.viewObject;

import java.util.Objects;

public class AQILevelStatisticsVO {
    private String level;

    private String description;

    private int count;

    @Override
    public String toString() {
        return "AQILevelStatisticsVO{" +
                "level='" + level + '\'' +
                ", description='" + description + '\'' +
                ", count=" + count +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AQILevelStatisticsVO that = (AQILevelStatisticsVO) o;
        return count == that.count && Objects.equals(level, that.level) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(level, description, count);
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public AQILevelStatisticsVO(String level, String description, int count) {
        this.level = level;
        this.description = description;
        this.count = count;
    }

    public AQILevelStatisticsVO() {
    }
}
