package org.tinygroup.sdpm.product.biz.inter;

import org.tinygroup.sdpm.product.dao.pojo.ProductRelease;

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
     * 根据发布对象删除
     * @param release
     * @return
     */
    int delete(ProductRelease release);
   
    /**
     * 查找发布
     * @param release
     * @return
     */
    ProductRelease find(Integer releaseId);
    /**
     * 根据发布Id查找
     * @param releaseId
     * @return
     */
    ProductRelease findList(ProductRelease releaseId);
}