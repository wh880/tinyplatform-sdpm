package org.tinygroup.sdpm.action.org;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiary;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiaryDetail;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.DiaryService;
import org.tinygroup.sdpm.system.dao.pojo.SystemEffort;
import org.tinygroup.sdpm.system.service.inter.EffortService;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    /**
     * 添加周报以及相应的周报详情
     *
     * @param summary
     * @param userId
     * @param json
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    // json {orgDiaryDetails:{{org_diary_id:"1",org_user_id:12,...},{org_diary_id:"2",org_user_id:14,...}}}
    public Map add(String summary, String userId, String json) {
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
            return resultMap(false, "添加失败");
        }

        OrgDiary orgDiary = new OrgDiary();
        Date date = new Date();
        orgDiary.setOrgDiaryCreateDate(date);
        orgDiary.setOrgDiarySummary(summary);
        orgDiary.setOrgUserId(userId);

        diaryService.addDiary(orgDiary, list);
        return resultMap(true, "添加成功");
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
     * 查看直接下级的所有的周报
     *
     * @param userId
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/findSubordinate")
    public String findSubordinate(String userId, Model model) {
        if (StringUtil.isBlank(userId)) {
            return null;
        }
        List<OrgDiary> list = diaryService.findDiaryListByUserId(userId);
        model.addAttribute("list", list);
        return "";
    }

    /**
     * 根据周报ID查询对应的详情ID
     *
     * @param id
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/findByDiaryId")
    public String findByDiaryId(Integer id, Model model) {
        if (id == null) {
            return null;
        }
        List<OrgDiaryDetail> list = diaryService.findDetailListByDiaryId(id);
        model.addAttribute("list", list);
        return "";
    }

    /**
     * 查看直接下级的某一周的周报
     *
     * @param year
     * @param week
     * @param userId
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/findSubordinateOneWeek")
    public String findSubordinateOneWeek(Integer year, Integer week, String userId, Model model) {
        if (year == null || week == null || StringUtil.isBlank(userId)) {
            return null;
        }
        List<OrgDiary> list = diaryService.findDiaryListSubordinateOneWeek(userId, year, week);
        model.addAttribute("list", list);
        return "";
    }

    /**
     * 显示当前用户某一周的日志情况
     *
     * @param userId
     * @param beginDate
     * @param endDate
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/showEffect")
    public String showEffect(String userId, String beginDate, String endDate, Model model) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date bDate = format.parse(beginDate);
            Date eDate = format.parse(endDate);
            List<SystemEffort> list = effortService.findEffortListByUserAndDate(userId, bDate, eDate);
            model.addAttribute("list", list);
            return "";
        } catch (ParseException e) {
            e.getStackTrace();
        }
        return null;
    }

    /**
     * 显示某一用户所有的周报
     *
     * @param userId
     * @param model
     * @return
     */
    @RequiresPermissions("organizationDiary")
    @RequestMapping("/findListByUser")
    public String findListByUser(String userId, Model model) {
        OrgUser user = new OrgUser();
        if (StringUtil.isBlank(userId) || StringUtil.isEmpty(userId)) {
            user = userUtils.getUser();
            userId = user.getOrgUserId();
        }
        user = userUtils.getUserById(userId);
        List<OrgDiary> list = diaryService.findDiaryListByUserId(userId);
        model.addAttribute("list", list);
        model.addAttribute("orgUser", user);
        return "organization/diary/oneselfDiary";
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
    //@RequiresPermissions("organizationDiary")
    @RequestMapping("/find")
    public String find(String userId, Integer year, Integer week, Model model) {
        OrgUser user = new OrgUser();
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
    public String show(Model model) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        Integer year = ca.get(Calendar.YEAR);
        Integer week = ca.get(Calendar.WEEK_OF_YEAR);
        OrgUser user = userUtils.getUser();
        OrgDiary orgDiary = diaryService.findDiaryByUserAndDate(year, year, user.getOrgUserId());
        List<OrgDiary> list = diaryService.findDiaryListSubordinateOneWeek(user.getOrgUserId(), year, week);
        model.addAttribute("orgDiary", orgDiary);
        model.addAttribute("list", list);
        return "organization/diary/diary";
    }

    @RequestMapping("/test")
    public String test() {
        return "organization/diary/oneselfDiary";
    }
    @RequestMapping("/testdata")
    public String testdata() {
        return "organization/diary/diaryData";
    }

}
