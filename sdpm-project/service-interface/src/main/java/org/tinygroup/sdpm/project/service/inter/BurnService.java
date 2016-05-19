package org.tinygroup.sdpm.project.service.inter;

import org.tinygroup.aopcache.annotation.CacheRemove;
import org.tinygroup.sdpm.project.service.dto.BurnDTO;

/**
 * Created by shenly13343 on 2015-09-17.
 */
public interface BurnService {
    /**
     * 更新项目燃尽情况
     *
     * @param projectId 项目编号
     */
    void updateBurnByProjectId(Integer projectId);

    /**
     * 生成项目的燃尽图数据
     *
     * @param projectId 项目id
     * @param interval  间隔时间
     * @return
     */
    @CacheRemove(removeGroups = "需要清理的缓存组")
    BurnDTO initBurn(Integer projectId, Integer interval);

}
