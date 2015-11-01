package org.tinygroup.sdpm.project.service.dto;

import java.io.Serializable;

/**
 * Created by Hulk on 2015/11/1.
 */
public class BurnDTO implements Serializable{
    /**
     * 以“,”分隔
     */
    String averageValues;
    String dayValues;
    String leftValues;

    public String getAverageValues() {
        return averageValues;
    }

    public BurnDTO setAverageValues(String averageValues) {
        this.averageValues = averageValues;
        return this;
    }

    public String getDayValues() {
        return dayValues;
    }

    public BurnDTO setDayValues(String dayValues) {
        this.dayValues = dayValues;
        return this;
    }

    public String getLeftValues() {
        return leftValues;
    }

    public BurnDTO setLeftValues(String leftValues) {
        this.leftValues = leftValues;
        return this;
    }
}
