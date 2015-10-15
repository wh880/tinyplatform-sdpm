package org.tinygroup.sdpm.dict.inter;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by wangll13383 on 2015/10/15.
 */
@XStreamAlias("dictNodeEntry")
public class DictNodeEntry {
    @XStreamAsAttribute
    private String name;
    @XStreamAsAttribute
    private String title;
    @XStreamImplicit
    private List<DictNodeEntry> children;
    @XStreamImplicit
    private List<DictItem> dictItems;

    public List<DictItem> getDictItems() {
        return dictItems;
    }

    public void setDictItems(List<DictItem> dictItems) {
        this.dictItems = dictItems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DictNodeEntry> getChildren() {
        return children;
    }
    public void setChildren(List<DictNodeEntry> children) {
        this.children = children;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
