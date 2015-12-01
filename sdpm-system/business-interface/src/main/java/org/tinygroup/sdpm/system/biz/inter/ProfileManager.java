package org.tinygroup.sdpm.system.biz.inter;

import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;

import java.util.List;

public interface ProfileManager {
    /**
     * 附件添加
     *
     * @param systemProfile
     * @return
     */
    SystemProfile add(SystemProfile systemProfile);

    /**
     * 批量添加附件
     *
     * @param systemProfiles
     * @return 附件List
     */
    int[] batchAdd(List<SystemProfile> systemProfiles);

    /**
     * 查询
     *
     * @param systemProfile
     * @return
     */
    List<SystemProfile> find(SystemProfile systemProfile);

    /**
     * 删除附件
     *
     * @param systemProfile
     * @return
     */
    Integer delete(SystemProfile systemProfile);

    /**
     * 更新附件信息
     *
     * @param systemProfile
     * @return
     */
    SystemProfile updateSystemProfile(SystemProfile systemProfile);

    /**
     * 批量更新附件信息
     *
     * @param list
     * @return
     */
    Integer batchUpdateSystemProfile(List<SystemProfile> list);

    /**
     * @param systemProfile
     * @return
     */
    SystemProfile editFileTitle(SystemProfile systemProfile);

    /**
     * 软删除
     *
     * @param id
     * @return
     */
    Integer softDelete(Integer id);

    /**
     * 通过Id查询
     *
     * @param id
     * @return
     */
    SystemProfile findById(Integer id);

}
