package org.tinygroup.sdpm.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.tinygroup.application.Application;
import org.tinygroup.application.ApplicationProcessor;
import org.tinygroup.sdpm.common.menu.Menu;
import org.tinygroup.sdpm.common.menu.MenuManager;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDocLib;
import org.tinygroup.sdpm.document.service.inter.DocService;
import org.tinygroup.sdpm.org.dao.pojo.*;
import org.tinygroup.sdpm.org.service.inter.CompanyService;
import org.tinygroup.sdpm.org.service.inter.DeptService;
import org.tinygroup.sdpm.org.service.inter.RoleService;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.system.dao.pojo.SystemConfig;
import org.tinygroup.sdpm.system.service.inter.ConfigService;
import org.tinygroup.xmlparser.node.XmlNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hulk on 2015/12/7.
 */
public class InitDataApplicationProcessor implements ApplicationProcessor {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private DocService docService;
    @Autowired
    private MenuManager menuManager;
    @Autowired
    private ConfigService configService;

    private void initRole() {
        List<OrgRole> roles = roleService.findRoleList(null);
        if (roles.isEmpty()) {
            OrgRole orgRole = new OrgRole();
            orgRole.setOrgRoleName("管理员");
            roleService.addRole(orgRole);
            List<Menu> allChildMenus = menuManager.getAllChildMenus("0");
            List<OrgRoleMenu> orgRoleMenuList = new ArrayList<OrgRoleMenu>();
            for (Menu menu : allChildMenus) {
                OrgRoleMenu orgRoleMenu = new OrgRoleMenu();
                orgRoleMenu.setOrgRoleId(1);
                orgRoleMenu.setOrgRoleMenuId(menu.getId());
                orgRoleMenuList.add(orgRoleMenu);
            }
            roleService.batchAddRoleMenu(orgRoleMenuList);

        }
    }

    private void initDept() {
        List<OrgDept> deptList = deptService.findDeptList(null);
        if (deptList.isEmpty()) {
            OrgDept orgDept = new OrgDept();
            orgDept.setOrgDeptId(1);
            orgDept.setOrgDeptParent(0);
            orgDept.setOrgDeptName("TinyGroup");
            deptService.addDept(orgDept);
        }
    }


    private void initUser() {
        List<OrgUser> userList = userService.findUserList(null);
        if (userList.isEmpty()) {
            OrgUser orgUser = new OrgUser();
            orgUser.setOrgDeptId(1);
            orgUser.setOrgUserAccount("admin");
            orgUser.setOrgUserRealName("管理员");
            orgUser.setOrgUserPassword("123");
            OrgUser orgUser1 = userService.addUser(orgUser);
            roleService.batchAddRolesToUser(orgUser1.getOrgUserId(), new String[]{"1"});
        }
    }

    private void initDoc() {
        List<DocumentDocLib> docLibList = docService.findDocLibList(null);
        if (docLibList.isEmpty()) {
            DocumentDocLib documentDocLib = new DocumentDocLib();
            documentDocLib.setDocLibId(1);
            documentDocLib.setDocLibName("产品文档库");
            docService.createNewDocLib(documentDocLib);

            documentDocLib.setDocLibId(2);
            documentDocLib.setDocLibName("项目文档库");
            docService.createNewDocLib(documentDocLib);
        }
    }

    private void initCompany() {
        List<OrgCompany> companyList = companyService.findCompanyList();
        if (companyList.isEmpty()) {
            OrgCompany orgCompany = new OrgCompany();
            orgCompany.setOrgCompanyId(1);
            orgCompany.setOrgCompanyName("TinyGroup");
            orgCompany.setOrgCompanyAddress("tiny");
            companyService.addCompany(orgCompany);
        }
    }

    private void initConfig() {
        List<SystemConfig> configs = configService.findConfigList();
        if (configs.isEmpty()) {
            SystemConfig systemConfig = new SystemConfig();
            systemConfig.setConfigSection("searchConfig");
            systemConfig.setConfigKey("8");
            systemConfig.setConfigValue("异步下拉框显示个数配置");
            configService.addConfig(systemConfig);
        }
    }

    public void start() {
        initRole();
        initDept();
        initUser();
        initDoc();
        initCompany();
        initConfig();
    }

    public void init() {

    }

    public void stop() {

    }

    public void setApplication(Application application) {

    }

    public String getApplicationNodePath() {
        return null;
    }

    public String getComponentConfigPath() {
        return null;
    }

    public void config(XmlNode applicationConfig, XmlNode componentConfig) {

    }

    public XmlNode getComponentConfig() {
        return null;
    }

    public XmlNode getApplicationConfig() {
        return null;
    }

    public int getOrder() {
        return 0;
    }
}
