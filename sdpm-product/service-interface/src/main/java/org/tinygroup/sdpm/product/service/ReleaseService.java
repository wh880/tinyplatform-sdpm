package org.tinygroup.sdpm.product.service;

import java.util.List;

import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;
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
    int[] updateBatchRelease(List<ProductRelease> releases);
    /**
     * 根据id进行软删除用户
     *
     * @param id 主键
     * @return
     */
    Integer deleteRelease(Integer releaseId);
    
    /**
	 * 根据Id查找
	 * @param planId
	 * @return
	 */
	ProductRelease findRelease(Integer releaseId);
	
	/**
	 * 根据多个ID查找
	 * @param releaseId
	 * @return
	 */
	List<ProductRelease> findReleaseList(Integer... releaseId);
	
   
    /**
     * 查找发布
     * @param release
     * @return
     */
    List<ProductRelease> findReleaseList(ProductRelease productRelease,String order,String ordertype);
   
    /**
     * 分页查询 
     * @param start
     * @param limit
     * @param release
     * @param orderBies
     * @return
     */
    Pager<ProductRelease>  findReleasePager(int page,int limit,ProductRelease productRelease,String order,String ordertype);
}
