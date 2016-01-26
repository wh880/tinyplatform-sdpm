package org.tinygroup.sdpm.action.org;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiary;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiaryAndUserDO;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiaryDetail;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.DiaryService;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.system.dao.pojo.SystemEffort;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.service.inter.EffortService;
import org.tinygroup.sdpm.util.UserUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by wangdl16860 on 2016/1/8.
 */
@Controller
@RequestMapping("/a/org/diary")
public class DiaryAction extends BaseController {
    @Autowired
    private DiaryService diaryService;

    @Autowired
    private EffortService effortService;

    @Autowired
    private UserService userService;


    /**
     * 添加周报以及相应的周报详情
     *
     * @param summary
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public Map add(String summary, Integer y, Integer w, String efforts) {
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
        if (y == null || w == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            y = calendar.get(Calendar.YEAR);
            w = calendar.get(Calendar.WEEK_OF_YEAR);
        }
        //查找这一周的周报
        OrgDiary diary = diaryService.findDiaryByUserLatest(userId, y, w);
        List<OrgDiaryDetail> list = new ArrayList<OrgDiaryDetail>();
        //查找相关日志ID对应的日志
        List<SystemEffort> effortList = effortService.findEffortListByIdList(idsList);
        //如果这一周已经提交了周报，插入的详情表写入周报ID
        if (diary != null) {
            //TODO:改成foreach
            for (int j = 0; j < effortList.size(); j++) {
                OrgDiaryDetail orgDiaryDetail = new OrgDiaryDetail();
                orgDiaryDetail.setOrgDetailContent(effortList.get(j).getEffortWork());
                orgDiaryDetail.setOrgDetailDate(effortList.get(j).getEffortDate());
                orgDiaryDetail.setOrgUserId(userUtils.getUser().getOrgUserId());
                orgDiaryDetail.setOrgDiaryId(diary.getOrgDiaryId());
                list.add(orgDiaryDetail);
            }
        }
        //如果这一周没有提交过周报，则进行添加操作，并且对详情不set日志ID
        else {
            //TODO:改成foreach

            for (int j = 0; j < effortList.size(); j++) {
                OrgDiaryDetail orgDiaryDetail = new OrgDiaryDetail();
                orgDiaryDetail.setOrgDetailContent(effortList.get(j).getEffortWork());
                orgDiaryDetail.setOrgDetailDate(effortList.get(j).getEffortDate());
                orgDiaryDetail.setOrgUserId(userUtils.getUser().getOrgUserId());
                list.add(orgDiaryDetail);
            }
            orgDiary.setOrgDiaryCreateDate(date);
            orgDiary.setOrgDiarySummary(summary);
            orgDiary.setOrgUserId(userId);
            orgDiary.setOrgDiaryWeek(w);
            orgDiary.setOrgDiaryYear(y);
            Date beginDate = getBeginDate(y, w);
            Date endDate = getEndDate(y, w);
            orgDiary.setOrgDiaryBeginDate(beginDate);
            orgDiary.setOrgDiaryEndDate(endDate);
            //进行添加周报以及添加周报详情的操作
            diaryService.addDiary(orgDiary, list);
            return resultMap(true, "添加成功");
        }
        //进行修改操作
        diary.setOrgDiarySummary(summary);
        diary.setOrgDiaryModifyDate(date);
        diaryService.updateDiary(diary, list);
        return resultMap(true, "修改成功");
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
    @ResponseBody
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

    /**
     * 删除某一周报
     *
     * @param orgDiaryId
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public Map delete(Integer orgDiaryId) {
        if (diaryService.deleteDiary(orgDiaryId) > 0) {
            return resultMap(false, "删除成功");
        } else {
            return resultMap(false, "删除失败");
        }
    }

    /**
     * 显示某一用户所有的周报
     *
     * @param userAccount
     * @param model
     * @return
     */
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

    /**
     * 查看某人确定某一周的周报
     *
     * @param userId
     * @param year
     * @param week
     * @param model
     * @return
     */
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

    /**
     * 显示当前用户的当前周的周报
     *
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
        return "organization/diary/diary.page";
    }


    /**
     * 增加或编辑时候显示页面
     *
     * @param w
     * @param model
     * @return
     */
    @RequiresPermissions(value = {"org-diary-edit"}, logical = Logical.OR)
    @RequestMapping("/form")
    public String form(Model model, Integer w, Integer y) {
        Calendar calendar = Calendar.getInstance();
        if (y == null || w == null) {
            Date date = new Date();
            calendar.setTime(date);
            y = calendar.get(Calendar.YEAR);
            w = calendar.get(Calendar.WEEK_OF_YEAR);
        }
        String userId = userUtils.getUser().getOrgUserId();
        OrgDiary orgDiary = diaryService.findDiaryByUserLatest(userId, y, w);
        model.addAttribute("orgDiary", orgDiary);
        if (orgDiary != null) {
            List<OrgDiaryDetail> list = diaryService.findDetailListByDiaryId(orgDiary.getOrgDiaryId());
            Date beginDate = getBeginDate(y, w);
            Date endDate = getEndDate(y, w);
            List<SystemEffort> effortsList = effortService.findEffortListByUserAndDate(userUtils.getUser().getOrgUserId(), beginDate, endDate);
            model.addAttribute("list", list);
            model.addAttribute("efforts", effortsList);
        }

        return "organization/diary/submit";
    }

    @ResponseBody
    @RequestMapping("tree")
    public List<Map<String, Object>> ajax(SystemModule systemModule, HttpServletResponse response, @RequestParam(value = "type", defaultValue = "name") String type) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<OrgUser> usersList = userService.findAllSubordinate(UserUtils.getUserId());
        usersList.add(userUtils.getUser());
        for (OrgUser user : usersList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", user.getOrgUserId());
            map.put("pId", user.getOrgUserLeader());
            map.put("open", true);
            map.put("add", false);
            map.put("edit", false);
            map.put("name", user.getOrgUserAccount());
            list.add(map);
        }
        return list;
    }

    @RequiresPermissions("organizationDiary")
    @RequestMapping("/list/one")
    public String listOne(Integer y, Integer w, Model model) {
        if (y == null || w == null) {
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            y = calendar.get(Calendar.YEAR);
            w = calendar.get(Calendar.WEEK_OF_YEAR) - 1;
        }
        OrgDiary orgDiary = diaryService.findDiaryByUserLatest(UserUtils.getUserId(), y, w);
        model.addAttribute("orgDiary", orgDiary);
        Date bDate = getBeginDate(y, w);
        Date eDate = getEndDate(y, w);
        String userAccount = userUtils.getUser().getOrgUserAccount();
        List<SystemEffort> list = effortService.findEffortListByUserAndDate(userAccount, bDate, eDate);
        List<OrgDiaryDetail> list2 = null;//TODO:改参数名
        if (orgDiary != null) {
            list2 = diaryService.findDetailListByDiaryId(orgDiary.getOrgDiaryId());
        }
        List<SystemEffort> list1 = new ArrayList<SystemEffort>();
        for (SystemEffort effort : list) {
            Date effortDate = effort.getEffortDate();
            effort.setEffortWeek(effortDate.getDay());
            list1.add(effort);
        }
        model.addAttribute("year", y);
        model.addAttribute("week", w);
        model.addAttribute("list", list1);
        model.addAttribute("details", list2);
        return "organization/diary/oneDiary.pagelet";
    }

    @RequiresPermissions("organizationDiary")
    @RequestMapping("/list/data")
    public String listData(String orgUserId, Integer y, Integer w,
                           Model model) {
        List<OrgDiaryAndUserDO> list;
        if (y == null || w == null) {
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            y = calendar.get(Calendar.YEAR);
            w = calendar.get(Calendar.WEEK_OF_YEAR) - 1;
        }
        if (StringUtil.isBlank(orgUserId)) {
            orgUserId = UserUtils.getUserId();
        }
        list = diaryService.findListDiarySubAndSelf(orgUserId, y, w);

        model.addAttribute("list", list);
        Map<Integer, List<OrgDiaryDetail>> map = new HashMap<Integer, List<OrgDiaryDetail>>();
        for (OrgDiaryAndUserDO orgDiaryAndYUser : list) {
            List<OrgDiaryDetail> efforts = null;
            if (map.get(orgDiaryAndYUser.getOrgDiaryId()) == null) {
                map.put(orgDiaryAndYUser.getOrgDiaryId(), efforts);
            }
            efforts = diaryService.findDetailListByDiaryList(list);
            //TODO:修改获取方式
            for (OrgDiaryDetail orgDiaryDetail : efforts) {
                int week = orgDiaryDetail.getOrgDetailDate().getDay();
                if (week == 0) {
                    orgDiaryDetail.setEffortWeek(7);
                } else {
                    orgDiaryDetail.setEffortWeek(week);
                }
            }

            map.put(orgDiaryAndYUser.getOrgDiaryId(), efforts);

        }
        model.addAttribute("efforts", map);
        return "organization/diary/diaryData.pagelet";
    }

    //TODO:切换到DateUtils
    private Date getBeginDate(Integer y, Integer w) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(y, 0, 1, 0, 0, 0);
        int week = calendar.get(calendar.DAY_OF_WEEK) - 1;
        int days = w * 7 - (8 - week);
        calendar.add(Calendar.DATE, days - 1);
        Date beginDate = calendar.getTime();
        return beginDate;
    }

    private Date getEndDate(Integer y, Integer w) {
        Date beginDate = getBeginDate(y, w);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginDate);
        calendar.add(Calendar.DATE, 6);
        Date endDate = calendar.getTime();
        return endDate;
    }

    @RequiresPermissions("organizationDiary")
    @RequestMapping("/showself")
    public String showSelf(String userAccount, Model model) {
        OrgUser user = userService.findUserByAccount(userAccount);
        List<OrgDiaryAndUserDO> list = diaryService.findListDiaryByUserId(user.getOrgUserId());
        Collections.sort(list);
        model.addAttribute("list", list);
        Map<Integer, List<OrgDiaryDetail>> map = new HashMap<Integer, List<OrgDiaryDetail>>();
        for (OrgDiaryAndUserDO orgDiaryAndYUser : list) {
            Integer diaryId = orgDiaryAndYUser.getOrgDiaryId();
            List<OrgDiaryDetail> orgDiaryDetails = null;
            if (map.get(diaryId) == null) {
                map.put(diaryId, orgDiaryDetails);
            }
            orgDiaryDetails = diaryService.findDetailListByDiaryId(diaryId);
            //TODO:同理
            for (int i = 0; i < orgDiaryDetails.size(); i++) {
                OrgDiaryDetail orgDetail = orgDiaryDetails.get(i);
                int week = orgDetail.getOrgDetailDate().getDay();
                if (week == 0) {
                    orgDetail.setEffortWeek(7);
                } else {
                    orgDetail.setEffortWeek(week);
                }
            }
            map.put(diaryId, orgDiaryDetails);
        }
        model.addAttribute("userAccount", userAccount);
        model.addAttribute("details", map);
        return "organization/diary/oneselfDiary";
    }
}
