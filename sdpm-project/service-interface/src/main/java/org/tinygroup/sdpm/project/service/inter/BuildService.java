package org.tinygroup.sdpm.project.service.inter;

import org.tinygroup.sdpm.project.dao.pojo.Build;
import org.tinygroup.tinysqldsl.Pager;

/**
 * Created by shenly13343 on 2015-09-20.
 */
public interface BuildService {

    /**
     * 创建版本
     *
     * @param build
     * @return
     */
    public Build add(Build build);

    /**
     * 根据项目id查询所有版本
     *
     * @param page
     * @param pagesize
     * @param projectId
     * @return
     */
    public Pager<Build> findPager(int page, int pagesize, int projectId);

    /**
     * 编辑版本
     *
     * @param build
     * @return
     */
    public Build updateBuild(Build build);

    /**
     * 根据id删除
     *
     * @param buildId
     * @return
     */
    public Integer deleteBuild(Integer buildId);
}
