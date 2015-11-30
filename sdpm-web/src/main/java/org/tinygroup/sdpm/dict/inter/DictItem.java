package org.tinygroup.sdpm.dict.inter;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Created by wangll13383 on 2015/10/15.
 */
@XStreamAlias("dictItem")
public class DictItem {
    @XStreamAsAttribute
    private String key;
    @XStreamAsAttribute
    private String value;
    @XStreamAsAttribute
    private int sort;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
