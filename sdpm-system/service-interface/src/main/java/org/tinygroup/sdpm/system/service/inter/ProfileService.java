package org.tinygroup.sdpm.system.service.inter;

import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;

import java.util.List;


public interface ProfileService {
    /**
     * 添加附件
     *
     * @param
     * @return 附件
     */
    SystemProfile addSystemProfile(SystemProfile systemProfile);

    /**
     * 批量添加附件
     *
     * @return
     */
    Integer batchAddSystemProfile(List<SystemProfile> systemProfiles);

    /**
     * 批量更新附件
     *
     * @return
     */
    Integer batchUpdateSystemProfile(List<SystemProfile> systemProfiles);

    /**
     * 查询附件信息
     *
     * @param
     * @return 附件信息表
     */
    List<SystemProfile> findSystemProfile(SystemProfile systemProfile);

    /**
     * 删除附件信息
     *
     * @param systemProfile
     * @return
     */
    Integer deleteSystemProfile(SystemProfile systemProfile);

    /**
     * 编辑附件
     *
     * @param systemProfile
     * @return
     */
    SystemProfile editSystemProfile(SystemProfile systemProfile);

    /**
     * 编辑文件名
     *
     * @param systemProfile
     * @return
     */
    SystemProfile editSystemProfileTitle(SystemProfile systemProfile);

    /**
     * 软删除
     *
     * @param id
     * @return
     */

    Integer softDeleteSystemProfile(Integer id);

    /**
     * 通过Id查询
     *
     * @param id
     * @return
     */
    SystemProfile findSystemProfileById(Integer id);

}
