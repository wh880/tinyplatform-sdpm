package org.tinygroup.sdpm.action.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;
import org.tinygroup.sdpm.product.service.PlanService;

@Controller
@RequestMapping("/plan")
public class PlanAction {
	
	@Autowired
	private PlanService planService;
	
	@RequestMapping("/save")
		public String save(ProductPlan plan,Model model){
			planService.addPlan(plan);
			return"/product/page/tabledemo/product-addplan.page";
		}
	
}
