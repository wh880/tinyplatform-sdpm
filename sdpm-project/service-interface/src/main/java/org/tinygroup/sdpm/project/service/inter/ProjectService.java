package org.tinygroup.sdpm.project.service.inter;


import java.util.List;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.tinysqldsl.Pager;

/**
 * Created by shenly13343 on 2015-09-17.
 */
public interface ProjectService {
    /**
     * 新增项目，保证项目名称唯一
     *
     * @param project
     * @return
     */
     Project addProject(Project project);

    /**
     * 查找所有项目
     *
     * @return
     */
    public List<Project> findList();

    /**
     * 根据项目名称查找项目，项目名称要保证唯一
     *
     * @param projectName
     * @return
     */
    public Project findByName(String projectName);

    /**
     * 查询所有项目
     *
     * @param start
     * @param limit
     * @param ordertype
     * @param order
     * @return
     */
    public Pager<Project> findProjects(Integer start, Integer limit, String order, String ordertype);

    /**
     * 根据项目id查找
     *
     * @param projectId
     * @return
     */
    public Project findById(int projectId);

    /**
     * 产品id查询所有关联的项目id，放入list
     * 根据项目id查询数据
     *
     * @param list
     * @return
     */
    public List<Project> findByProjectList(List<Integer> list);
    
    /**
     * 对象查询(排序)
     * @param productLine
     * @param order
     * @param ordertype
     * @return
     */
	List<Project> findProjectList(Project project,String order,String ordertype);
	
	
	/**
	 * 分页查询(排序)
	 * @param page
	 * @param pagesize
	 * @param productLine
	 * @param order
	 * @param ordertype
	 * @return
	 */
	Pager<Project> findProjectPager(int page,int pagesize,Project project,String order,String ordertype);


}
