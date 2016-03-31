package org.tinygroup.sdpm.org.dao.fields;

/**
 * Created by gongyl on 2016/3/21.
 */
public class SourceRepo {
    private int project_id;
    private String project_name;

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = Integer.parseInt(project_id);
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }
}
