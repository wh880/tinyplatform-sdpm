package org.tinygroup.sdpm.action.org;

import org.apache.commons.io.FilenameUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.tinygroup.sdpm.action.system.FileRepository;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgCompany;
import org.tinygroup.sdpm.org.service.inter.CompanyService;
import org.tinygroup.sdpm.system.dao.pojo.ProfileType;
import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;
import org.tinygroup.sdpm.system.service.inter.ProfileService;
import org.tinygroup.sdpm.util.UserUtils;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/a/org/company")
public class CompanyAction extends BaseController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private ProfileService profileService;


    /**
     * 公司编辑后的保存
     *
     * @param company
     * @param model
     * @return
     */
    @RequiresPermissions("org-company-edit")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(OrgCompany company, Model model) {
        companyService.updateCompany(company);
        model.addAttribute("company", company);
        return "redirect:" + adminPath + "/org/company/show/";
    }

    @ResponseBody
    @RequestMapping(value = "/uploadLogo", method = RequestMethod.POST)
    public Map uploadLogo(OrgCompany company, @RequestParam(value = "upfile", required = false) MultipartFile upfile) throws IOException {
//        upload(upfile, company.getOrgCompanyId(), ProfileType.ORG, "");

        String origName = upfile.getOriginalFilename();
        String ext = FilenameUtils.getExtension(origName);
        String fileUrl = fileRepository.storeByExt(UPLOAD_PATH, origName, upfile);
        fileUrl = fileRepository.resolverFilePath(fileUrl, UPLOAD_PATH);
        long size = upfile.getSize();
        SystemProfile profile = new SystemProfile(fileUrl, null, ext, (int) size,
                ProfileType.ORG.getType(), company.getOrgCompanyId(), UserUtils.getUserAccount(), new Date(), null, null);
        profileService.add(profile);
        company.setOrgCompanyLogo(fileUrl);
        companyService.updateCompany(company);
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "success");
        return map;
    }


    /**
     * 显示公司页面
     *
     * @param model
     * @return
     */
    @RequiresPermissions("organizationCompany")
    @RequestMapping("/show")
    public String show(Model model) {
        OrgCompany company = companyService.findCompany(1);
        model.addAttribute("company", company);
        return "organization/company/company.page";
    }

    /**
     * 编辑时候查询公司详情
     *
     * @param model
     * @return
     */
    @RequiresPermissions("org-company-edit")
    @RequestMapping("/edit")
    public String edit(Model model) {
        OrgCompany company = companyService.findCompany(1);
        model.addAttribute("company", company);
        return "organization/company/updateCompany.pagelet";
    }

}
