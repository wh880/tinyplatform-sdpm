package org.tinygroup.sdpm.system.service.inter;

import org.tinygroup.sdpm.system.dao.pojo.SystemDict;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface DictService {
    /**
     * 添加字典
     *
     * @param dict
     * @return
     */
    SystemDict addDict(SystemDict dict);

    /**
     * 删除
     *
     * @param dictId
     * @return
     */
    int deleteDict(Integer dictId);

    /**
     * 修改
     *
     * @param dict
     * @return
     */
    int updateDict(SystemDict dict);

    /**
     * 根据ID查找
     *
     * @param dictId
     * @return
     */
    SystemDict findDict(Integer dictId);

    /**
     * 根据对象查找
     *
     * @param dict
     * @return
     */
    List<SystemDict> findDictList(SystemDict dict);

    /**
     * 根据对象查找(分页、排序)
     *
     * @param start
     * @param limit
     * @param dict
     * @param columnName
     * @param asc
     * @return
     */
    Pager<SystemDict> findDictPager(int start, int limit, SystemDict dict, String columnName, boolean asc);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    int batchDelete(Integer... ids);

    /**
     * 删除所有字典项
     */

    void deleteAllDict();

    /**
     * 有序查询字典项
     * @param dict
     * @param columnName
     * @param asc
     * @return
     */
    List<SystemDict> findDictListByOrder(SystemDict dict, String columnName, boolean asc);

}
