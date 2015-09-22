package org.tinygroup.sdpm.product.service;

import java.util.List;

import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.product.dao.pojo.ProductRelease;
import org.tinygroup.tinysqldsl.Pager;

public interface ReleaseService {
    /**
     *添加发布
     *@param release
     *@return
     */
	ProductRelease addRelease(ProductRelease release);
    /**
     * 编辑发布
     * @param release
     * @return
     */
    int updateRelease(ProductRelease release);
    /**
     * 批量编辑
     * @param releases
     * @return
     */
    int[] updateBatch(List<ProductRelease> releases);
    /**
     * 根据发布对象删除
     * @param release
     * @return
     */
    int deleteRelease(Integer releaseId);
   
    /**
     * 查找发布
     * @param release
     * @return
     */
    List<ProductRelease> findReleaseList(ProductRelease release,String columnName,boolean asc);
   
    /**
     * 分页查询 
     * @param start
     * @param limit
     * @param release
     * @param orderBies
     * @return
     */
    Pager<ProductRelease>  findReleasePager(int start,int limit,ProductRelease release,String columnName,boolean asc);
}
