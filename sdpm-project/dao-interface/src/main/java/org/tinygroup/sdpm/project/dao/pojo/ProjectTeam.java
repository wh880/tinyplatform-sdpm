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
 * 团队
 */
public class ProjectTeam implements Serializable {

    /**
     * 逻辑ID
     */
    private Integer id;

    /**
     * 产品id
     */
    private Integer productId;

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 用户
     */
    private String teamUserId;

    /**
     * 角色
     */
    private String teamRole;

    /**
     * 加盟日
     */
    private Date teamJoin;

    /**
     * 可用工时
     */
    private Float teamDays;

    /**
     * 可用工时
     */
    private Float teamHours;
    /**
     * 成员角色
     */
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getTeamUserId() {
        return teamUserId;
    }

    public void setTeamUserId(String teamUserId) {
        this.teamUserId = teamUserId;
    }

    public String getTeamRole() {
        return teamRole;
    }

    public void setTeamRole(String teamRole) {
        this.teamRole = teamRole;
    }

    public Date getTeamJoin() {
        return teamJoin;
    }

    public void setTeamJoin(Date teamJoin) {
        this.teamJoin = teamJoin;
    }

    public Float getTeamDays() {
        return teamDays;
    }

    public void setTeamDays(Float teamDays) {
        this.teamDays = teamDays;
    }

    public Float getTeamHours() {
        return teamHours;
    }

    public void setTeamHours(Float teamHours) {
        this.teamHours = teamHours;
    }

}
