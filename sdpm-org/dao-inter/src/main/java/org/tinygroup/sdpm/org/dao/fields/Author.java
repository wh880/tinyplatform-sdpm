package org.tinygroup.sdpm.org.dao.fields;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by Administrator on 2016/3/21.
 */
@XStreamAlias("author")
public class Author {
    private String name;
    private String email;
    private String time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
