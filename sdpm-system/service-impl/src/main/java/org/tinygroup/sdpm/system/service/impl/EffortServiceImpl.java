package org.tinygroup.sdpm.system.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.project.biz.inter.TaskManager;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.sdpm.system.biz.inter.EffortManager;
import org.tinygroup.sdpm.system.dao.pojo.SystemEffort;
import org.tinygroup.sdpm.system.service.inter.EffortService;
import org.tinygroup.tinysqldsl.Pager;
@Component
public class EffortServiceImpl implements EffortService {
	@Autowired
    private EffortManager  effortManager;
	@Autowired
	private TaskManager taskManager;
	public List<SystemEffort> findByDate(Date date) {
		// TODO Auto-generated method stub 
		
		return effortManager.findByDate(date);
	}

	public SystemEffort save(SystemEffort systemEffort) {
		// TODO Auto-generated method stub
		if(systemEffort.getEffortId()==null){
			
	
		if(systemEffort.getEffortBegin()==null&&systemEffort.getEffortEnd()==null){
		ProjectTask task=
			 taskManager.find(systemEffort.getEffortObjectId());
			if(task.getTaskCanceledDate()!=null||task.getTaskFinishedDate()!=null){
				if(task.getTaskCloseDate()==null&&task.getTaskFinishedDate()!=null){
					systemEffort.setEffortEnd(new SimpleDateFormat("yyyy-MM-dd").format(task.getTaskFinishedDate()));
				}
				if(task.getTaskCloseDate()!=null&&task.getTaskFinishedDate()==null){
					systemEffort.setEffortEnd(new SimpleDateFormat("yyyy-MM-dd").format(task.getTaskCloseDate()));
				}
				if(task.getTaskCloseDate()!=null&&task.getTaskFinishedDate()!=null){
					systemEffort.setEffortEnd(new SimpleDateFormat("yyyy-MM-dd").format(task.getTaskCloseDate()));
				}
			}
		return effortManager.add(systemEffort);
		}
		return effortManager.add(systemEffort);
		}
		else{
		
			return effortManager.updata(systemEffort);
		}
	}

	public List<SystemEffort> findByAccount(String account) {
		// TODO Auto-generated method stub
		return effortManager.findByAccount(account);
	}

	public List<SystemEffort> find(SystemEffort systemEffort) {
		// TODO Auto-generated method stub
		return effortManager.find(systemEffort);
	}

	public List<SystemEffort> findBetweenDate(Date begindate, Date enddate) {
		// TODO Auto-generated method stub
		return effortManager.findBetweenDate(begindate, enddate);
	}

	public List<SystemEffort> findByProject(int projectId) {
		// TODO Auto-generated method stub
		return effortManager.findByProject(projectId);
	}

	public Pager<SystemEffort> findByPage(int start, int limit, SystemEffort SystemEffort, String sortName, boolean asc) {
		// TODO Auto-generated method stub
		return effortManager.findByPage(start, limit, SystemEffort, sortName, asc);
	}

	public List<SystemEffort> findList(SystemEffort systemEffort, String order,
			String orderTpye) {
		// TODO Auto-generated method stub
		return effortManager.findList(systemEffort, order, orderTpye);
	}

	public int batchDelete(Integer... ids) {
		// TODO Auto-generated method stub
		return effortManager.batchDelete(ids);
	}

	public Pager<SystemEffort> findByDate(int start, int limit,
			SystemEffort effort, Date startDate, Date endDate, String sortName,
			boolean asc) {
		// TODO Auto-generated method stub
		return effortManager.findByDate(start, limit, effort, startDate, endDate, sortName, asc);
	}

	public SystemEffort findById(int id) {
		// TODO Auto-generated method stub
		return effortManager.findById(id);
	}

}
