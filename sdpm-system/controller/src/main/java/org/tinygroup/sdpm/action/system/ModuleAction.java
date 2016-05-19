package org.tinygroup.sdpm.action.system;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.inter.ProductService;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;
import org.tinygroup.sdpm.project.service.inter.ProjectProductService;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.service.inter.ModuleService;
import org.tinygroup.sdpm.util.ModuleUtil;
import org.tinygroup.sdpm.util.UserUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("a/system/module")
public class ModuleAction extends BaseController {
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProjectProductService projectProductService;
    @Autowired
    private ProjectService projectService;


    @ResponseBody
    @RequestMapping("tree")
    public List<Map<String, Object>> ajax(SystemModule systemModule, HttpServletResponse response, @RequestParam(value = "type", defaultValue = "name") String type) {
        response.setContentType("application/json; charset=UTF-8");
        List<Map<String, Object>> mapList = Lists.newArrayList();
        List<SystemModule> list = moduleService.findModuleList(systemModule);
        if (list != null && list.size() > 0) {
            mergeModule(list, mapList, "0", type, true, true);
        }
        return mapList;
    }

    @ResponseBody
    @RequestMapping("projectTree")
    public List<Map<String, Object>> projectTree(SystemModule systemModule, HttpServletResponse response, int openProject) {
        response.setContentType("application/json; charset=UTF-8");
        List<Map<String, Object>> mapList = Lists.newArrayList();
        List<ProjectProduct> projectProducts = projectProductService.findProductListByProjectId(systemModule.getModuleRoot());
        List<Integer> integers = new ArrayList<Integer>();
        for (ProjectProduct p : projectProducts) {
            integers.add(p.getProductId());
        }
        Integer[] pIds = new Integer[integers.size()];
        List<Product> products = productService.findProductListByIds(integers.toArray(pIds));
        mergeProductModule(products, "story", mapList, "name", false, false, "project");
        if (openProject > 0) {
            List<SystemModule> systemModules = moduleService.findModuleList(systemModule);
            mergeModule(systemModules, mapList, "0", "name", true, true);
        }
        return mapList;
    }

    @ResponseBody
    @RequestMapping("bugTree")
    public List<Map<String, Object>> bugTree(SystemModule systemModule, HttpServletResponse response) {
        response.setContentType("application/json; charset=UTF-8");
        List<Map<String, Object>> mapList = Lists.newArrayList();
        List<SystemModule> bugModules = moduleService.findModuleList(systemModule);
        systemModule.setModuleType("story");
        List<SystemModule> productModules = moduleService.findModuleList(systemModule);
        mergeModule(productModules, mapList, "0", "name", true, true);
        mergeModule(bugModules, mapList, "0", "name", true, true);
        return mapList;
    }

    @ResponseBody
    @RequestMapping("docProductTree")
    public List<Map<String, Object>> productDocTree() {
        List<Map<String, Object>> mapList = Lists.newArrayList();
        List<Product> products = productService.getProductByUser(UserUtils.getUserId(), 0, null, Product.CHOOSE_OPENED);
        mergeProductModule(products, "productDoc", mapList, "name", true, false, "doc");
        return mapList;
    }

    @ResponseBody
    @RequestMapping("docProjectTree")
    public List<Map<String, Object>> projectDocTree(HttpServletResponse response) {
        response.setContentType("application/json; charset=UTF-8");
        List<Map<String, Object>> mapList = Lists.newArrayList();
        List<Project> projects = projectService.getUserProjectList(UserUtils.getUserId());
        for (Project p : projects) {
            SystemModule module = new SystemModule();
            module.setModuleRoot(p.getProjectId());
            module.setModuleType("projectDoc");
            List<SystemModule> systemModules = moduleService.findModuleList(module);
            Map<String, Object> mapTop = Maps.newHashMap();
            mapTop.put("id", "p" + p.getProjectId());
            mapTop.put("pId", 0);
            mapTop.put("open", true);
            mergeModule(systemModules, mapList, "p" + p.getProjectId().toString(), "name", true, true);
            mapTop.put("add", true);
            mapTop.put("edit", false);
            mapTop.put("name", p.getProjectName());
            mapList.add(mapTop);
        }
        return mapList;
    }

    @RequestMapping("list")
    public String findModule(String moduleType, Model model) {
        SystemModule systemModule = new SystemModule();
        systemModule.setModuleType(moduleType);
        List<SystemModule> list = moduleService.findModuleList(systemModule);
        model.addAttribute("list", list);
        return "/system/page/dictionaries/dict_list.page";
    }

    @RequestMapping("view")
    public String viewModule(Integer moduleId, Model model) {
        SystemModule module = moduleService.findById(moduleId);
        model.addAttribute("module", module);
        return "/system/page/dictionaries/dict_view.pagelet";
    }

    @ResponseBody
    @RequestMapping("delete")
    public Map<String, String> deleteModule(Integer id) {
        Map<String, String> map = new HashedMap();
        if (id != null) {
            delteModule(id);
        }
        map.put("status", "y");
        map.put("info", "删除成功");


        return map;
    }

    @ResponseBody
    @RequestMapping("ajax/delete")
    public Map<String, String> ajaxDeleteModule(Integer moduleId) {
        if (moduleId != null) {
            delteModule(moduleId);
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
        list = moduleService.findModuleList(systemModule);
        model.addAttribute("moduleList", list);

        if (moduleId != null) {

            SystemModule module = moduleService.findById(moduleId);
            model.addAttribute("module", module);
        } else {
            SystemModule module = new SystemModule();
            moduleService.findModuleList(module);
            model.addAttribute("module", module);
        }
        return "/system/page/dictionaries/dict_edit.pagelet";
    }

    @RequestMapping("save")
    public String saveModule(SystemModule systemModule) {
        if (systemModule.getModuleId() == null) {
            systemModule.setModuleRoot(0);
            systemModule.setModuleGrade(0);
            systemModule.setModuleOrder(0);
            systemModule.setModulePath(moduleService.findById(systemModule.getModuleParent()).getModulePath() + "," + systemModule.getModulePath());
            systemModule.setModuleType("dict");
            if (systemModule.getModuleParent() == null) {
                systemModule.setModuleParent(0);
            }

            moduleService.addSystemModule(systemModule);
        } else {

            moduleService.editNameAndTitle(systemModule);
        }
        return "redirect: list?moduleType=dict";
    }

    @ResponseBody
    @RequestMapping("moduleAdd")
    public Map moduleAdd(SystemModule module) {
        SystemModule pModule = moduleService.findById(module.getModuleParent());
        if (module.getModuleRoot() == null) {
            module.setModuleRoot(pModule.getModuleRoot());
        }
        if (module.getModuleType() == null) {
            module.setModuleType(pModule.getModuleType());
        }
        return ajaxSaveModule(module);
    }

    @ResponseBody
    @RequestMapping("ajax/save")
    public Map<String, String> ajaxSaveModule(SystemModule systemModule) {

        if (systemModule.getModuleId() == null) {
            systemModule.setModuleGrade(0);
            systemModule.setModuleOrder(0);
            String path = "";
            if (systemModule.getModuleParent() != 0) {
                path = moduleService.findById(systemModule.getModuleParent()).getModulePath();
            }
            systemModule.setModulePath((StringUtil.isBlank(path) ? "" : path) + systemModule.getModuleParent() + ",");
            if (systemModule.getModuleParent() == null) {
                systemModule.setModuleParent(0);
            }
            moduleService.addSystemModule(systemModule);
        } else {
            SystemModule systemModule1 = moduleService.findById(systemModule.getModuleId());
            if (systemModule.getModuleParent() != systemModule1.getModuleParent()) {
                systemModule.setModulePath(moduleService.findById(systemModule.getModuleParent()).getModulePath() + systemModule.getModuleParent() + ",");
            }
            moduleService.editNameAndTitle(systemModule);
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "success");
        map.put("info", "成功");
        return map;
    }

    @ResponseBody
    @RequestMapping("ajax/docTreeSave")
    public Map<String, String> ajaxSaveDocTreeModule(String moduleParent, String moduleType, String moduleName) {
        SystemModule systemModule = new SystemModule();
        if (moduleParent.contains("p")) {
            systemModule.setModuleRoot(Integer.parseInt(moduleParent.substring(1)));
            systemModule.setModuleParent(0);
        } else {
            systemModule.setModuleRoot(moduleService.findById(Integer.parseInt(moduleParent)).getModuleRoot());
            systemModule.setModuleParent(Integer.parseInt(moduleParent));
        }
        systemModule.setModuleType(moduleType);
        systemModule.setModuleName(moduleName);
        systemModule.setModuleGrade(0);
        systemModule.setModuleOrder(0);
        systemModule.setModulePath((systemModule.getModuleParent() != 0 ? moduleService.findById(systemModule.getModuleParent()).getModulePath() : "") + systemModule.getModuleParent() + ",");
        if (systemModule.getModuleParent() == null) {
            systemModule.setModuleParent(0);
        }
        moduleService.addSystemModule(systemModule);

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
        moduleService.batchDeleteSystemModule(intIds);
        Map<String, String> map = new HashedMap();
        map.put("info", "success");
        map.put("status", "y");
        return map;
    }

    private void mergeModule(List<SystemModule> systemModules, List<Map<String, Object>> maps, String parent, String nameOrTitle, boolean add, boolean edit) {
        for (SystemModule systemModule : systemModules) {
            if (!parent.contains("p") && systemModule.getModuleParent() == Integer.parseInt(parent)) {
                mergeSingleModule(systemModules, systemModule, maps, parent, nameOrTitle, add, edit);
            }
            if (parent.contains("p") && systemModule.getModuleParent() == 0) {
                mergeSingleModule(systemModules, systemModule, maps, parent, nameOrTitle, add, edit);
            }
        }
    }

    @ResponseBody
    @RequestMapping("/moduleList")
    public List<SystemModule> findProduct(SystemModule module) {

        List<SystemModule> list = moduleService.findModuleList(module);

        return list;
    }


    @ResponseBody
    @RequestMapping("/data")
    public List data() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        SystemModule module = new SystemModule();
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

    @ResponseBody
    @RequestMapping("/{forwordPager}/add")
    public Map addModule(SystemModule module, @PathVariable(value = "forwordPager") String forwordPager) {

        moduleService.addSystemModule(module);
        return resultMap(true, "添加成功");

    }

    @ResponseBody
    @RequestMapping("/{forwordPager}/edit")
    public Map editModule(Integer moduleId, String moduleName, @PathVariable(value = "forwordPager") String forwordPager) {
        SystemModule module = moduleService.findById(moduleId);
        module.setModuleName(moduleName);
        moduleService.editModule(module);

        return resultMap(true, "修改成功");
    }

    @ResponseBody
    @RequestMapping("/{forwordPager}/deleteTree")
    public Map deleteSystemModule(Integer moduleId, @PathVariable(value = "forwordPager") String forwordPager) {
        moduleService.deleteModuleById(moduleId);
        return resultMap(true, "删除成功");
    }

    @ResponseBody
    @RequestMapping("/dataone")
    public List dataone() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        SystemModule module = new SystemModule();
        module.setModuleType("story");

        List<SystemModule> moduleList = moduleService.findAllModules(module);

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

        module.setModuleType("story");

        module.setModuleType("story");
        List<SystemModule> list = moduleService.findAllModules(module);
        String modulePath = ModuleUtil.getPath(module.getModuleParent(), ">", null, false);
        model.addAttribute("list", list);
        model.addAttribute("modulePath", modulePath);
        return "/product/page/list/module/product-modular.page";
    }

    private void mergeProductModule(List<Product> products, String moduleType, List<Map<String, Object>> mapList, String nameOrTitle, boolean add, boolean edit, String type) {
        for (Product p : products) {
            SystemModule module = new SystemModule();
            module.setModuleRoot(p.getProductId());
            module.setModuleType(moduleType);
            List<SystemModule> systemModules = moduleService.findModuleList(module);
            Map<String, Object> mapTop = Maps.newHashMap();
            mapTop.put("id", "p" + p.getProductId());
            mapTop.put("pId", 0);
            mapTop.put("open", true);
            if ("doc".equals(type)) {
                mergeModule(systemModules, mapList, "p" + p.getProductId().toString(), nameOrTitle, true, true);
            } else {
                mergeModule(systemModules, mapList, "p" + p.getProductId().toString(), nameOrTitle, false, false);
            }
            mapTop.put("add", add);
            mapTop.put("edit", edit);
            mapTop.put("name", p.getProductName());
            mapList.add(mapTop);
        }
    }

    private void mergeSingleModule(List<SystemModule> systemModules, SystemModule systemModule, List<Map<String, Object>> maps, String parent, String nameOrTitle, boolean add, boolean edit) {
        Map<String, Object> mapTop = Maps.newHashMap();
        mapTop.put("id", systemModule.getModuleId());
        mapTop.put("pId", parent);
        mapTop.put("open", true);
        mergeModule(systemModules, maps, systemModule.getModuleId().toString(), nameOrTitle, add, edit);
        mapTop.put("add", add);
        mapTop.put("edit", edit);
        if ("title".equals(nameOrTitle)) {
            mapTop.put("name", systemModule.getModuleTitle());
        } else {
            mapTop.put("name", systemModule.getModuleName());
        }
        maps.add(mapTop);
    }

    private void delteModule(int id) {
        SystemModule module = new SystemModule();
        module.setModuleParent(id);
        List<SystemModule> moduleList = moduleService.findModuleList(module);
        for (SystemModule module1 : moduleList) {
            deleteModule(module1.getModuleId());
        }
        moduleService.deleteModuleById(id);
    }

}
