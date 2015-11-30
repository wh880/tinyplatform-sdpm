package org.tinygroup.sdpm.common.biz.inter;

import org.tinygroup.sdpm.common.dao.pojo.Profile;

import java.util.List;

public interface ProfileManager {
    /**
     * 附件添加
     *
     * @param Profile
     * @return
     */
    Profile add(Profile Profile);

    /**
     * 批量添加附件
     *
     * @param Profile
     * @return 附件List
     */
    List<Profile> batchAdd(List<Profile> Profiles);

    /**
     * 查询
     *
     * @param Profile
     * @return
     */
    List<Profile> find(Profile Profile);

    /**
     * 删除附件
     *
     * @param Profile
     * @return
     */
    Integer delete(Profile Profile);

    /**
     * 更新附件信息
     *
     * @param Profile
     * @return
     */
    Profile updataProfile(Profile Profile);

}
