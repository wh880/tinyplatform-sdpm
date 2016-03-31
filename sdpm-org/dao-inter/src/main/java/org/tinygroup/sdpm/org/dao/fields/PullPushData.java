package org.tinygroup.sdpm.org.dao.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gongyl on 2016/3/21.
 */

public class PullPushData {
    private String before;
    private String after;
    private String ref;
    private Integer user_id;
    private String user_name;
    private User user;
    private Repository repository;
    private List<Commit> commits;
    private Integer total_commits_count;
    private Boolean commits_more_than_ten;

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public List<Commit> getCommits() {
        return commits;
    }

    public void setCommits(ArrayList<Commit> commits) {
        this.commits = commits;
    }

    public Integer getTotal_commits_count() {
        return total_commits_count;
    }

    public void setTotal_commits_count(Integer total_commits_count) {
        this.total_commits_count = total_commits_count;
    }

    public Boolean getCommits_more_than_ten() {
        return commits_more_than_ten;
    }

    public void setCommits_more_than_ten(Boolean commits_more_than_ten) {
        this.commits_more_than_ten = commits_more_than_ten;
    }
}
