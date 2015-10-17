package org.tinygroup.sdpm.action.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.system.dao.pojo.Holiday;
import org.tinygroup.sdpm.system.service.inter.HolidayService;
import org.tinygroup.tinysqldsl.Pager;
@Controller
@RequestMapping("a/system")
public class HolidayAction extends BaseController{
	@Autowired
	private HolidayService holidayService;
	@Autowired
	private UserService userService;
	@RequestMapping("")
	public String index(){
		
		return "/system/page/holiday/manage.page"; 
	}
	@RequestMapping("holiday")
	public String holiday(){
		return "/system/page/holiday/holiday.page";
	}
	@RequestMapping("holiday/add")
	public String holidayAdd(Model model){
	List<OrgUser> orgUsers=userService.findUserList(new OrgUser());
	model.addAttribute("users", orgUsers);
	return "/system/page/holiday/holiday-add.pagelet";
	}
	@RequestMapping("holiday/findPager")
	public String fingPage(Integer start ,Integer limit ,
			String order,String ordertype,Holiday holiday,Model model){
		boolean asc = true;
//		orderType="asc";
		if("desc".equals(ordertype)){
			asc=false;
		}
//		holiday.setHolidayId(1);
		
	    Pager<Holiday> holidayPage=	holidayService.findByPage(start, limit, holiday, order, asc);
		model.addAttribute("holiday", holidayPage);
		return "/system/page/holiday/data/holidaydata.pagelet";
	}
	@RequestMapping("holiday/find")
	public String find(Holiday holiday,Model model){
		List<OrgUser> orgUsers=userService.findUserList(new OrgUser());
		Holiday day = holidayService.findById(holiday.getHolidayId());
		model.addAttribute("users", orgUsers);
		model.addAttribute("holiday", day);
		return "/system/page/holiday/edit.page";
	}
	@RequestMapping(value ="holiday/save",method = RequestMethod.POST)
	public String saveHoliday(@RequestParam(required = false)String selectList,Holiday holiday,Model model){
		if(holiday.getHolidayId()==null){
			List<Holiday> holidayList = new ArrayList<Holiday>();
			String[] dates=selectList.split(",");
			for(int i=0,n=dates.length;i<n;i++){
				Holiday day = new Holiday();
				
		     	day.setCompanyId(holiday.getCompanyId());
				
				day.setHoilidayRemark(holiday.getHoilidayRemark());
				
				day.setHolidayAccount(holiday.getHolidayAccount());
				
				day.setHolidayDate(dates[i]);
				day.setHolidayDeleted(holiday.getHolidayDeleted());
				day.setHolidayDetail(holiday.getHolidayDetail());
				day.setHolidayName(holiday.getHolidayName());
				day.setHolidayType(holiday.getHolidayType());
				holidayList.add(day);
			}
			
			holidayService.batchAdd(holidayList);
		}else{
			holidayService.update(holiday);
		}
		model.addAttribute("holiday", holiday);
		return "/system/page/holiday/holiday.page";
	}
	@ResponseBody
	@RequestMapping("holiday/delete")
    public Map<String, String> deleteHoliday(Integer id){
	   Map<String, String> map = new HashedMap();
	   if(id!=null){
		   Holiday holiday = new Holiday();
		   holiday.setHolidayId(id);
		   holidayService.delete(holiday);
		   map.put("info", "删除成功");
		   map.put("status", "y");
	     }
	   else{
		   map.put("info", "删除失败");
		   map.put("status", "n");
	   }
	   return map;
     }
//	@ResponseBody
//	@RequestMapping("holiday/delete")
//	public String deleteHoliday(Integer holidayId){
//		if(holidayId!=null){
//			   Holiday holiday = new Holiday();
//			   holiday.setHolidayId(holidayId);
//			   holidayService.delete(holiday);
//	    }
//		return "/system/page/holiday/holiday.page";
//	}
	@RequestMapping("holiday/manage")
	public String manage(Holiday holiday,Model model){
		List<Holiday> holidayList = holidayService.find(holiday);
		model.addAttribute("holiday", holidayList);
		return "/system/page/holiday/manage.page";
	}
	@RequestMapping("holiday/findIds")
	public String findIds(String ids,Model model){
		String[] sids = ids.split(",");
		Integer[] intIds = new Integer[sids.length];
		for(int i=0;i<sids.length;i++){
			intIds[i] = Integer.valueOf(sids[i]);
		}
		List<Holiday> holidayList=holidayService.findByIds(intIds);
		model.addAttribute("holiday", holidayList);
		return "/system/page/holiday/batch-del.pagelet";
	}
	@RequestMapping("holiday/batchDelete")
	public String batchDelete(Holidays holidays)
	{
	   List<Holiday> holiday= holidays.getHoliday();
	 
		holidayService.batchSofeDelete(holiday);
		return  "/system/page/holiday/holiday.page";
	}
	
}
