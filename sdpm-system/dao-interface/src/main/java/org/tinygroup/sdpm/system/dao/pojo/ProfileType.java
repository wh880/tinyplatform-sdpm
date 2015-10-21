package org.tinygroup.sdpm.system.dao.pojo;

/**
 * 附件类型
 * Created by Hulk on 2015/10/21.
 */
public enum ProfileType {
    PROJECT("project"),          //创建
    EDITED("edited");          //编辑了
    private final String type;

    ProfileType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
