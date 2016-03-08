package org.tinygroup.sdpm.product.service.inter;

import org.tinygroup.sdpm.product.dao.pojo.ProductRelease;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface ReleaseService {
    /**
     * 添加发布
     *
     * @param release
     * @return
     */
    ProductRelease addRelease(ProductRelease release);

    /**
     * 编辑发布
     *
     * @param release
     * @return
     */
    int updateRelease(ProductRelease release);

    /**
     * 根据id进行软删除用户
     *
     * @param releaseId 主键
     * @return
     */
    Integer deleteRelease(Integer releaseId);

    /**
     * 根据Id查找
     *
     * @param releaseId
     * @return
     */
    ProductRelease findRelease(Integer releaseId);

    /**
     * 根据对象查找
     *
     * @param release
     * @return
     */
    List<ProductRelease> findReleaseList(ProductRelease release);


    /**
     * 查找发布,根据排序
     *
     * @param productRelease
     * @param order
     * @param orderType
     * @return
     */
    List<ProductRelease> findReleaseListByOrder(ProductRelease productRelease, String order, String orderType);

    /**
     * 分页查询
     *
     * @param page
     * @param limit
     * @param productRelease
     * @param order
     * @param orderType
     * @return
     */
    Pager<ProductRelease> findReleasePager(int page, int limit, ProductRelease productRelease, String order, String orderType);
}
