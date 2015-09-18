package org.tinygroup.sdpm.product.biz.inter;

import org.tinygroup.database.config.Field;
import org.tinygroup.sdpm.product.dao.pojo.Release;
import org.tinygroup.sdpm.product.service.pojo.PagerPojo;
import org.tinygroup.tinysqldsl.Pager;
import java.util.List;

public interface ReleaseManger{
    /**
     *添加发布
     *@param release
     *@return
     */
    Release add(Release release);
    /**
     * 编辑发布
     * @param release
     * @return
     */
    int update(Release release);
    /**
     * 根据发布对象删除
     * @param release
     * @return
     */
    int deleteRelease(Release release);
    /**
     * 根据发布ID删除
     * @param releaseId
     * @return
     */
    int deleteByReleaseId(Integer releaseId);
    /**
     * 查找发布
     * @param release
     * @return
     */
    Release findRelease(Release release);
    /**
     * 根据发布Id查找
     * @param releaseId
     * @return
     */
    Release findById(Release releaseId);
}