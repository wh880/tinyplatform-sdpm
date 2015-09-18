package org.tinygroup.sdpm.project.service.inter;

import org.tinygroup.sdpm.project.dao.pojo.Project;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-17.
 */
public interface ProjectService {
    /**
     * 新增项目，保证项目名称唯一
     *
     * @param project
     * @return
     */
    public Project add(Project project);

    /**
     * 查找所有项目
     *
     * @return
     */
    public List<Project> findList();

    /**
     * 根据项目名称查找项目，项目名称要保证唯一
     *
     * @param projectName
     * @return
     */
    public Project findByName(String projectName);

    /**
     * 根据项目id查找
     *
     * @param projectId
     * @return
     */
    public Project findById(int projectId);

    /**
     * 产品id查询所有关联的项目id，放入list
     * 根据项目id查询数据
     *
     * @param list
     * @return
     */
    public List<Project> findByProjectList(List<Integer> list);
}
