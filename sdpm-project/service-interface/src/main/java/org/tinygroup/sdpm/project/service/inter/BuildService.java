package org.tinygroup.sdpm.project.service.inter;

import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.product.dao.pojo.ProductAndLine;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
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
    /**
     * 删除关联
     * @param storyId
     * @param buildId
     * @return
     */
    public Integer deletereleate(Integer storyId,Integer buildId);
    /**
     * 关联需求
     * @param storyId
     * @param buildId
     * @return
     */
    public Integer releateReq(Integer storyId,Integer buildId);
    /**
     * 删除关联
     * @param bugId
     * @param buildId
     * @return
     */
    public Integer deletereleateBug(Integer bugId,Integer buildId);
    /**
     * 关联需求
     * @param bugId
     * @param buildId
     * @return
     */
    public Integer releateBug(Integer bugId,Integer buildId);
    /**
     * bug分页
     * @param id
     * @param start
     * @param limit
     * @param conditions
     * @param groupOperate
     * @return
     */
    public Pager<QualityBug> findBugPager(int start, int limit, int id, SearchInfos conditions, String groupOperate);
    /**
     * 未关联bug分页
     * @param id
     * @param start
     * @param limit
     * @param conditions
     * @param groupOperate
     * @return
     */
    public Pager<QualityBug> findnoBugPager(int start, int limit, int id,String condition, SearchInfos conditions, String groupOperate);
    /**
     * 未关联bug分页
     * @param id
     * @param start
     * @param limit
     * @param conditions
     * @param groupOperate
     * @return
     */
    public Pager<QualityBug> findBugLegacyPager(int start, int limit, int id, SearchInfos conditions, String groupOperate);

    }
