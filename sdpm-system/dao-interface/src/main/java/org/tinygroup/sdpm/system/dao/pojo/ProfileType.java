package org.tinygroup.sdpm.system.dao.pojo;

/**
 * 附件类型
 * Created by Hulk on 2015/10/21.
 */
public enum ProfileType {
    PROJECT("project"),
    EDITED("edited"),
    STORY("story"),
    DOCUMENT("document"),
    TASK("task"),
    RELEASE("release"),
    BUG("bug"),
    ORG("org"),
    TESTCASE("testCase"),
    BUILD("build");
    private final String type;

    ProfileType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
