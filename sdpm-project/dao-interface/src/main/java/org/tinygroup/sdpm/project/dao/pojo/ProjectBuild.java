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

package org.tinygroup.sdpm.project.dao.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 版本
 */
public class ProjectBuild implements Serializable {

    public static final String DELETE_YES = "1";
    public static final String DELETE_NO = "0";
    private String productName;
    /**
     * 版本id
     */
    private Integer buildId;
    /**
     * 产品
     */
    private Integer buildProduct;
    /**
     * 项目
     */
    private Integer buildProject;
    /**
     * 版本名称
     */
    private String buildName;
    /**
     * 源码地址
     */
    private String buildScmPath;
    /**
     * 下载地址
     */
    private String buildFilePath;
    /**
     * 打包日期
     */
    private Date buildDate;
    /**
     * 已解决需求
     */
    private String buildStories;
    /**
     * 已解决bug
     */
    private String buildBugs;
    /**
     * 构建者
     */
    private String buildBuilder;
    /**
     * 描述
     */
    private String buildDesc;
    /**
     * 已删除
     * <p>
     * 0-未删除，1-删除
     */
    private String buildDeleted;

    public ProjectBuild() {
        super();
        this.buildDeleted = DELETE_NO;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getBuildId() {
        return buildId;
    }

    public void setBuildId(Integer buildId) {
        this.buildId = buildId;
    }

    public Integer getBuildProduct() {
        return buildProduct;
    }

    public void setBuildProduct(Integer buildProduct) {
        this.buildProduct = buildProduct;
    }

    public Integer getBuildProject() {
        return buildProject;
    }

    public void setBuildProject(Integer buildProject) {
        this.buildProject = buildProject;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getBuildScmPath() {
        return buildScmPath;
    }

    public void setBuildScmPath(String buildScmPath) {
        this.buildScmPath = buildScmPath;
    }

    public String getBuildFilePath() {
        return buildFilePath;
    }

    public void setBuildFilePath(String buildFilePath) {
        this.buildFilePath = buildFilePath;
    }

    public Date getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(Date buildDate) {
        this.buildDate = buildDate;
    }

    public String getBuildStories() {
        return buildStories;
    }

    public void setBuildStories(String buildStories) {
        this.buildStories = buildStories;
    }

    public String getBuildBugs() {
        return buildBugs;
    }

    public void setBuildBugs(String buildBugs) {
        this.buildBugs = buildBugs;
    }

    public String getBuildBuilder() {
        return buildBuilder;
    }

    public void setBuildBuilder(String buildBuilder) {
        this.buildBuilder = buildBuilder;
    }

    public String getBuildDesc() {
        return buildDesc;
    }

    public void setBuildDesc(String buildDesc) {
        this.buildDesc = buildDesc;
    }

    public String getBuildDeleted() {
        return buildDeleted;
    }

    public void setBuildDeleted(String buildDeleted) {
        this.buildDeleted = buildDeleted;
    }

}
