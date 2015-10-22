package org.tinygroup.sdpm.project.service.inter;

import org.tinygroup.sdpm.project.dao.pojo.ProjectBurn;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-17.
 */
public interface BurnService {

    /**
     * 根据数据库数据跟新燃尽图表
     */
    public void updateDate(Integer taskId);
    /**
     * 新增，在项目变动后需要增加字段
     * @param projectBurn
     * @return
     */
    public int addBurn(ProjectBurn projectBurn);


    /**
     * 根据项目id找到对应的所有数据，前台生成燃尽图
     * @param projectId
     * @return
     */
    public List<ProjectBurn> findBurnByProjectId(int projectId);

    /**
     * 预留接口，在删除项目中
     * @param projectId
     * @param date
     * @return
     */
    public Integer deleteBurnByProjectDate(int projectId, String date);

}
