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
import org.tinygroup.weblayer.WebContext;

import javax.servlet.http.HttpServletResponse;
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
    public void uploadLogo(OrgCompany company,
                           @RequestParam(value = "upfile", required = false) MultipartFile upFile,
                           HttpServletResponse response, WebContext webContext) throws IOException {
        String origName = upFile.getOriginalFilename();
        String ext = FilenameUtils.getExtension(origName);
        String fileUrl = fileRepository.storeByExt(UPLOAD_PATH, origName, upFile);
        fileUrl = fileRepository.resolverFilePath(fileUrl, UPLOAD_PATH);
        long size = upFile.getSize();
        SystemProfile profile = new SystemProfile(fileUrl, null, ext, (int) size,
                ProfileType.ORG.getType(), company.getOrgCompanyId(), UserUtils.getUserAccount(), new Date(), null, null);
        profileService.add(profile);
//        company.setOrgCompanyLogo(fileUrl);
//        companyService.updateCompany(company);
//        model.addAttribute("company", company);
        Map<String, String> map = new HashMap<String, String>();
        map.put("url", fileUrl);
        map.put("state", "SUCCESS");
//        response.setContentType("text/html;charset=UTF-8");
//        webContext.getResponse().setContentType("text/html");
//        ObjectToJson objectToJson = new ObjectToJson();
//        return objectToJson.convert(map);


        StringBuffer buffer = new StringBuffer();
        buffer.append("{'url':")
                .append("'")
                .append(fileUrl).append("'").append(",")
                .append("'state':").append("'SUCCESS'").append("}");

        webContext.getResponse().setContentType("text/html");
        webContext.getResponse().getWriter().write(buffer.toString());
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
