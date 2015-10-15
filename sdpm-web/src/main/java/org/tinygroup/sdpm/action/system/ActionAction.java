package org.tinygroup.sdpm.action.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;
import org.tinygroup.sdpm.system.dao.pojo.Holiday;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.service.inter.ActionService;
import org.tinygroup.sdpm.system.service.inter.HolidayService;
import org.tinygroup.tinysqldsl.Pager;
@Controller
@RequestMapping("a/system/action")
public class ActionAction extends BaseController{
	@Autowired
	private ActionService actionService;
//	@Autowired
//	private HolidayService holidayService;
	@RequestMapping("find")
	public String find(SystemAction action,Model model){
		List<SystemAction> actions=actionService.find(action);
//		Integer[] ids = new Integer[actions.size()];
//		for(int i=0,n=actions.size();i<n;i++){
//			ids[i]=actions.get(i).getActionObjectId();
//		}
//		List<Holiday> holidays=holidayService.findByIds(ids);
//		model.addAttribute("holiday", holidays);
		model.addAttribute("action", actions);
		return "/system/page/holiday/holiday-dynamic.pagelet";
	}
	
	
	@RequestMapping("/list")
	public String list(SystemAction action,
			@RequestParam(required = false,defaultValue = "1")int page,
			@RequestParam(required = false,defaultValue = "10")int pagesize,
			@RequestParam(required = false,defaultValue = "actionDate")String order,
			@RequestParam(required = false,defaultValue = "asc")String ordertype,Model model){
		
	/*	if(request.getSession().getAttribute("sessionProductId")!=null){
			plan.setProductId((Integer)(request.getSession().getAttribute("sessionProductId")));
		}
		*/
		Pager<SystemAction>  pagerSystemAction = actionService.findSystemActionPager(page, pagesize, action, order, ordertype);

		model.addAttribute("systemAction",pagerSystemAction);

		return "/product/data/tinydynamicdata.pagelet";
	}
}
