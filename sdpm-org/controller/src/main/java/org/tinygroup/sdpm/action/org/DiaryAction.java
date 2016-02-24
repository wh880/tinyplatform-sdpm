package org.tinygroup.sdpm.action.org;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.util.DateUtils;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiary;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiaryAndUserDO;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiaryDetail;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.DiaryService;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.product.service.inter.StoryService;
import org.tinygroup.sdpm.project.service.inter.TaskService;
import org.tinygroup.sdpm.quality.service.inter.BugService;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.service.inter.ActionService;
import org.tinygroup.sdpm.util.UserUtils;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by wangdl16860 on 2016/1/8.
 * 操作周报模块的Action
 */
@Controller
@RequestMapping("/a/org/diary")
public class DiaryAction extends BaseController {
    @Autowired
    private DiaryService diaryService;
    @Autowired
    private UserService userService;
    @Autowired
    private ActionService actionService;
    @Autowired
    private BugService bugService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private StoryService storyService;

    /**
     * 添加周报以及相应的周报详情
     *
     * @param summary
     * @param year
     * @param week
     * @param efforts
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public Map add(String summary, @RequestParam(value = "y") Integer year, @RequestParam(value = "w") Integer week, String efforts) {
        String[] effortIds = null;
        //获得需要插入周报详情的日志ID
        List<Integer> idsList = new ArrayList<Integer>();
        if (!StringUtil.isBlank(efforts)) {
            effortIds = efforts.split(",");
            for (int i = 0; i < effortIds.length; i++) {
                idsList.add(Integer.parseInt(effortIds[i]));
            }
        }
        String userId = userUtils.getUser().getOrgUserId();
        OrgDiary orgDiary = new OrgDiary();
        Date date = new Date();
        if (year == null || week == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            year = calendar.get(Calendar.YEAR);
            week = calendar.get(Calendar.WEEK_OF_YEAR);
        }
        //查找这一周的周报
        OrgDiary diary = diaryService.findDiaryByUserLatest(userId, year, week);
        List<OrgDiaryDetail> list = new ArrayList<OrgDiaryDetail>();
        //查找相关日志ID对应的日志
        List<SystemAction> actionList = actionService.findActionListByIdList(idsList);
        if (!CollectionUtil.isEmpty(actionList)) {
            actionList = getDiffObjectName(actionList);
        }
        //如果这一周已经提交了周报，插入的详情表写入周报ID
        if (diary != null) {
            if (!CollectionUtil.isEmpty(actionList)) {
                for (SystemAction systemAction : actionList) {
                    OrgDiaryDetail orgDiaryDetail = new OrgDiaryDetail();
                    String content = userUtils.getUser().getOrgUserRealName();
                    String objectType = systemAction.getActionObjectType();
                    if ("finished".equals(systemAction.getActionAction())) {
                        content = content + "完成了" + objectType + systemAction.getObjectName();
                    } else {
                        content = content + "创建了" + objectType + systemAction.getObjectName();
                    }
                    orgDiaryDetail.setOrgDetailContent(content);
                    orgDiaryDetail.setOrgDetailDate(systemAction.getActionDate());
                    orgDiaryDetail.setActionId(systemAction.getActionId());
                    orgDiaryDetail.setOrgUserId(userUtils.getUser().getOrgUserId());
                    orgDiaryDetail.setOrgDiaryId(diary.getOrgDiaryId());
                    list.add(orgDiaryDetail);
                }
            }
            //进行修改操作
            diary.setOrgDiarySummary(summary);
            diary.setOrgDiaryModifyDate(date);
            diaryService.updateDiary(diary, list);
            return resultMap(true, "修改成功");
        }
        //如果这一周没有提交过周报，则进行添加操作，并且对详情不set日志ID
        else {
            if (!CollectionUtil.isEmpty(actionList)) {
                actionList = getDiffObjectName(actionList);
                for (SystemAction systemAction : actionList) {
                    OrgDiaryDetail orgDiaryDetail = new OrgDiaryDetail();
                    String content = userUtils.getUser().getOrgUserRealName();
                    String objectType = systemAction.getActionObjectType();
                    String title = systemAction.getObjectName();
                    if ("finished".equals(systemAction.getActionAction())) {
                        content = content + "完成了" + objectType + title;
                    } else {
                        content = content + "创建了" + objectType + title;
                    }
                    orgDiaryDetail.setOrgDetailContent(content);
                    orgDiaryDetail.setOrgDetailDate(systemAction.getActionDate());
                    orgDiaryDetail.setActionId(systemAction.getActionId());
                    orgDiaryDetail.setOrgUserId(userUtils.getUser().getOrgUserId());
                    list.add(orgDiaryDetail);
                }
            }
            orgDiary.setOrgDiaryCreateDate(date);
            orgDiary.setOrgDiarySummary(summary);
            orgDiary.setOrgUserId(userId);
            orgDiary.setOrgDiaryWeek(week);
            orgDiary.setOrgDiaryYear(year);
            Date beginDate = DateUtils.getBeginDate(year, week);
            Date endDate = DateUtils.getEndDate(year, week);
            orgDiary.setOrgDiaryBeginDate(beginDate);
            orgDiary.setOrgDiaryEndDate(endDate);
            //进行添加周报以及添加周报详情的操作
            diaryService.addDiary(orgDiary, list);
            return resultMap(true, "添加成功");
        }
    }

    /**
     * 获得某一条系统日志中对象的名字(周报所需的BUG TASK STORY相关的系统日志)
     *
     * @param actionList
     * @return
     */
    private List<SystemAction> getDiffObjectName(List<SystemAction> actionList) {
        List<SystemAction> bugList = new ArrayList<SystemAction>();
        List<SystemAction> storyList = new ArrayList<SystemAction>();
        List<SystemAction> taskList = new ArrayList<SystemAction>();
        for (SystemAction systemAction : actionList) {
            if (systemAction.getActionObjectType().equals("bug")) {
                bugList.add(systemAction);
            } else if (systemAction.getActionObjectType().equals("task")) {
                taskList.add(systemAction);
            } else {
                storyList.add(systemAction);
            }
        }
        return actionService.findActionListByTypeList(bugList, storyList, taskList);
    }
    /**
     * 编辑修改周报
     *
     * @param summary
     * @param json
     * @param userId
     * @param diaryId
     * @return
     */
   /* @ResponseBody
    @RequestMapping("/edit")
    public Map edit(String summary, String json, String userId, Integer diaryId) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<OrgDiaryDetail> list = null;
        try {
            JsonParser parser = objectMapper.getJsonFactory().createJsonParser(json);
            JsonNode nodes = parser.readValueAsTree();
            list = new ArrayList<OrgDiaryDetail>();
            for (JsonNode node : nodes) {
                list.add(objectMapper.readValue(node, OrgDiaryDetail.class));
            }
        } catch (IOException e) {
            e.getStackTrace();
            return resultMap(false, "修改失败");
        }
        OrgDiary orgDiary = new OrgDiary();
        orgDiary.setOrgDiarySummary(summary);
        orgDiary.setOrgUserId(userId);
        orgDiary.setOrgDiaryId(diaryId);

        diaryService.updateDiary(orgDiary, list);
        return resultMap(false, "修改成功");
    }
*/
    /**
     * 删除某一周报
     *
     * @param orgDiaryId
     * @return
     */
    /*
    @ResponseBody
    @RequestMapping("/delete")
    public Map delete(Integer orgDiaryId) {
        if (diaryService.deleteDiary(orgDiaryId) > 0) {
            return resultMap(false, "删除成功");
        } else {
            return resultMap(false, "删除失败");
        }
    }
*/
    /**
     * 显示某一用户所有的周报
     *
     * @param userAccount
     * @param model
     * @return
     */
    /*
    @RequiresPermissions("organizationDiary")
    @RequestMapping("/findListByUser")
    public String findListByUser(String userAccount, Model model) {
        OrgUser user = null;
        List<OrgDiaryAndUserDO> list;
        String userId = userService.findUserByAccount(userAccount).getOrgUserId();
        if (StringUtil.isEmpty(userAccount)) {
            user = userUtils.getUser();
            userId = user.getOrgUserId();
        }
        user = userUtils.getUserById(userId);
        list = diaryService.findListDiaryByUserId(userId);
        model.addAttribute("list", list);
        model.addAttribute("orgUser", user);
        return "organization/diary/diaryData.pagelet";
    }
*/
    /**
     * 查看某人确定某一周的周报
     *
     * @param userId
     * @param year
     * @param week
     * @param model
     * @return
     */
    /*
    @RequestMapping("/find")
    public String find(String userId, Integer year, Integer week, Model model) {
        OrgUser user = null;
        if (StringUtil.isBlank(userId) || StringUtil.isEmpty(userId)) {
            return null;
        }
        OrgDiary orgDiary = diaryService.findDiaryByUserAndDate(year, week, userId);
        user = userUtils.getUserById(userId);
        model.addAttribute("orgDiary", orgDiary);
        model.addAttribute("orgUser", user);
        return "organization/diary/oneselfDiary";
    }
*/

    /**
     * 显示当前用户的当前周的周报
     *
     * @param orgUserId
     * @param model
     * @return
     */
    @RequiresPermissions("organizationDiary")
    @RequestMapping("/show")
    public String show(String orgUserId, Model model) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        Integer year = ca.get(Calendar.YEAR);
        Integer week = ca.get(Calendar.WEEK_OF_YEAR) - 1;
        OrgUser user = null;
        if (orgUserId == null) {
            user = userUtils.getUser();
        } else {
            user = userUtils.getUserById(orgUserId);
        }
        OrgDiary orgDiary = diaryService.findDiaryByUserLatest(user.getOrgUserId(), year, week);//自己
        List<OrgDiary> list = diaryService.findDiaryListSubordinateOneWeek(user.getOrgUserId(), year, week);//下属
        model.addAttribute("orgDiary", orgDiary);
        model.addAttribute("list", list);
        model.addAttribute("orgUserId", orgUserId);
        model.addAttribute("realName", user.getOrgUserRealName());
        return "organization/diary/diary.page";
    }


    /**
     * 增加或编辑时候显示页面
     *
     * @param week
     * @param model
     * @return
     */
    @RequiresPermissions(value = {"org-diary-edit"}, logical = Logical.OR)
    @RequestMapping("/form")
    public String form(Model model, Integer week, Integer year) {
        Calendar calendar = Calendar.getInstance();
        if (year == null || week == null) {
            Date date = new Date();
            calendar.setTime(date);
            year = calendar.get(Calendar.YEAR);
            week = calendar.get(Calendar.WEEK_OF_YEAR);
        }
        String userId = userUtils.getUser().getOrgUserId();
        OrgDiary orgDiary = diaryService.findDiaryByUserLatest(userId, year, week);
        model.addAttribute("orgDiary", orgDiary);
        if (orgDiary != null) {
            List<OrgDiaryDetail> list = diaryService.findDetailListByDiaryId(orgDiary.getOrgDiaryId());
            Date beginDate = DateUtils.getBeginDate(year, week);
            Date endDate = DateUtils.getEndDate(year, week);
            //List<SystemEffort> effortsList = effortService.findEffortListByUserAndDate(userUtils.getUser().getOrgUserId(), beginDate, endDate);
            List<SystemAction> actionList = actionService.findDiaryActionListByUserAndDate(userId, beginDate, endDate);
            model.addAttribute("list", list);
            model.addAttribute("efforts", actionList);
        }

        return "organization/diary/submit";
    }

    /**
     * diary页面显示左侧的用户树
     *
     * @param systemModule
     * @param response
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping("tree")
    public List<Map<String, Object>> ajax(SystemModule systemModule, HttpServletResponse response, @RequestParam(value = "type", defaultValue = "name") String type, Integer choose) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<OrgUser> usersList = null;
        OrgUser orgUser = userUtils.getUser();
        if (choose == null) {
            usersList = userService.findAllSubordinate(UserUtils.getUserId());//直接下属树
            usersList.add(orgUser);
            for (OrgUser user : usersList) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", user.getOrgUserId());
                map.put("open", true);
                map.put("add", false);
                map.put("pId", user.getOrgUserLeader());
                map.put("edit", false);
                map.put("name", user.getOrgUserRealName());
                list.add(map);
            }
        } else {
            usersList = userService.findWhiteUser(UserUtils.getUserAccount());//白名单树
            usersList.add(orgUser);
            for (OrgUser user : usersList) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", user.getOrgUserId());
                map.put("open", true);
                map.put("add", false);
                if (!user.getOrgUserId().equals(orgUser.getOrgUserId())) {
                    map.put("pId", orgUser.getOrgUserId());
                } else {
                    map.put("pId", null);
                }
                map.put("edit", true);
                map.put("name", user.getOrgUserRealName());
                list.add(map);
            }
        }
        return list;
    }

    /**
     * 获取某人某一周的周报相关数据
     *
     * @param year
     * @param week
     * @param model
     * @return
     */
    @RequiresPermissions("organizationDiary")
    @RequestMapping("/list/one")
    public String listOne(@RequestParam(value = "y") Integer year, @RequestParam(value = "w") Integer week, Model model) {
        if (year == null || week == null) {
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            year = calendar.get(Calendar.YEAR);
            week = calendar.get(Calendar.WEEK_OF_YEAR) - 1;
        }
        OrgDiary orgDiary = diaryService.findDiaryByUserLatest(UserUtils.getUserId(), year, week);
        model.addAttribute("orgDiary", orgDiary);
        Date bDate = DateUtils.getBeginDate(year, week);
        Date eDate = DateUtils.getEndDate(year, week);
        OrgUser user = userUtils.getUser();
        List<SystemAction> actionList = actionService.findDiaryActionListByUserAndDate(user.getOrgUserId(), bDate, eDate);
        List<OrgDiaryDetail> orgDiaryDetailList = null;
        if (orgDiary != null) {
            orgDiaryDetailList = diaryService.findDetailListByDiaryId(orgDiary.getOrgDiaryId());
        }
        List<SystemAction> systemActions = new ArrayList<SystemAction>();
        for (SystemAction systemAction : actionList) {
            systemAction.setActionWeekDay(DateUtils.getDateWeek(systemAction.getActionDate()));
            systemActions.add(systemAction);
        }
        model.addAttribute("year", year);
        model.addAttribute("week", week);
        model.addAttribute("list", systemActions);
        model.addAttribute("details", orgDiaryDetailList);
        return "organization/diary/oneDiary.pagelet";
    }

    /**
     * 获取List的周报信息（某一周所有人的周报或者某一个人的所有周报）
     *
     * @param orgUserId
     * @param year
     * @param week
     * @param model
     * @return
     */
    @RequiresPermissions("organizationDiary")
    @RequestMapping("/list/data")
    public String listData(String orgUserId, @RequestParam(value = "y") Integer year, @RequestParam(value = "w") Integer week,
                           Model model, Integer type) {
        List<OrgDiaryAndUserDO> list;
        if (year == null || week == null) {
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            year = calendar.get(Calendar.YEAR);
            week = calendar.get(Calendar.WEEK_OF_YEAR) - 1;
        }
        if (StringUtil.isBlank(orgUserId)) {
            orgUserId = UserUtils.getUserId();
        }
        if (type == null) {
            list = diaryService.findListDiarySubAndSelf(orgUserId, year, week);
        } else {//如果是查询白名单用户周报，传一个type值
            String userAccount = userUtils.getUserById(orgUserId).getOrgUserAccount();
            list = diaryService.findDiaryListByWhiteList(userAccount, year, week);
        }
        if (!CollectionUtil.isEmpty(list)) {
            for (OrgDiaryAndUserDO orgDiaryAndUserDO : list) {
                orgDiaryAndUserDO.setDiaryDateTime(DateUtils.formatDate(orgDiaryAndUserDO.getOrgDiaryCreateDate()));
            }

            Map<Integer, List<OrgDiaryDetail>> map = new HashMap<Integer, List<OrgDiaryDetail>>();
            for (OrgDiaryAndUserDO orgDiaryAndYUser : list) {
                List<OrgDiaryDetail> efforts = null;
                if (map.get(orgDiaryAndYUser.getOrgDiaryId()) == null) {
                    map.put(orgDiaryAndYUser.getOrgDiaryId(), efforts);
                }
                efforts = diaryService.findDetailListByDiaryId(orgDiaryAndYUser.getOrgDiaryId());
                for (OrgDiaryDetail orgDiaryDetail : efforts) {
                    orgDiaryDetail.setEffortWeek(DateUtils.getDateWeek(orgDiaryDetail.getOrgDetailDate()));
                }
                map.put(orgDiaryAndYUser.getOrgDiaryId(), efforts);
            }
            model.addAttribute("efforts", map);
        }
        model.addAttribute("list", list);
        return "organization/diary/diaryData.pagelet";
    }

    /**
     * 显示某一个人的周报页面
     *
     * @param userAccount
     * @param model
     * @return
     */
    @RequiresPermissions("organizationDiary")
    @RequestMapping("/showOne")
    public String showSelf(String userAccount, Model model, String orgUserId) {
        OrgUser user = null;
        if (StringUtil.isBlank(orgUserId)&!StringUtil.isBlank(userAccount)) {
            user = userService.findUserByAccount(userAccount);
        } else if (StringUtil.isBlank(userAccount)&!StringUtil.isBlank(orgUserId)) {
            user = userUtils.getUserById(orgUserId);
        } else if (StringUtil.isBlank(orgUserId) & StringUtil.isBlank(userAccount)) {
            user = userUtils.getUser();
        }
        String realName = user.getOrgUserRealName();
        model.addAttribute("userAccount", realName);
        model.addAttribute("user", user);
        if (StringUtil.isBlank(orgUserId))
            return "organization/diary/oneselfDiary";
        return "organization/diary/diaryWhiteShowOne";
    }

    @RequiresPermissions("organizationDiary")
    @RequestMapping("/list/oneself")
    public String listOneself(String userAccount, String orgUserId, Model model, Integer start, Integer limit) {
        OrgUser user;
        if (StringUtil.isBlank(orgUserId)) {
            user = userService.findUserByAccount(userAccount);
        } else {
            user = userUtils.getUserById(orgUserId);
        }
        //List<OrgDiaryAndUserDO> list = diaryService.findListDiaryByUserId(user.getOrgUserId());
        Pager<OrgDiaryAndUserDO> pager = diaryService.findPagerDiaryByUserId(user.getOrgUserId(), start, limit);
        Map<Integer, List<OrgDiaryDetail>> map = new HashMap<Integer, List<OrgDiaryDetail>>();
        for (OrgDiaryAndUserDO orgDiaryAndYUser : pager.getRecords()) {
            orgDiaryAndYUser.setDiaryDateTime(DateUtils.formatDate(orgDiaryAndYUser.getOrgDiaryCreateDate()));
            Integer diaryId = orgDiaryAndYUser.getOrgDiaryId();
            List<OrgDiaryDetail> orgDiaryDetails = null;
            if (map.get(diaryId) == null) {
                map.put(diaryId, orgDiaryDetails);
            }
            orgDiaryDetails = diaryService.findDetailListByDiaryId(diaryId);
            for (int i = 0; i < orgDiaryDetails.size(); i++) {
                OrgDiaryDetail orgDetail = orgDiaryDetails.get(i);
                orgDetail.setEffortWeek(DateUtils.getDateWeek(orgDetail.getOrgDetailDate()));
            }
            map.put(diaryId, orgDiaryDetails);
        }
        String realName = user.getOrgUserRealName();
        // Collections.sort(pager.getRecords());
        //model.addAttribute("list", list);
        model.addAttribute("pager", pager);
        model.addAttribute("userAccount", realName);
        model.addAttribute("details", map);
        return "organization/diary/showPersonDiary.pagelet";
    }

    @RequiresPermissions("organizationDiary")
    @RequestMapping("/show/white")
    public String showWhite(String orgUserId, Model model) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        Integer year = ca.get(Calendar.YEAR);
        Integer week = ca.get(Calendar.WEEK_OF_YEAR) - 1;
        OrgUser user = null;
        if (orgUserId == null) {
            user = userUtils.getUser();
        } else {
            user = userUtils.getUserById(orgUserId);
        }
        OrgDiary orgDiary = diaryService.findDiaryByUserLatest(user.getOrgUserId(), year, week);//自己
        List<OrgDiary> list = diaryService.findDiaryListSubordinateOneWeek(user.getOrgUserId(), year, week);//下属
        model.addAttribute("orgDiary", orgDiary);
        model.addAttribute("list", list);
        model.addAttribute("orgUserId", orgUserId);
        model.addAttribute("realName", user.getOrgUserRealName());
        return "organization/diary/diaryWhiteShow.page";
    }
}
