package org.tinygroup.sdpm.action.system;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.system.dao.pojo.SystemEffort;
import org.tinygroup.sdpm.system.service.inter.EffortService;
import org.tinygroup.tinysqldsl.Pager;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Controller
@RequestMapping("system/effort")
public class EffortAction extends BaseController{
	@Autowired
	private EffortService effortService;
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
}
