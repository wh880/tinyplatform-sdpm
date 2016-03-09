package org.tinygroup.sdpm.org.service.inter;

import org.tinygroup.sdpm.org.dao.pojo.OrgDept;

import java.util.List;

public interface DeptService {
    /**
     * 根据主键id查找部门
     *
     * @param id 主键
     * @return
     */
    OrgDept findDept(Integer id);

    /**
     * 根据条件查询List
     *
     * @param orgDept 用于查询条件
     * @return
     */
    List<OrgDept> findDeptList(OrgDept orgDept);

    /**
     * 新增有一个部门
     *
     * @param orgDept 新增实体类
     * @return
     */
    OrgDept addDept(OrgDept orgDept);

    /**
     * 更新部门
     *
     * @param orgDept 需要更新的实体类
     * @return
     */
    OrgDept updateDept(OrgDept orgDept);

    /**
     * 根据id进行软删除部门
     *
     * @param id 主键
     * @return
     */
    Integer deleteDept(Integer id);

}
