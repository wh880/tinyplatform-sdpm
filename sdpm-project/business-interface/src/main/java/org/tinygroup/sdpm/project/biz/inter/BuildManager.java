package org.tinygroup.sdpm.project.biz.inter;


import org.tinygroup.sdpm.product.dao.pojo.ProductAndLine;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-18.
 */
public interface BuildManager {

    /**
     * 软删除
     *
     * @param build
     * @return
     */
    public Integer softDelete(ProjectBuild build);

    /**
     * 根据主键id查找用户
     *
     * @param id 主键
     * @return
     */
    ProjectBuild find(Integer id);

    /**
     * 根据条件查询List
     *
     * @param build 用于查询条件
     * @return
     */
    List<ProjectBuild> findList(ProjectBuild build);

    /**
     * 分页查询
     *
     * @param build
     * @param start
     * @param limit
     * @param order
     * @param asc
     * @return
     */
    public Pager<ProjectBuild> findPager(ProjectBuild build, Integer start, Integer limit, String order, boolean asc);

    /**
     * 新增有一个版本
     *
     * @param build 新增实体类
     * @return
     */
    ProjectBuild add(ProjectBuild build);

    /**
     * 更新用户
     *
     * @param build 需要更新的实体类
     * @return
     */
    int update(ProjectBuild build);

    /**
     * 根据id进行软删除
     *
     * @param id 主键
     * @return
     */
    Integer delete(int id);

    public int[] batchDelBuildByIds(List<ProjectBuild> keys);
    
    /**
	 * 产品线树
	 * @param t
	 * @return
	 */
	List<ProductAndLine> getProductLineTree(ProductLine t);
}
