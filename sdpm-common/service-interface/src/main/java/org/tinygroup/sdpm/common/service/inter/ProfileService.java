package org.tinygroup.sdpm.common.service.inter;

import org.tinygroup.sdpm.dao.pojo.Profile;

import java.util.List;


public interface ProfileService {
    /**
     * 添加附件
     *
     * @param
     * @return 附件
     */
    Profile add(Profile Profile);

    /**
     * 批量添加附件
     *
     * @return
     */
    List<Profile> batchAdd(List<Profile> Profiles);

    /**
     * 查询附件信息
     *
     * @param
     * @return 附件信息表
     */
    List<Profile> find(Profile Profile);

    /**
     * 删除附件信息
     *
     * @param Profile
     * @return
     */
    int delete(Profile Profile);

    /**
     * 编辑附件
     *
     * @param Profile
     * @return Flie 对象
     */

    Profile edit(Profile Profile);

}
