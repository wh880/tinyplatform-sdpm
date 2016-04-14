package org.tinygroup.sdpm.action.system;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.dto.system.Holidays;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.system.dao.pojo.Holiday;
import org.tinygroup.sdpm.system.service.inter.HolidayService;
import org.tinygroup.sdpm.util.LogUtil;
import org.tinygroup.sdpm.util.LogUtil.LogAction;
import org.tinygroup.sdpm.util.LogUtil.LogOperateObject;
import org.tinygroup.tinysqldsl.Pager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("a/system")
public class HolidayAction extends BaseController {
    @Autowired
    private HolidayService holidayService;
    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String index() {
        return "/system/page/holiday/holiday.page";
    }

    @RequestMapping("holiday")
    public String holiday() {
        return "/system/page/holiday/holiday.page";
    }

    @RequestMapping("holiday/add")
    public String holidayAdd() {
        return "/system/page/holiday/holiday-add.pagelet";
    }

    @RequestMapping("holiday/findPager")
    public String fingPage(Integer start, Integer limit,
                           @RequestParam(required = false, defaultValue = "holidayId") String order,
                           @RequestParam(required = false, defaultValue = "desc") String ordertype, Holiday holiday, Model model) {
        boolean asc = true;
        if ("desc".equals(ordertype)) {
            asc = false;
        }
        holiday.setHolidayDeleted(0);
        //Pager<Holiday> holidayPage = holidayService.findByPage(start, limit, holiday, order, asc);
        Pager<Holiday> holidayPage=holidayService.findByHolidayDeleted(start, limit, holiday, order, asc);
        model.addAttribute("holiday", holidayPage);
        return "/system/page/holiday/data/holidaydata.pagelet";
    }

    @RequestMapping("holiday/find")
    public String find(Holiday holiday, Model model) {
        Holiday day = holidayService.findHolidayById(holiday.getHolidayId());
        model.addAttribute("holiday", day);
        return "/system/page/holiday/edit.page";
    }

    @RequestMapping("holiday/view")
    public String view(Holiday holiday, Model model) {
        Holiday day = holidayService.findHolidayById(holiday.getHolidayId());
        model.addAttribute("holiday", day);
        return "/system/page/holiday/view.page";
    }

    @RequestMapping(value = "holiday/save", method = RequestMethod.POST)
    public String saveHoliday(@RequestParam(required = false) String selectList, Holiday holiday, Model model) {
        System.out.println(holiday.getHolidayName()+" "+holiday.getHolidayType()+" "+holiday.getHolidayDate());
        if (holiday.getHolidayId() == null) {
            List<Holiday> holidayList = new ArrayList<Holiday>();
            if (!StringUtils.isBlank(selectList)) {
                String[] dates = selectList.split(",");
                for (int i = 0, n = dates.length; i < n; i++) {
                    Holiday day = new Holiday();
                    day.setCompanyId(holiday.getCompanyId());
                    day.setHoilidayRemark(holiday.getHoilidayRemark());
                    day.setHolidayAccount(userUtils.getUserId());
                    day.setHolidayDate(dates[i]);
                    day.setHolidayDeleted(holiday.getHolidayDeleted());
                    day.setHolidayDetail(holiday.getHolidayDetail());
                    day.setHolidayName(holiday.getHolidayName());
                    day.setHolidayType(holiday.getHolidayType());
                    holidayList.add(day);
                }
            }
            List<Holiday> holidays = holidayService.batchAddHoliday(holidayList);
            for (int i = 0, n = holidays.size(); i < n; i++) {
                LogUtil.logWithComment(LogOperateObject.HOLIDAY,
                        LogAction.OPENED, String.valueOf(holidays.get(i).getHolidayId()),
                        userUtils.getUserId(), null, null, null, null, null);
            }
        } else {
            Holiday holiday1 = holidayService.findHolidayById(holiday.getHolidayId());
            holidayService.updateHoliday(holiday);
            LogUtil.logWithComment(LogOperateObject.HOLIDAY, LogAction.EDITED,
                    String.valueOf(holiday.getHolidayId()),
                    userUtils.getUserId(), null, null, holiday1, holiday, null);
        }
        model.addAttribute("holiday", holiday);
        return "redirect:/a/system";
    }

    @ResponseBody
    @RequestMapping(value = "holiday/delete")
    public Map<String, String> deleteHoliday(Integer id) {
        Map<String, String> map = new HashedMap();
        if (id != null) {
            Holiday holiday = new Holiday();
            holiday.setHolidayId(id);
            holidayService.deleteHoliday(holiday);
            map.put("info", "删除成功");
            map.put("status", "y");
            LogUtil.logWithComment(LogOperateObject.HOLIDAY, LogAction.DELETED,
                    String.valueOf(id), userUtils.getUserId(), null,
                    null, null, null, null);
        } else {
            map.put("info", "删除失败");
            map.put("status", "n");
        }
        return map;
    }

    @RequestMapping("holiday/manage")
    public String manage(Holiday holiday, Model model) {
        List<Holiday> holidayList = holidayService.findHolidayList(holiday);
        model.addAttribute("holiday", holidayList);
        return "/system/page/holiday/manage.page";
    }

    @RequestMapping("holiday/findIds")
    public String findIds(String ids, Model model) {
        String[] sids = ids.split(",");
        Integer[] intIds = new Integer[sids.length];
        for (int i = 0; i < sids.length; i++) {
            intIds[i] = Integer.valueOf(sids[i]);
        }
        List<Holiday> holidayList = holidayService.findHolidayByIds(intIds);
        model.addAttribute("holiday", holidayList);
        return "/system/page/holiday/batch-del.pagelet";
    }

    @RequestMapping("holiday/batchDelete")
    public String batchDelete(Holidays holidays) {
        List<Holiday> holiday = holidays.getHoliday();
        for (int i = 0, n = holiday.size(); i < n; i++) {
            LogUtil.logWithComment(LogOperateObject.HOLIDAY, LogAction.DELETED,
                    String.valueOf(holiday.get(i).getHolidayId()), userUtils.getUserId(), null,
                    null, null, null, holiday.get(i).getHoilidayRemark());
        }
        holidayService.batchSoftDeleteHoliday(holiday);
        return "/system/page/holiday/holiday.page";
    }

    @RequestMapping("holiday/action")
    public String holidayAction(Model model) {
        Holiday holiday = new Holiday();
        Pager<Holiday> historyPage = holidayService.findByPage(0, 7, holiday, "systemAction.actionDate", false);
        List<Holiday> histories = historyPage.getRecords();
        model.addAttribute("action", histories);
        return "/system/page/holiday/holiday-dynamic.pagelet";
    }

    @RequestMapping("holiday/history/more")
    public String historyMore() {
        return "/system/page/holiday/holidayHistory.pagelet";
    }

    @RequestMapping("holiday/history")
    public String holidayHistory(Integer start, Integer limit, String order, String ordertype, Model model) {
        Holiday holiday = new Holiday();
        boolean asc = true;
        if ("desc".equals(ordertype)) {
            asc = false;
        }
        Pager<Holiday> History = holidayService.findByPage(start, limit, holiday, "action_date", false);

        model.addAttribute("History", History);
        return "/system/page/holiday/holidayHistoryTableData.pagelet";
    }
}
