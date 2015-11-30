package org.tinygroup.sdpm.system.biz.inter;

import org.tinygroup.sdpm.system.dao.pojo.SystemConfig;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface ConfigManager {

    /**
     * 添加配置
     *
     * @param config
     * @return
     */
    SystemConfig add(SystemConfig config);

    /**
     * 删除
     *
     * @param config
     * @return
     */
    int delete(Integer configId);

    /**
     * 修改
     *
     * @param config
     * @return
     */
    int update(SystemConfig config);

    /**
     * 批量删除
     *
     * @param config
     * @return
     */
    int[] updateBatch(List<SystemConfig> configs);

    /**
     * 根据ID查找
     *
     * @param configId
     * @return
     */
    SystemConfig find(Integer configId);

    /**
     * 根据对象查找（排序）
     *
     * @param config
     * @param columnName
     * @param asc
     * @return
     */
    List<SystemConfig> findList(SystemConfig config, String columnName, boolean asc);

    /**
     * 根据对象查找(分页、排序)
     *
     * @param start
     * @param limit
     * @param config
     * @param columnName
     * @param asc
     * @return
     */
    Pager<SystemConfig> findPager(int start, int limit, SystemConfig config, String columnName, boolean asc);
}
