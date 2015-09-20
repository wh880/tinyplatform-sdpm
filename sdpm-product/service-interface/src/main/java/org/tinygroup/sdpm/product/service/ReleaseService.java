package org.tinygroup.sdpm.product.service;

import java.util.List;

import org.tinygroup.sdpm.product.dao.pojo.ProductRelease;

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
    ProductRelease findRelease(Integer releaseId);
    /**
     * 根据发布Id查找
     * @param releaseId
     * @return
     */
    List<ProductRelease> findReleaseList(ProductRelease releaseId);
}
