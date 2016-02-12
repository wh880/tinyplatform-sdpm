package org.tinygroup.sdpm.dict.inter;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by wangll13383 on 2015/10/15.
 */
@XStreamAlias("dictNodeEntries")
public class DictNodeEntries {

    @XStreamAsAttribute
    private String name;

    @XStreamAsAttribute
    private String title;

    @XStreamImplicit
    private List<DictNodeEntry> dictNodeEntries;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DictNodeEntry> getDictNodeEntries() {
        return dictNodeEntries;
    }

    public void setDictNodeEntries(List<DictNodeEntry> dictNodeEntries) {
        this.dictNodeEntries = dictNodeEntries;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
