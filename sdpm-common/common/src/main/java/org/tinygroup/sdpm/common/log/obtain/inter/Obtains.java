package org.tinygroup.sdpm.common.log.obtain.inter;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by wangll13383 on 2015/9/16.
 */
@XStreamAlias("obtains")
public class Obtains {
    @XStreamAsAttribute
    private String name;
    @XStreamImplicit
    private List<Obtain> obtainList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Obtain> getObtainList() {
        return obtainList;
    }

    public void setObtainList(List<Obtain> obtainList) {
        this.obtainList = obtainList;
    }
}
