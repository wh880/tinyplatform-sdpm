package org.tinygroup.sdpm.project.biz.inter;

import org.tinygroup.sdpm.project.dao.pojo.Projectproduct;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-18.
 */
public interface ProjectProductManager {
    /**
     * 根据主键id查找用户
     *
     * @param id 主键
     * @return
     */
    Projectproduct find(String id);

    /**
     * 根据条件查询List
     *
     * @param projectproduct 用于查询条件
     * @return
     */
    List<Projectproduct> findList(Projectproduct projectproduct);

    /**
     * 新增有一个用户
     *
     * @param projectproduct 新增实体类
     * @return
     */
    Projectproduct add(Projectproduct projectproduct);

    /**
     * 更新用户
     *
     * @param projectproduct 需要更新的实体类
     * @return
     */
    Projectproduct update(Projectproduct projectproduct);

    /**
     * 根据id进行软删除用户
     *
     * @param id 主键
     * @return
     */
    Integer delete(String id);
}
