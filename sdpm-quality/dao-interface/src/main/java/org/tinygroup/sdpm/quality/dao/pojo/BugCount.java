package org.tinygroup.sdpm.quality.dao.pojo;

import java.io.Serializable;

/**
 * Created by wangll13383 on 2015/10/30.
 */
public class BugCount implements Serializable{
    private String name;

    private Integer number;

    private float percent;

    private String percentToString;

    public String getPercentToString() {
        String s = String.valueOf(percent*100);
        return s.substring(0,s.indexOf("\\.")+3)+"%";
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }
}
