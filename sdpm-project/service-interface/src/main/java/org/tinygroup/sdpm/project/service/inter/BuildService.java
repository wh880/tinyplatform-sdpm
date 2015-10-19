package org.tinygroup.sdpm.project.service.inter;

import org.tinygroup.sdpm.product.dao.pojo.ProductAndLine;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-20.
 */
public interface BuildService {

    /**
     * 根据版本的逻辑id软删除
     * @param buildId
     * @return
     */
    public Integer softDeleteBuild(Integer buildId);

    /**
     * 创建版本
     *
     * @param projectBuild
     * @return
     */
    public ProjectBuild add(ProjectBuild projectBuild);

    /**
     * 根据id查询
     * @param projectId
     * @param start
     * @param limit
     * @param order
     * @param asc
     * @return
     */
    public Pager<ProjectBuild> findPager(Integer projectId, Integer start, Integer limit, String order, boolean asc);


    /**
     * 编辑版本
     *
     * @param projectBuild
     * @return
     */
    public int updateBuild(ProjectBuild projectBuild);

    /**
     * 根据id删除
     *
     * @param buildId
     * @return
     */
    public Integer deleteBuild(Integer buildId);
    /**
     * 根据id查找
     *
     * @param buildId
     * @return
     */
    public ProjectBuild findBuild(Integer buildId);

    public int[] deleteBuildByIds(List<ProjectBuild> ids);
    /**
     * 根据任务状态进行查询
     *
     * @param projectBuild
     * @return
     */
    public List<ProjectBuild> findListBuild(ProjectBuild projectBuild);
    
    /**
	 * 产品线树
	 * @param t
	 * @return
	 */
	List<ProductAndLine> getProductLineTree(ProductLine t);
    /**
     * 需求分页
     * @param projectBuild
     * @return
     */
    public Pager<ProjectBuild> findPagerBuild(ProjectBuild projectBuild, Integer start, Integer limit, String order, boolean asc);
}
