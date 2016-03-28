package org.tinygroup.sdpm.org.dao.fields;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by Administrator on 2016/3/21.
 */
@XStreamAlias("commit")
public class Commit {
    private String id;
    private String message;
    private String timestamp;
    private String url;
    private Author author;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
