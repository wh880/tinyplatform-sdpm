package org.tinygroup.sdpm.action.quality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.quality.dao.pojo.QualityCaseStep;
import org.tinygroup.sdpm.quality.service.inter.CaseStepService;

/**
 * Created by chenpeng15668 on 2015-9-24
 */

@Controller
@RequestMapping("/a/quality/caseStep")
public class CaseStepAction extends BaseController {

    @Autowired
    private CaseStepService caseStepService;

    @RequestMapping("findList")
    public String findList(Integer id, Model model) {
        QualityCaseStep casestep = new QualityCaseStep();
        caseStepService.findCaseStepList(casestep);
        model.addAttribute("caseStepList", casestep);
        return "";
    }

    @RequestMapping("delete")
    public String delete(Integer id, Model model) {
        caseStepService.deleteCaseStepById(id);
        return "";
    }
}
