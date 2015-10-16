package org.tinygroup.sdpm.action.system;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.util.DateUtils;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.sdpm.project.service.inter.TaskService;
import org.tinygroup.sdpm.system.dao.pojo.SystemEffort;
import org.tinygroup.sdpm.system.service.inter.EffortService;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("a/system/effort")
public class EffortAction extends BaseController{
	@Autowired
	private EffortService effortService;
	@Autowired
	private TaskService taskService;
	@RequestMapping("list")
	public String list(int taskId,SystemEffort effort,Model model){
		String order="effort_date";
		String orderTpye="desc";
		List<SystemEffort> list = effortService.findList(effort, order, orderTpye);
		List<SystemEffort> effortList = new ArrayList<SystemEffort>();
		if(list.size()>5){
			for(int i=0;i<5;i++){
				effortList.add(list.get(i));
			}
		}
		else{
			effortList=list;
		}
		model.addAttribute("taskId", taskId);
		model.addAttribute("list", effortList);
		return "project/task/note.page";
		
	}
	@RequestMapping("save")
	public String add(SystemEffort systemEffort,Model model){
		if(systemEffort.getEffortId()==null){
			
		systemEffort.setEffortBegin(new SimpleDateFormat("yyyy-MM-dd").format(systemEffort.getEffortDate()));
		}
		effortService.save(systemEffort);
		return "project/note/notetable.page";
	}
	@RequestMapping("findPager")
   public String findPager(Integer start,Integer limit,String order ,String ordertype, Integer effortId, Model model){
	   boolean asc = true;
       if ("desc".equals(ordertype)) {
           asc = false;
       }
       SystemEffort effort = new SystemEffort();
       effort.setEffortId(effortId);
       Pager<SystemEffort> effortPager = effortService.findByPage(start, limit,effort, order, asc);
       model.addAttribute("effortPager", effortPager);
       return "project/note/tableData.pagelet"; 
   }
	@ResponseBody
	@RequestMapping("event")
	public List<Map<String, Object>> effortEvent(SystemEffort systemEffort,HttpServletResponse response)
	{
		response.setContentType("application/json; charset=UTF-8");
		List<Map<String, Object>> maplist = Lists.newArrayList();
		List<SystemEffort> list = effortService.find(systemEffort);
		if(list!=null&&list.size()>0){
			for(int i=0,size=list.size();i<size;i++){
				SystemEffort effort =list.get(i);
				Map<String, Object> map = Maps.newHashMap();
				map.put("id",effort.getEffortId());
				map.put("title", effort.getEffortWork());
				map.put("start", effort.getEffortBegin());
				map.put("end", effort.getEffortEnd());
				maplist.add(map);
			}
		}
		return maplist;
	}
	@ResponseBody
	@RequestMapping("batchDelete")
	public Map<String, String> batchDelete(String ids){
		String[] sids = ids.split(",");
		Integer[] intIds = new Integer[sids.length];
		for(int i=0;i<sids.length;i++){
			intIds[i] = Integer.valueOf(sids[i]);
		} 
		effortService.batchDelete(intIds);
		Map<String, String> map = new HashedMap();
		map.put("info", "success");
	   	map.put("status","y" );
   		return map;
	}
	@RequestMapping("date")
	public String findByDate(int start,int limit,String order ,String ordertype, int date,SystemEffort systemEffort,Model model) throws ParseException{
		Date startDate = new Date();
		Date endDate = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar c =Calendar.getInstance();
		Pager<SystemEffort> pager = null ;
		String dateStr=new SimpleDateFormat("yyyy-MM-dd").format(startDate);
		new SimpleDateFormat("yyyy-MM-dd").format(endDate);
		boolean asc = true;
	    if ("desc".equals(ordertype)) {
	           asc = false;
	    }
		
		if(date==1){
			Date today=sf.parse(dateStr);
			systemEffort.setEffortDate(today);
			pager=effortService.findByPage(start, limit, systemEffort, order, asc);
		}
		if(date==2){
			int day =c.get(Calendar.DATE);
			c.set(Calendar.DATE, day-1);
			String ydStr = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
			Date yesterday=sf.parse(ydStr);
			systemEffort.setEffortDate(yesterday);
			pager=effortService.findByPage(start, limit, systemEffort, order, asc);
		}
		if(date==3){

			startDate = DateUtils.getFirstDayOfWeek(startDate);
			endDate = DateUtils.getLastDayOfWeek(startDate);
			pager= effortService.findByDate(start, limit, systemEffort, startDate, endDate, order, asc);
			
		}
		if(date==4){
			startDate = DateUtils.getFirstDayOfWeek(DateUtils.getLastDayOfLastWeek(startDate));
			endDate = DateUtils.getLastDayOfLastWeek(endDate);
			pager = effortService.findByDate(start, limit, systemEffort, startDate, endDate, order, asc);
			
		}
		if(date==5){
			startDate = DateUtils.getFirstDayOfMonth(startDate);
			pager= effortService.findByDate(start, limit, systemEffort, startDate, endDate, order, asc);
		}
		if(date==6){
			startDate = DateUtils.getFirstDayOfMonth(DateUtils.getLastDayOfLastMonth(startDate));
			endDate = DateUtils.getLastDayOfLastMonth(endDate);
			pager = effortService.findByDate(start, limit, systemEffort, startDate, endDate, order, asc);
		}
		if(date==0){
			pager=effortService.findByPage(start, limit, systemEffort, order, asc);//全部日志
		}
	    
		   model.addAttribute("effortPager", pager);
	       return "project/note/tableData.pagelet"; 
	}
	@RequestMapping("calendar")
	public String calendar(String Action,@RequestParam(required = false)String id,String date, Model model) throws Exception{

		ProjectTask task= new ProjectTask();
		List<ProjectTask> taskList = taskService.findListTask(task);
		model.addAttribute("taskList", taskList);
		if(Action.equals("add")){
			SystemEffort effort = new SystemEffort();
			effort.setEffortBegin(date);
			model.addAttribute("effort", effort);
		}
		if(Action.equals("edit")){
			SystemEffort effort = effortService.findById(Integer.valueOf(id));
			
			model.addAttribute("effort", effort);
		}
		return "project/note/calendarEvent.pagelet";
	}
	@ResponseBody
	@RequestMapping("operate")
	public Map<String, String> operate(SystemEffort effort,String date,String isvip,Model model){
		Map<String, String> map = new HashedMap();
		if(isvip.equals("y")){
			effortService.batchDelete(effort.getEffortId());
			map.put("status", "y");
			map.put("info", "1");
		    
			return map;
		}
		else if(isvip.equals("n"))
		{ 
			effortService.save(effort);
			map.put("status", "y");
			map.put("info", "1");
		    
			return map;
			
		}
		else{
			map.put("status", "y");
			map.put("info", "2");
			return map;
		}
	}
	
}
