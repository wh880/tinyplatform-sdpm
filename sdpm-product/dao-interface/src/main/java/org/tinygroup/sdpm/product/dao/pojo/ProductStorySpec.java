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

package org.tinygroup.sdpm.product.dao.pojo;


import java.io.Serializable;

/**
 * 需求表述表
 */
public class ProductStorySpec implements Serializable {

    /**
     * 需求描述ID
     */
    private Integer storyspecId;

    /**
     * 公司ID
     */
    private Integer companyId;

    /**
     * 需求ID
     */
    private Integer storyId;

    /**
     * 需求版本
     */
    private Integer storyVersion;

    /**
     * 需求标题
     */
    private String storyTitle;

    /**
     * 需求描述
     */
    private String storySpec;

    /**
     * 验证标准
     */
    private String storyVerification;

    public Integer getStoryspecId() {
        return storyspecId;
    }

    public void setStoryspecId(Integer storyspecId) {
        this.storyspecId = storyspecId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getStoryId() {
        return storyId;
    }

    public void setStoryId(Integer storyId) {
        this.storyId = storyId;
    }

    public Integer getStoryVersion() {
        return storyVersion;
    }

    public void setStoryVersion(Integer storyVersion) {
        this.storyVersion = storyVersion;
    }

    public String getStoryTitle() {
        return storyTitle;
    }

    public void setStoryTitle(String storyTitle) {
        this.storyTitle = storyTitle;
    }

    public String getStorySpec() {
        return storySpec;
    }

    public void setStorySpec(String storySpec) {
        this.storySpec = storySpec;
    }

    public String getStoryVerification() {
        return storyVerification;
    }

    public void setStoryVerification(String storyVerification) {
        this.storyVerification = storyVerification;
    }

}
