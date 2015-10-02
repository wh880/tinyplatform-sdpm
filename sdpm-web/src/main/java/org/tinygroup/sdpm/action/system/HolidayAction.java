package org.tinygroup.sdpm.action.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.system.dao.pojo.Holiday;
import org.tinygroup.sdpm.system.service.inter.HolidayService;
import org.tinygroup.tinysqldsl.Pager;
@Controller
@RequestMapping("system/holiday")
public class HolidayAction extends BaseController{
	@Autowired
	private HolidayService holidayService;
	@RequestMapping("findPager")
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
	@RequestMapping("find")
	public String find(Holiday holiday,Model model){
		List<Holiday> list = holidayService.find(holiday);
		model.addAttribute("list", list);
		return "/system/page/holiday/data/holidaydata.pagelet";
	}
	@RequestMapping(value ="save",method = RequestMethod.POST)
	public String saveHoliday(Holiday holiday,Model model){
		if(holiday.getHolidayId()==null){
			holidayService.add(holiday);
		}else{
			holidayService.update(holiday);
		}
		model.addAttribute("holiday", holiday);
		return " ";
	}
   public String deleteHoliday(Integer holidayId){
	   if(holidayId!=null){
		   Holiday holiday = new Holiday();
		   holiday.setHolidayId(holidayId);
		   holidayService.delete(holiday);
	   }
	 return " ";
   }
}
