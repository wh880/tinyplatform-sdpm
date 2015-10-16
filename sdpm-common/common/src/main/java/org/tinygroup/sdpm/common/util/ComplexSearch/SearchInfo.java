package org.tinygroup.sdpm.common.util.ComplexSearch;

import org.tinygroup.sdpm.common.util.common.NameUtil;

/**
 * Created by wangll13383 on 2015/9/18.
 */
public class SearchInfo {
    private String relation;

    private String field;

    private String operate;

    private String value;

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
