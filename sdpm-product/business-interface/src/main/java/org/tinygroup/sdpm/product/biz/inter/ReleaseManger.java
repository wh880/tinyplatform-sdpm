package org.tinygroup.sdpm.product.biz.inter;

import java.util.List;

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
    int delete(Integer releaseId);
   
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
    List<ProductRelease> findList(ProductRelease releaseId);
}