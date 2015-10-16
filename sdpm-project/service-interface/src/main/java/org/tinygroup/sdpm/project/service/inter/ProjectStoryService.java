package org.tinygroup.sdpm.project.service.inter;

import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.project.dao.pojo.ProjectStory;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;


/**
 * Created by shenly13343 on 2015-09-20.
 */
public interface ProjectStoryService {

    public Integer batchtDel(String condition);

    public int[] addLink(List<ProjectStory> projectStoryList);

    public List<ProductStory> findStoryByProject(Integer projectId);

    public Pager<ProductStory> findStoryByProject(Integer projectId, Integer start, Integer limit, String order, String ordertype);

    public Integer deleteProjectStory(Integer projectId, Integer storyId);

    public Pager<ProductStory> findStoryToLink(Integer projectId, Integer start, Integer limit, String order, String ordertype);

    public Pager<ProductStory> findStoryPager(int start, int limit, ProductStory story, String statusCondition, SearchInfos conditions, String groupOperate, String columnName, boolean asc);
}
