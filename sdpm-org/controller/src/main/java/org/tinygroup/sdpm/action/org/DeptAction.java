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
@RequestMapping("/a/org/dept")
public class DeptAction extends BaseController {
    @Autowired
    private DeptService deptService;

    /**
     * 部门的增加功能
     *
     * @param orgDeptParent
     * @param orgDeptName
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public Map addDept(Integer orgDeptParent, String orgDeptName) {
        OrgDept dept = new OrgDept();
        dept.setOrgDeptParent(orgDeptParent);
        dept.setOrgDeptName(orgDeptName);
        deptService.addDept(dept);
        return resultMap(true, "增加成功");
    }

    /**
     * 部门的编辑功能
     *
     * @param orgDeptParent
     * @param orgDeptId
     * @param orgDeptName
     * @return
     */
    @ResponseBody
    @RequestMapping("edit")
    public Map editDept(Integer orgDeptParent, Integer orgDeptId, String orgDeptName) {
        OrgDept dept = deptService.findDept(orgDeptId);
        dept.setOrgDeptName(orgDeptName);
        dept.setOrgDeptParent(orgDeptParent);
        deptService.updateDept(dept);
        return resultMap(true, "修改成功");
    }

    /**
     * 部门的删除功能
     *
     * @param orgDeptId
     * @return
     */
    @ResponseBody
    @RequestMapping("delete")
    public Map deleteDept(Integer orgDeptId) {
        deptService.deleteDept(orgDeptId);
        return resultMap(true, "删除成功");
    }

    /**
     * 部门树的数据拼接
     */
    @ResponseBody
    @RequestMapping("/data")
    public List data() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<OrgDept> deptList = deptService.findDeptList(new OrgDept());
        for (OrgDept d : deptList) {
            if (!d.getOrgDeptParent().equals(-2)) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", d.getOrgDeptId());
                map.put("pId", d.getOrgDeptParent());
                map.put("open", true);
                map.put("add", true);
                map.put("edit", true);
                map.put("name", d.getOrgDeptName());
                list.add(map);
            }
        }
        return list;
    }
}
