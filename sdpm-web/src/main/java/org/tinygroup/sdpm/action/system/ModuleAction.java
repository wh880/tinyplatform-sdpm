package org.tinygroup.sdpm.action.system;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;
import org.tinygroup.sdpm.project.service.inter.ProjectProductService;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.service.inter.ModuleService;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/system/module")
public class ModuleAction extends BaseController {
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProjectProductService projectProductService;
    @ResponseBody
    @RequestMapping("tree")
    public List<Map<String, Object>> ajax(SystemModule systemModule, HttpServletResponse response) {
        response.setContentType("application/json; charset=UTF-8");
        List<Map<String, Object>> mapList = Lists.newArrayList();
//		systemModule.setModuleType("dict");
        List<SystemModule> list = moduleService.findModules(systemModule);
        if (list != null && list.size() > 0) {
            mergeModule(list, mapList, "0");
        }
        return mapList;
    }
    @ResponseBody
    @RequestMapping("projectTree")
    public List<Map<String, Object>> projectTree(SystemModule systemModule, HttpServletResponse response){
        response.setContentType("application/json; charset=UTF-8");
        List<Map<String, Object>> mapList = Lists.newArrayList();
        List<ProjectProduct> projectProducts = projectProductService.findProducts(systemModule.getModuleRoot());
        List<Integer> integers = new ArrayList<Integer>();
        for(ProjectProduct p : projectProducts){
            integers.add(p.getProductId());
        }
        Integer[] pIds = new Integer[integers.size()];
        List<Product> products = productService.findProductList(integers.toArray(pIds));
        for(Product p : products){
            SystemModule module = new SystemModule();
            module.setModuleRoot(p.getProductId());
            module.setModuleType("story");
            List<SystemModule> systemModules = moduleService.findModules(module);
            Map<String, Object> mapTop = Maps.newHashMap();
            mapTop.put("id", "p"+p.getProductId());
            mapTop.put("pId", 0);
            mapTop.put("open", false);
            mergeModule(systemModules, mapList, "p"+p.getProductId().toString());
            mapTop.put("isParent", true);
            mapTop.put("add", false);
            mapTop.put("edit", false);
            mapTop.put("name", p.getProductName());
            mapList.add(mapTop);
        }
        List<SystemModule> systemModules = moduleService.findModules(systemModule);
        mergeModule(systemModules, mapList, "0");
        return mapList;
    }

    @RequestMapping("list")
    public String findModule(String moduleType, Model model) {
        SystemModule systemModule = new SystemModule();
        systemModule.setModuleType(moduleType);
        List<SystemModule> list = moduleService.findModules(systemModule);
        model.addAttribute("list", list);
        return "/system/page/dictionaries/dict_list.page";
    }

    @RequestMapping("view")
    public String viewModule(Integer moduleId, Model model) {
        SystemModule module = moduleService.findById(moduleId);
        model.addAttribute("module", module);
        return "/system/page/dictionaries/dict_view.pagelet";
    }

    @RequestMapping("delete")
    public String deleteModule(Integer moduleId) {
        if (moduleId != null) {
            moduleService.deleteById(moduleId);
        }
        return "redirect: list?moduleType=dict";
    }
    @ResponseBody
    @RequestMapping("ajax/delete")
    public Map<String, String> ajaxDeleteModule(Integer moduleId) {
        if (moduleId != null) {
            moduleService.deleteById(moduleId);
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "success");
        map.put("info", "删除成功");
        return map;
    }

    @RequestMapping("find")
    public String find(Integer moduleId, Model model) {

        List<SystemModule> list = new ArrayList<SystemModule>();
        SystemModule systemModule = new SystemModule();
        systemModule.setModuleType("dict");
        list = moduleService.findModules(systemModule);
        model.addAttribute("moduleList", list);

        if (moduleId != null) {

            SystemModule module = moduleService.findById(moduleId);
            model.addAttribute("module", module);
        } else {
            SystemModule module = new SystemModule();
            moduleService.findModules(module);
            model.addAttribute("module", module);
        }
        return "/system/page/dictionaries/dict_edit.pagelet";
    }

    @RequestMapping("save")
    public String saveModule(SystemModule systemModule, Model model) {
        SystemModule systemModule1 = moduleService.findById(systemModule.getModuleId());
        if (systemModule.getModuleId() == null) {
            systemModule.setModuleRoot(0);
            systemModule.setModuleGrade(0);
            systemModule.setModuleOrder(0);
            systemModule.setModulePath(moduleService.findById(systemModule.getModuleParent()).getModulePath()+","+systemModule.getModulePath());
            systemModule.setModuleType("dict");
            if (systemModule.getModuleParent() == null) {
                systemModule.setModuleParent(0);
            }

            moduleService.add(systemModule);
        } else {

            moduleService.eidtNameAndTiele(systemModule);
        }
        return "redirect: list?moduleType=dict";
    }
    @ResponseBody
    @RequestMapping("ajax/save")
    public Map<String, String> ajaxSaveModule(SystemModule systemModule) {

        if (systemModule.getModuleId() == null) {
            systemModule.setModuleGrade(0);
            systemModule.setModuleOrder(0);
            systemModule.setModulePath(moduleService.findById(systemModule.getModuleParent()).getModulePath()+systemModule.getModuleParent()+",");
            if (systemModule.getModuleParent() == null) {
                systemModule.setModuleParent(0);
            }
            moduleService.add(systemModule);
        } else {
            SystemModule systemModule1 = moduleService.findById(systemModule.getModuleId());
            if(systemModule.getModuleParent()!=systemModule1.getModuleParent()){
                systemModule.setModulePath(moduleService.findById(systemModule.getModuleParent()).getModulePath()+systemModule.getModuleParent()+",");
            }
            moduleService.eidtNameAndTiele(systemModule);
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "success");
        map.put("info", "成功");
        return map;
    }

    @ResponseBody
    @RequestMapping("batchDelete")
    public Map<String, String> batchDelete(String ids) {
        String[] sids = ids.split(",");
        Integer[] intIds = new Integer[sids.length];
        for (int i = 0; i < sids.length; i++) {
            intIds[i] = Integer.valueOf(sids[i]);
        }
        moduleService.batchDelete(intIds);
        Map<String, String> map = new HashedMap();
        map.put("info", "success");
        map.put("status", "y");
        return map;
    }

    private void mergeModule(List<SystemModule> systemModules, List<Map<String, Object>> maps, String parent) {
        for (SystemModule systemModule : systemModules) {
            if (!parent.contains("p")&&systemModule.getModuleParent() == Integer.valueOf(parent)) {
                int size = maps.size();
                Map<String, Object> mapTop = Maps.newHashMap();
                mapTop.put("id", systemModule.getModuleId());
                mapTop.put("pId", parent);
                mapTop.put("open", true);
                mergeModule(systemModules, maps, systemModule.getModuleId().toString());
                mapTop.put("isParent", maps.size() > size ? true : false);
                mapTop.put("add", true);
                mapTop.put("edit", true);
                mapTop.put("name", systemModule.getModuleName());
                maps.add(mapTop);
//				systemModules.remove(i);
            }
            if(parent.contains("p")&&systemModule.getModuleParent() == 0){
                int size = maps.size();
                Map<String, Object> mapTop = Maps.newHashMap();
                mapTop.put("id", systemModule.getModuleId());
                mapTop.put("pId", parent);
                mapTop.put("open", true);
                mergeModule(systemModules, maps, systemModule.getModuleId().toString());
                mapTop.put("isParent", maps.size() > size ? true : false);
                mapTop.put("add", true);
                mapTop.put("edit", true);
                mapTop.put("name", systemModule.getModuleName());
                maps.add(mapTop);
            }
        }
    }

    @ResponseBody
    @RequestMapping("/moduleList")
    public List<SystemModule> findProduct(SystemModule module) {

        List<SystemModule> list = moduleService.findModules(module);

        return list;
    }


    @ResponseBody
    @RequestMapping("/data")
    public List data(String check) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        SystemModule module = new SystemModule();
        module.setModuleType("story");
        List<SystemModule> moduleList = moduleService.findModuleList(module);
        /*if (check == null || !check.equals("n")) {
            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("id", -1);
            map1.put("pId", 0);
            map1.put("name", "所有部门");
            list.add(map1);
        }
*/
        for (SystemModule d : moduleList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", d.getModuleId());
            map.put("pId", d.getModuleParent());
            map.put("open", true);
            map.put("add", true);
            map.put("edit", true);
            map.put("name", d.getModuleName());
            list.add(map);
        }
        return list;

    }


    @RequestMapping("/{forwordPager}/add")
    public String addModule(SystemModule module,@PathVariable(value="forwordPager")String forwordPager) {

        moduleService.add(module);

        if("story".equals(forwordPager)){
    		return "/product/page/project/togglebox.page";
    	}else if ("promodule".equals(forwordPager)) {
    		return "/product/page/project/product-modular.page";
    	}
        return "";

    }


    @RequestMapping("/{forwordPager}/edit")
     public  String editModule(Integer moduleId,String moduleName,@PathVariable(value="forwordPager")String forwordPager) {
        SystemModule module = moduleService.findById(moduleId);
        module.setModuleName(moduleName);
        moduleService.edit(module);

        if("story".equals(forwordPager)){
    		return "/product/page/project/togglebox.page";
    	}else if ("promodule".equals(forwordPager)) {
    		return "/product/page/project/product-modular.page";
    	}
        return "";
    }


    @RequestMapping("/{forwordPager}/deleteTree")
    public String deleteSystemModule(Integer moduleId,@PathVariable(value="forwordPager")String forwordPager){
        moduleService.deleteById(moduleId);
        if("story".equals(forwordPager)){
    		return "/product/page/project/togglebox.page";
    	}else if ("promodule".equals(forwordPager)) {
    		return "/product/page/project/product-modular.page";
    	}
        return "";
    }

    @ResponseBody
    @RequestMapping("/dataone")
    public List dataone(String check) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        SystemModule module= new SystemModule();
        module.setModuleType("story");

        List<SystemModule> moduleList = moduleService.findModuleList(module);

        for (SystemModule d : moduleList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", d.getModuleId());
            map.put("pId", d.getModuleParent());
            map.put("open", true);
            map.put("add", true);
            map.put("edit", true);
            map.put("name", d.getModuleName());
            list.add(map);
        }
        return list;

    }
    @RequestMapping("/findProductModule")
    public String findProductModule(SystemModule module, Model model) {
    	if(module.getModuleParent()==null){
    		module.setModuleParent(0);
    	}
    	module.setModuleType("story");
        List<SystemModule> list = moduleService.findModules(module);
        model.addAttribute("list", list);
        return "/product/page/project/product-modular.page";
    }
}
