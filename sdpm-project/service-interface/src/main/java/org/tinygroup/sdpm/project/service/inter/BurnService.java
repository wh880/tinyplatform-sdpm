package org.tinygroup.sdpm.project.service.inter;

import org.tinygroup.sdpm.project.dao.pojo.ProjectBurn;
import org.tinygroup.sdpm.project.service.dto.BurnDTO;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-17.
 */
public interface BurnService {
    /**
     * 更新项目燃尽情况
     *
     * @param projectId 项目编号
     */
    void updateBurnByProjectId(Integer projectId);

    /**
     * 根据数据库数据跟新燃尽图表
     */
    void updateDateTaskId(Integer taskId);

    /**
     * 生成项目的燃尽图数据
     *
     * @param projectId 项目id
     * @param interval  间隔时间
     * @return
     */
    BurnDTO initBurn(Integer projectId, Integer interval);

    /**
     * 根据项目id找到对应的所有数据，前台生成燃尽图
     *
     * @param projectId
     * @return
     */
    List<ProjectBurn> findBurnByProjectId(int projectId);

}
