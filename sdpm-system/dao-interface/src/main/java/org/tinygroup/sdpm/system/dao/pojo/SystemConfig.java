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


/**
 * 模块配置表
 */
public class SystemConfig implements Serializable {
    public static final int DELETE_YES = 1;
    public static final int DELETE_NO = 0;
    public static final String SEARCH_CONFIG = "searchConfig";

    /**
     * 配置ID
     */
    private Integer configId;

    /**
     * 配置创建人
     */
    private String configOwner;

    /**
     * 配置模块
     */
    private String configModule;

    /**
     * 配置部分
     */
    private String configSection;

    /**
     * 配置的关键词
     */
    private String configKey;

    /**
     * 配置的值
     */
    private String configValue;

    /**
     * 已删除
     */
    private Integer deleted;

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }

    public String getConfigOwner() {
        return configOwner;
    }

    public void setConfigOwner(String configOwner) {
        this.configOwner = configOwner;
    }

    public String getConfigModule() {
        return configModule;
    }

    public void setConfigModule(String configModule) {
        this.configModule = configModule;
    }

    public String getConfigSection() {
        return configSection;
    }

    public void setConfigSection(String configSection) {
        this.configSection = configSection;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

}
