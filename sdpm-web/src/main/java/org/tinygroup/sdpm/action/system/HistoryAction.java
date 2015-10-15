package org.tinygroup.sdpm.action.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.system.dao.pojo.SystemHistory;
import org.tinygroup.sdpm.system.service.inter.HistoryService;
@Controller
@RequestMapping("a/system/history")
public class HistoryAction extends BaseController{
	@Autowired
	private HistoryService historyService;
	@RequestMapping("find")
	public String find(SystemHistory history,Model model){
		List<SystemHistory> histories = historyService.find(history);
		model.addAttribute("history", histories);
		return "/product/page/tabledemo/historyrecord.pagelet";
	}
}
