package org.tinygroup.sdpm.common.log.obtain.inter;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by wangll13383 on 2015/9/16.
 */
@XStreamAlias("obtain")
public class Obtain {
    @XStreamAsAttribute
    @XStreamAlias("moduleType")
    private String name;
    @XStreamAsAttribute
    @XStreamAlias("classType")
    private String type;
    @XStreamAsAttribute
    private String method;
    @XStreamAsAttribute
    private String primaryName;
    @XStreamImplicit
    private List<ObtainParameter> parameters;
    @XStreamAsAttribute
    @XStreamAlias("InfoUrl")
    private String url;
    public String getPrimaryName() {
        return primaryName;
    }

    public void setPrimaryName(String primaryName) {
        this.primaryName = primaryName;
    }

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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<ObtainParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<ObtainParameter> parameters) {
        this.parameters = parameters;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
