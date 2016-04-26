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
 * 附件表
 */
public class SystemProfile implements Serializable {

    public static final String DELETE_YES = "1";
    public static final String DELETE_NO = "0";
    /**
     * 附件ID
     */
    private Integer fileId;
    /**
     * 附件地址
     */
    private String filePathname;
    /**
     * 附件名
     */
    private String fileTitle;
    /**
     * 附件扩展名
     */
    private String fileExtension;
    /**
     * 附件大小
     */
    private Integer fileSize;
    /**
     * 附件文件类型
     */
    private String fileObjectType;
    /**
     * 附件对象ID
     */
    private Integer fileObjectId;
    /**
     * 由谁添加
     */
    private String fileAddedBy;
    /**
     * 附件添加日期
     */
    private Date fileAddedDate;
    /**
     * 下载次数
     */
    private Integer fileDownloads;
    /**
     * file_extra 无用
     */
    private String fileExtra;
    /**
     * 是否删除
     */
    private String fileDeleted;

    public SystemProfile() {
        this.fileDeleted = DELETE_NO;
    }

    public SystemProfile(String filePathname, String fileTitle, String fileExtension,
                         Integer fileSize, String fileObjectType, Integer fileObjectId,
                         String fileAddedBy, Date fileAddedDate) {
        this.filePathname = filePathname;
        this.fileTitle = fileTitle;
        this.fileExtension = fileExtension;
        this.fileSize = fileSize;
        this.fileObjectType = fileObjectType;
        this.fileObjectId = fileObjectId;
        this.fileAddedBy = fileAddedBy;
        this.fileAddedDate = fileAddedDate;
        this.fileDeleted = DELETE_NO;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFilePathname() {
        return filePathname;
    }

    public void setFilePathname(String filePathname) {
        this.filePathname = filePathname;
    }

    public String getFileTitle() {
        return fileTitle;
    }

    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileObjectType() {
        return fileObjectType;
    }

    public void setFileObjectType(String fileObjectType) {
        this.fileObjectType = fileObjectType;
    }

    public Integer getFileObjectId() {
        return fileObjectId;
    }

    public void setFileObjectId(Integer fileObjectId) {
        this.fileObjectId = fileObjectId;
    }

    public String getFileAddedBy() {
        return fileAddedBy;
    }

    public void setFileAddedBy(String fileAddedBy) {
        this.fileAddedBy = fileAddedBy;
    }

    public Date getFileAddedDate() {
        return fileAddedDate;
    }

    public void setFileAddedDate(Date fileAddedDate) {
        this.fileAddedDate = fileAddedDate;
    }

    public Integer getFileDownloads() {
        return fileDownloads;
    }

    public void setFileDownloads(Integer fileDownloads) {
        this.fileDownloads = fileDownloads;
    }

    public String getFileExtra() {
        return fileExtra;
    }

    public void setFileExtra(String fileExtra) {
        this.fileExtra = fileExtra;
    }

    public String getFileDeleted() {
        return fileDeleted;
    }

    public void setFileDeleted(String fileDeleted) {
        this.fileDeleted = fileDeleted;
    }

}
