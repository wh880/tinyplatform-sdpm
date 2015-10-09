package org.tinygroup.sdpm.action.org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgDept;
import org.tinygroup.sdpm.org.service.inter.DeptService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/org/dept")
public class DeptAction extends BaseController {
    @Autowired
    private DeptService deptService;

    @RequestMapping("/add")
    public String addDept(Integer orgDeptParent, String orgDeptName) {
        OrgDept dept = new OrgDept();
        dept.setOrgDeptParent(orgDeptParent);
        dept.setOrgDeptName(orgDeptName);
        deptService.addDept(dept);
        return "organization/user/user.page";
    }

    @RequestMapping("edit")
    public String editDept(Integer orgDeptId, String orgDeptName) {
        OrgDept dept = deptService.findDept(orgDeptId);
        dept.setOrgDeptName(orgDeptName);
        deptService.updateDept(dept);
        return "organization/user/user.page";
    }

    @RequestMapping("delete")
    public String deleteDept(Integer orgDeptId) {
        deptService.deleteDept(orgDeptId);
        return "organization/user/user.page";
    }
//    @ResponseBody
//    @RequestMapping("/tree")
//    public List<Map<String,Object>> tree(OrgDept orgDept, HttpServletResponse response) {
//        response.setContentType("application/json; charset=UTF-8");
//        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//        List<OrgDept> deptList=deptService.findDeptList(orgDept);
//        if(deptList!=null&&deptList.size()>0) {
//            mergeDept(deptList,list,0);
//        }
//        return  list;
//    }

//    private void mergeDept(List<OrgDept> orgDepts, List<Map<String, Object>> maps, int parent){
//        for(OrgDept orgDept:orgDepts){
//            if(orgDept.getOrgDeptParent()==parent){
//                int size=maps.size();
//                Map<String,Object> map=Maps.newHashMap();
//                map.put("id","p" +orgDept.getOrgDeptId());
//                map.put("pId",parent!=0?"p"+parent:parent);
//                map.put("open",true);
//                mergeDept(orgDepts,maps,orgDept.getOrgDeptId());
//                map.put("isParent",maps.size()>size?true:false);
//                map.put("name",orgDept.getOrgDeptName());
//                maps.add(map);
//            }
//
//        }
//    }

    @ResponseBody
    @RequestMapping("/data")
    public List data() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<OrgDept> deptList = deptService.findDeptList(new OrgDept());
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("id", -1);
        map1.put("pId", 0);
        map1.put("name", "所有部门");

        list.add(map1);
        for (OrgDept d : deptList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", d.getOrgDeptId());
            map.put("pId", d.getOrgDeptParent());
            map.put("open", true);
            map.put("add", true);
            map.put("edit", true);
            map.put("name", d.getOrgDeptName());
            list.add(map);
        }
        return list;
    }
}
