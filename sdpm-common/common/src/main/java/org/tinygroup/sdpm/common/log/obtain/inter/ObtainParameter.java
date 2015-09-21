package org.tinygroup.sdpm.common.log.obtain.inter;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Created by wangll13383 on 2015/9/16.
 */
@XStreamAlias("obtain_parameter")
public class ObtainParameter {
    @XStreamAsAttribute
    @XStreamAlias("parameterName")
    private String name;
    @XStreamAsAttribute
    @XStreamAlias("parameterType")
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
