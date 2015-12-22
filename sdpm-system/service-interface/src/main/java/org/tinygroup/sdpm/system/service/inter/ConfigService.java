package org.tinygroup.sdpm.system.service.inter;

import org.tinygroup.aopcache.annotation.CacheGet;
import org.tinygroup.aopcache.annotation.CacheRemove;
import org.tinygroup.sdpm.system.dao.pojo.SystemConfig;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by wangll13383 on 2015/12/18.
 */
public interface ConfigService {
    String CACHE_CONFIG_ID = "configId";
    String CACHE_CONFIG_SECTION = "configSection";
    String CACHE_CONFIG_LIST = "configList";
    /**
     * 添加配置
     *
     * @param config
     * @return
     */
    @CacheRemove(removeGroups = {CACHE_CONFIG_LIST})
    SystemConfig addConfig(SystemConfig config);

    /**
     * 删除
     *
     * @param configId
     * @return
     */
    @CacheRemove(removeKeys = "${configId}", group = CACHE_CONFIG_ID, removeGroups = {CACHE_CONFIG_LIST, CACHE_CONFIG_SECTION,CACHE_CONFIG_ID})
    int deleteConfig(Integer configId);

    /**
     * 修改
     *
     * @param config
     * @return
     */
    @CacheRemove(removeKeys = "${config?.configId}", group = CACHE_CONFIG_ID, removeGroups = {CACHE_CONFIG_LIST, CACHE_CONFIG_SECTION,CACHE_CONFIG_ID})
    int updateConfig(SystemConfig config);

    /**
     * 根据ID查找
     *
     * @param configId
     * @return
     */
    @CacheGet(key = "${configId}", group = CACHE_CONFIG_ID)
    SystemConfig findConfig(Integer configId);

    /**
     * 查找
     * @return
     */
    @CacheGet(key = "configList", group = CACHE_CONFIG_LIST)
    List<SystemConfig> findConfigList();

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
    Pager<SystemConfig> findConfigPager(int start, int limit, SystemConfig config, String columnName, Boolean asc);
    @CacheGet(key = "${section}", group = CACHE_CONFIG_SECTION)
    SystemConfig getConfigBySection(String section);
}
