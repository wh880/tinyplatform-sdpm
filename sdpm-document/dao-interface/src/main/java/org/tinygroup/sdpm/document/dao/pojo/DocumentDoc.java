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

package org.tinygroup.sdpm.document.dao.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 文档表
 * <p>
 * wendang
 */
public class DocumentDoc implements Serializable {

    public static final String DELETE_YES = "1";
    public static final String DELETE_NO = "0";

    /*
     * 文档关联查询的字段
     */
    private String projectName;
    private String docLibName;
    private String productName;
    private String moduleName;
    private String docAddName;
    private String docEditName;
    /**
     * 文档ID
     * <p>
     * 文档ID，主键，唯一标示
     */
    private Integer docId;
    /**
     * 所属产品
     * <p>
     * 所属产品，存放产品ID作为索引
     */
    private Integer docProduct;
    /**
     * 所属项目
     * <p>
     * 所属项目，存储项目ID作为索引
     */
    private Integer docProject;
    /**
     * 文档库ID
     * <p>
     * 文档库ID
     */
    private Integer docLibId;
    /**
     * 所属分类
     * <p>
     * 所属分类
     */
    private Integer docModule;
    /**
     * 文档标题
     * <p>
     * 文档标题
     */
    private String docTitle;
    /**
     * 摘要
     * <p>
     * 摘要
     */
    private String docDigest;
    /**
     * 关键字
     * <p>
     * 关键字
     */
    private String docKeywords;
    /**
     * 文档类型
     * <p>
     * 文档类型
     */
    private String docType;
    /**
     * 文档正文
     * <p>
     * 文档正文
     */
    private String docContent;
    /**
     * 文档url
     * <p>
     * 文档url
     */
    private String docUrl;
    /**
     * 附件
     * <p>
     * 放文档附件路径吧，标准字段类型根本没有存储二进制文件的类型。有点遗憾。
     */
    private String docAttach;
    /**
     * DOC查阅次数
     * <p>
     * 查阅次数
     */
    private Integer docViews;
    /**
     * DOC由谁添加
     * <p>
     * 由谁添加
     */
    private String docAddedBy;
    /**
     * DOC添加时间
     * <p>
     * 添加时间
     */
    private Date docAddedDate;
    /**
     * 由谁编辑
     * <p>
     * 由谁编辑
     */
    private String docEditedBy;
    /**
     * 文档编辑时间
     * <p>
     * 编辑时间
     */
    private Date docEditedDate;
    /**
     * 文档删除标志
     * <p>
     * 已删除
     */
    private String docDeleted;

    public DocumentDoc() {
        setDocDeleted(DELETE_NO);
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDocLibName() {
        return docLibName;
    }

    public void setDocLibName(String docLibName) {
        this.docLibName = docLibName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getDocAddName() {
        return docAddName;
    }

    public void setDocAddName(String docAddName) {
        this.docAddName = docAddName;
    }

    public String getDocEditName() {
        return docAddName;
    }

    public void setDocEditName(String docEditName) {
        this.docEditName = docEditName;
    }

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public Integer getDocProduct() {
        return docProduct;
    }

    public void setDocProduct(Integer docProduct) {
        this.docProduct = docProduct;
    }

    public Integer getDocProject() {
        return docProject;
    }

    public void setDocProject(Integer docProject) {
        this.docProject = docProject;
    }

    public Integer getDocLibId() {
        return docLibId;
    }

    public void setDocLibId(Integer docLibId) {
        this.docLibId = docLibId;
    }

    public Integer getDocModule() {
        return docModule;
    }

    public void setDocModule(Integer docModule) {
        this.docModule = docModule;
    }

    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    public String getDocDigest() {
        return docDigest;
    }

    public void setDocDigest(String docDigest) {
        this.docDigest = docDigest;
    }

    public String getDocKeywords() {
        return docKeywords;
    }

    public void setDocKeywords(String docKeywords) {
        this.docKeywords = docKeywords;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocContent() {
        return docContent;
    }

    public void setDocContent(String docContent) {
        this.docContent = docContent;
    }

    public String getDocUrl() {
        return docUrl;
    }

    public void setDocUrl(String docUrl) {
        this.docUrl = docUrl;
    }

    public String getDocAttach() {
        return docAttach;
    }

    public void setDocAttach(String docAttach) {
        this.docAttach = docAttach;
    }

    public Integer getDocViews() {
        return docViews;
    }

    public void setDocViews(Integer docViews) {
        this.docViews = docViews;
    }

    public String getDocAddedBy() {
        return docAddedBy;
    }

    public void setDocAddedBy(String docAddedBy) {
        this.docAddedBy = docAddedBy;
    }

    public Date getDocAddedDate() {
        return docAddedDate;
    }

    public void setDocAddedDate(Date docAddedDate) {
        this.docAddedDate = docAddedDate;
    }

    public String getDocEditedBy() {
        return docEditedBy;
    }

    public void setDocEditedBy(String docEditedBy) {
        this.docEditedBy = docEditedBy;
    }

    public Date getDocEditedDate() {
        return docEditedDate;
    }

    public void setDocEditedDate(Date docEditedDate) {
        this.docEditedDate = docEditedDate;
    }

    public String getDocDeleted() {
        return docDeleted;
    }

    public void setDocDeleted(String docDeleted) {
        this.docDeleted = docDeleted;
    }

}
