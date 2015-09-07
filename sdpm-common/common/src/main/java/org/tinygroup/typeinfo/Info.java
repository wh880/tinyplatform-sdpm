package org.tinygroup.typeinfo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Created by wangll13383 on 2015/9/1.
 */
@XStreamAlias("info")
public class Info {
    @XStreamAsAttribute
    @XStreamAlias("name")
    private String infoName;
    @XStreamAsAttribute
    @XStreamAlias("title")
    private String infoTile;
    @XStreamAsAttribute
    @XStreamAlias("url")
    private String infoUrl;
    @XStreamAsAttribute
    @XStreamAlias("infoParameter")
    private String infoParameter;
    @XStreamAsAttribute
    @XStreamAlias("resource")
    private String urlResource;

    public String getUrlResource() {
        return urlResource;
    }

    public void setUrlResource(String urlResource) {
        this.urlResource = urlResource;
    }

    public String getInfoParameter() {
        return infoParameter;
    }

    public void setInfoParameter(String infoParameter) {
        this.infoParameter = infoParameter;
    }

    public String getInfoName() {
        return infoName;
    }

    public void setInfoName(String infoName) {
        this.infoName = infoName;
    }

    public String getInfoTile() {
        return infoTile;
    }

    public void setInfoTile(String infoTile) {
        this.infoTile = infoTile;
    }

    public String getInfoUrl() {
        return infoUrl;
    }

    public void setInfoUrl(String infoUrl) {
        this.infoUrl = infoUrl;
    }
}
