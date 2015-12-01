package org.tinygroup.sdpm.product.dao.pojo;

import java.io.Serializable;

public class StoryCount implements Serializable {

    private String name;

    private int number;

    private double percent;

    private String percentToString;

    public String getPercentToString() {
        String s = String.valueOf(percent * 100);
        return s + "%";
    }

    public void setPercentToString(String percentToString) {
        this.percentToString = percentToString;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

}
