package org.tinygroup.sdpm.product.biz.inter;

import java.util.List;

import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.product.dao.pojo.ProductRelease;
import org.tinygroup.tinysqldsl.Pager;

public interface ReleaseManger{
    /**
     *添加发布
     *@param release
     *@return
     */
	ProductRelease add(ProductRelease release);
    /**
     * 编辑发布
     * @param release
     * @return
     */
    int update(ProductRelease release);
    /**
     * 批量编辑
     * @param productreleases
     * @return
     */
    int[] updateBatch(List<ProductRelease> releases);
    /**
	 * 根据ID进行软删除
	 * @param planId
	 * @return
	 */
	 Integer delete(Integer releaseId);
    /**
     * 查找发布
     * @param release
     * @return
     */
    ProductRelease find(Integer releaseId);
    /**
     * 根据对象查询
     * @param productRelease
     * @return
     */
    List<ProductRelease> findList(ProductRelease productRelease);
    /**
     * 根据对象查找(排序)
     * @param release
     * @param orderBies
     * @return
     */
    List<ProductRelease> findList(ProductRelease productRelease,String order,String ordertype);
    /**
     * 根据对象查找(分页、排序)
     * @param start
     * @param limit
     * @param release
     * @param orderBies
     * @return
     */
     Pager<ProductRelease> findPager(int page,int limit,ProductRelease productRelease, String order,String ordertype);
}