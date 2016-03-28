package org.tinygroup.sdpm.org.dao.fields;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by Administrator on 2016/3/21.
 */
@XStreamAlias("repository")
public class Repository {
    private String name;
    private String url;
    private String description;
    private String homepage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }
}
