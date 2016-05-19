/**
 * Copyright (c) 1997-2013, www.tinygroup.org (luo_guo@icloud.com).
 * <p>
 * Licensed under the GPL, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/gpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tinygroup.sdpm.system.dao.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 模块搜索表
 */
public class SystemSearch implements Serializable {
    public static final int DELETE_YES = 1;
    public static final int DELETE_NO = 0;

    /**
     * 搜索ID
     */
    private Integer searchId;

    /**
     * 搜索对象类型
     */
    private String searchObjectType;

    /**
     * 搜索对象ID
     */
    private Integer searchObjectId;

    /**
     * 搜索名称
     */
    private String searchTitle;

    /**
     * 搜索内容
     */
    private String searchContent;

    /**
     * 搜索添加日期
     */
    private Date searchAddedDate;

    /**
     * 搜索编辑日期
     */
    private Date searchEditedDate;

    /**
     * 已删除
     */
    private Integer deleted;

    public Integer getSearchId() {
        return searchId;
    }

    public void setSearchId(Integer searchId) {
        this.searchId = searchId;
    }

    public String getSearchObjectType() {
        return searchObjectType;
    }

    public void setSearchObjectType(String searchObjectType) {
        this.searchObjectType = searchObjectType;
    }

    public Integer getSearchObjectId() {
        return searchObjectId;
    }

    public void setSearchObjectId(Integer searchObjectId) {
        this.searchObjectId = searchObjectId;
    }

    public String getSearchTitle() {
        return searchTitle;
    }

    public void setSearchTitle(String searchTitle) {
        this.searchTitle = searchTitle;
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    public Date getSearchAddedDate() {
        return searchAddedDate;
    }

    public void setSearchAddedDate(Date searchAddedDate) {
        this.searchAddedDate = searchAddedDate;
    }

    public Date getSearchEditedDate() {
        return searchEditedDate;
    }

    public void setSearchEditedDate(Date searchEditedDate) {
        this.searchEditedDate = searchEditedDate;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

}
