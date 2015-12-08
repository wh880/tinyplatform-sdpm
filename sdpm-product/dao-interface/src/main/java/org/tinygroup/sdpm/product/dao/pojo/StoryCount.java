package org.tinygroup.sdpm.product.dao.pojo;

import java.io.Serializable;
import java.text.DecimalFormat;

public class StoryCount implements Serializable {

    private String name;

    private int number;

    private double percent;

    private String percentToString;

    public String getPercentToString() {
        DecimalFormat df   =new DecimalFormat("#.00");
        String s = df.format(percent*100);
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
