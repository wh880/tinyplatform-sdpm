package org.tinygroup.sdpm.org.dao.fields;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by gongyl on 2016/3/21.
 */
public class Hook {
    private String password;
    private String hook_name;

    private PullPushData pull_push_data;

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

    @JSONField(name = "push_data")
    public PullPushData getPull_push_data() {
        return pull_push_data;
    }

    @JSONField(name = "push_data")
    public void setPull_push_data(PullPushData push_data) {
        this.pull_push_data = push_data;
    }
}
