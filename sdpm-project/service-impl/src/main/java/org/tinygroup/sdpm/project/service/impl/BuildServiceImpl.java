package org.tinygroup.sdpm.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.product.dao.pojo.ProductAndLine;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.sdpm.project.biz.inter.BuildManager;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
import org.tinygroup.sdpm.project.service.inter.BuildService;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.tinysqldsl.Pager;



import java.util.Date;
import java.util.List;

/**
 * Created by shenly13343 on 2015-09-20.
 */
@Component
public class BuildServiceImpl implements BuildService {
    @Autowired
    private BuildManager buildManager;

    public Integer softDeleteBuild(Integer buildId) {
        if (buildId == null || buildId <= 0) {
            return 0;
        } else {
            ProjectBuild build = new ProjectBuild();
            build.setBuildId(buildId);
            build.setBuildDeleted(build.DELETE_YES);
            return buildManager.softDelete(build);
        }
    }
    public ProjectBuild add(ProjectBuild build) {
        build.setBuildStories("");
        build.setBuildBugs("");
        build.setBuildDeleted(build.DELETE_NO);
        return buildManager.add(build);
    }

    public Pager<ProjectBuild> findPager(Integer projectId, Integer start, Integer limit, String order, boolean asc) {
        ProjectBuild build = new ProjectBuild();
        build.setBuildProject(projectId);
        build.setBuildDeleted(build.DELETE_NO);
        return buildManager.findPager(build, start, limit, order, asc);
    }

    public int updateBuild(ProjectBuild build) {
        return buildManager.update(build);
    }

    public Integer deleteBuild(Integer buildId) {
        return null;
    }

    public ProjectBuild findBuild(Integer id) {
        return buildManager.find(id);
    }
    public int[] deleteBuildByIds(List<ProjectBuild> ids) {
        
        return buildManager.batchDelBuildByIds(ids);
    }
    public List<ProjectBuild> findListBuild(ProjectBuild projectBuild){
        return buildManager.findList(projectBuild);
    }
	public List<ProductAndLine> getProductLineTree(ProductLine t) {

		return buildManager.getProductLineTree(t);
	}
    public Pager<ProjectBuild> findPagerBuild(ProjectBuild projectBuild, Integer start, Integer limit, String order, boolean asc){
        return buildManager.findPager(projectBuild,start,limit,order,asc);
    }
    public Integer deletereleate(Integer storyId,Integer buildId){
        return buildManager.deletereleate(storyId, buildId);
    }
    public Integer releateReq(Integer storyId,Integer buildId){
        return buildManager.releateReq(storyId,buildId);
    }
    public Integer deletereleateBug(Integer bugId,Integer buildId){
        return buildManager.deletereleateBug(bugId, buildId);
    }
    public Integer releateBug(Integer bugId,Integer buildId){
        return buildManager.releateBug(bugId,buildId);
    }
    public Pager<QualityBug> findBugPager(int start, int limit, int id, SearchInfos conditions, String groupOperate) {
        return buildManager.findBuildBug(start,limit,id);
    }
    public Pager<QualityBug> findBugLegacyPager(int start, int limit, int id, SearchInfos conditions, String groupOperate) {
        return buildManager.findBuildLegacyBug(start,limit,id);
    }

    public List<ProjectBuild> getBuildByIds(String... ids) {
        return buildManager.getBuildByIds(ids);
    }

    public Pager<QualityBug> findnoBugPager(int start, int limit, int id,String condition, SearchInfos conditions, String groupOperate) {
        return buildManager.findnoBuildBug(start,limit,condition,id);
    }

}
