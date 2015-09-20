package org.tinygroup.sdpm.project.biz.inter;

import org.tinygroup.sdpm.project.dao.pojo.Build;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-18.
 */
public interface BuildManager {
    /**
     * 根据主键id查找用户
     *
     * @param id 主键
     * @return
     */
    Build find(String id);

    /**
     * 根据条件查询List
     *
     * @param build 用于查询条件
     * @return
     */
    List<Build> findList(Build build);

    /**
     * 新增有一个用户
     *
     * @param build 新增实体类
     * @return
     */
    Build add(Build build);

    /**
     * 更新用户
     *
     * @param build 需要更新的实体类
     * @return
     */
    Build update(Build build);

    /**
     * 根据id进行软删除用户
     *
     * @param id 主键
     * @return
     */
    Integer delete(String id);
}
