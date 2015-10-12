package org.tinygroup.sdpm.action.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.service.service.inter.SlaService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/service/dept")
public class SlaTreeAction extends BaseController {
    @Autowired
    private SlaService slaService;

    @ResponseBody
    @RequestMapping("/data")
    public List data(String check) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        if (check == null || !check.equals("n")) {
            Map<String, Object> mapfaq = new HashMap<String, Object>();
            /*不同之处：调用各自的方法，获取产品线名称，ID，以及产品的名称和ID*/
           /* List<OrgDept> deptList = deptService.findDeptList(new OrgDept());*/
            mapfaq.put("id", 0);
            mapfaq.put("pId", 1);
            mapfaq.put("name", "所有产品");
            list.add(mapfaq);
        }
        /*for (OrgDept d : deptList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", d.getOrgDeptId());
            map.put("pId", d.getOrgDeptParent());
            map.put("open", true);
            map.put("add", true);
            map.put("edit", true);
            map.put("name", d.getOrgDeptName());
            list.add(map);
        }*/
        return list;

    }


}