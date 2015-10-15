package org.tinygroup.sdpm.action.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;
import org.tinygroup.sdpm.product.dao.pojo.ProductRelease;
import org.tinygroup.sdpm.product.service.PlanService;
import org.tinygroup.sdpm.product.service.ReleaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/a/product/common")
public class ProductCommonAction   extends BaseController{
	
	@Autowired
	private PlanService planService;
	@Autowired
	private ReleaseService releaseService;

	public static void main(String[] args) {
		List<Date> list = new ArrayList<Date>();
		list.add(new Date(2015, 12, 2));
		list.add(new Date(2014, 3, 3));
		Collections.sort(list, new Comparator<Date>() {

			public int compare(Date o1, Date o2) {
				// TODO Auto-generated method stub
				int i = o1.compareTo(o2);
				System.out.println(i);
				return i;
			}
		});
		System.out.println(list.get(0));
	}

	@RequestMapping("/a/roadMap")
	public String roadMap(HttpServletRequest request,Model model){

		ProductPlan plan = new ProductPlan();
		ProductRelease release = new ProductRelease();
		if(request.getSession().getAttribute("sessionProductId")!=null){
			plan.setProductId((Integer)request.getSession().getAttribute("sessionProductId"));
			release.setProductId((Integer)request.getSession().getAttribute("sessionProductId"));
		}
		List<ProductPlan> plans = planService.findPlanList(plan, "planEndDate", "desc");
		List<ProductRelease> releases = releaseService.findReleaseList(release, "releaseDate", "desc");

		List<Object> list = new ArrayList<Object>();
		list.addAll(plans);
		list.addAll(releases);

		Collections.sort(list,  new Comparator<Object>() {

			public int compare(Object obj1, Object obj2) {

				Date date1 = null;
				Date date2 = null;
				if(obj1 instanceof ProductPlan){
					date1 = ((ProductPlan) obj2).getPlanEndDate();
				}else if (obj1 instanceof ProductRelease) {
					date1 = ((ProductRelease) obj1).getReleaseDate();
				}

				if (obj2 instanceof ProductPlan) {
					date2 = ((ProductPlan) obj2).getPlanEndDate();
				}else if (obj2 instanceof ProductRelease) {
					date2 = ((ProductRelease) obj2).getReleaseDate();
				}
				if(date1!=null&&date2!=null){
					return date2.compareTo(date1);
				}else{
					return 0;
				}
			}
		});
		List<Object> lo = list;
		model.addAttribute("list", list);
		return "/product/page/project/product-roadmap.page";
	}
	
}
