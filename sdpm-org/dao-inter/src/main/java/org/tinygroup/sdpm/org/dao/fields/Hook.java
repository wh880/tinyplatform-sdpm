package org.tinygroup.sdpm.org.dao.fields;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by Administrator on 2016/3/21.
 */
@XStreamAlias("hook")
public class Hook {
    private String password;
    private String hook_name;
    private PullPushData push_data;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHook_name() {
        return hook_name;
    }

    public void setHook_name(String hook_name) {
        this.hook_name = hook_name;
    }

    public PullPushData getPush_data() {
        return push_data;
    }

    public void setPush_data(PullPushData push_data) {
        this.push_data = push_data;
    }
}
