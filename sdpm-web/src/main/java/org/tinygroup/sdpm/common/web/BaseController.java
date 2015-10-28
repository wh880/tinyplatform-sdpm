package org.tinygroup.sdpm.common.web;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartFile;
import org.tinygroup.logger.Logger;
import org.tinygroup.logger.LoggerFactory;
import org.tinygroup.sdpm.action.system.FileRepository;
import org.tinygroup.sdpm.common.beanvalidator.BeanValidators;
import org.tinygroup.sdpm.common.util.DateUtils;
import org.tinygroup.sdpm.system.dao.pojo.ProfileType;
import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;
import org.tinygroup.sdpm.system.service.inter.LogService;
import org.tinygroup.sdpm.system.service.inter.ProfileService;
import org.tinygroup.sdpm.util.UserUtils;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础控制器
 * Created by Hulk on 2015/9/20.
 */
public abstract class BaseController {
    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(BaseController.class);
    /**
     * 动态记录对象
     */
    @Autowired
    protected LogService logService;
    /**
     * 验证Bean实例对象
     */
    @Autowired
    protected Validator validator;

    /**
     * 管理基础路径
     */
    @Value("${adminPath}")
    protected String adminPath;

    /**
     * 上传路径
     */
    @Value("${userfiles.basedir}")
    protected String UPLOAD_PATH;

    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private ProfileService profileService;

    protected BaseController() {
    }

    /**
     * 服务端参数有效性验证
     *
     * @param object 验证的实体对象
     * @param groups 验证组
     * @return 验证成功：返回true；严重失败：将错误信息添加到 message 中
     */
    protected boolean beanValidator(Model model, Object object, Class<?>... groups) {
        try {
            BeanValidators.validateWithException(validator, object, groups);
        } catch (ConstraintViolationException ex) {
            List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
            list.add(0, "数据验证失败：");
            addMessage(model, list.toArray(new String[]{}));
            return false;
        }
        return true;
    }


//    /**
//     * 客户端返回JSON字符串
//     *
//     * @param response
//     * @param object
//     * @return
//     */
//    protected String renderString(HttpServletResponse response, Object object) {
//        return renderString(response, JsonMapper.toJsonString(object), "application/json");
//    }

    /**
     * 服务端参数有效性验证
     *
     * @param object 验证的实体对象
     * @param groups 验证组，不传入此参数时，同@Valid注解验证
     * @return 验证成功：继续执行；验证失败：抛出异常跳转400页面。
     */
    protected void beanValidator(Object object, Class<?>... groups) {
        BeanValidators.validateWithException(validator, object, groups);
    }

//    /**
//     * 参数绑定异常
//     */
//    @ExceptionHandler({BindException.class, ConstraintViolationException.class, ValidationException.class})
//    public String bindException() {
//        return "error/400";
//    }

//    /**
//     * 授权登录异常
//     */
//    @ExceptionHandler({AuthenticationException.class})
//    public String authenticationException() {
//        return "error/40x";
//    }
//

    /**
     * 添加Model消息
     *
     * @param model
     * @param messages
     */
    protected void addMessage(Model model, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages) {
            sb.append(message).append(messages.length > 1 ? "<br/>" : "");
        }
        model.addAttribute("message", sb.toString());
    }

    /**
     * 客户端返回字符串
     *
     * @param response
     * @param string
     * @return
     */
    protected String renderString(HttpServletResponse response, String string, String type) {
        try {
            response.reset();
            response.setContentType(type);
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 初始化数据绑定
     * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
     * 2. 将字段中Date类型转换为String类型
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
//        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
//            @Override
//            public void setAsText(String text) {
//                setValue(text == null ? null : StringEscapeUtils.escapeHtml(text.trim()));
//            }
//
//            @Override
//            public String getAsText() {
//                Object value = getValue();
//                return value != null ? value.toString() : "";
//            }
//        });
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
//			@Override
//			public String getAsText() {
//				Object value = getValue();
//				return value != null ? DateUtils.formatDateTime((Date)value) : "";
//			}
        });
    }

    /**
     * 添加单个附件
     *
     * @param uploadFile
     * @param objectId
     * @param type
     * @param title
     * @throws IOException
     */
    public void upload(MultipartFile uploadFile, Integer objectId, ProfileType type, String title) throws IOException {
        String origName = uploadFile.getOriginalFilename();
        String ext = FilenameUtils.getExtension(origName);
        String fileUrl = fileRepository.storeByExt(UPLOAD_PATH, origName, uploadFile);
        fileUrl = fileRepository.resolverFilePath(fileUrl, UPLOAD_PATH);
        long size = uploadFile.getSize();
        SystemProfile profile = new SystemProfile(fileUrl, title, ext, (int) size,
                type.getType(), objectId, UserUtils.getUserAccount(), new Date(), null, null);
        profileService.add(profile);
    }

    /**
     * 添加多个附件
     *
     * @param uploadFiles
     * @param objectId
     * @param type
     * @param title
     * @throws IOException
     */
    public void uploads(MultipartFile[] uploadFiles, Integer objectId, ProfileType type, String[] title) throws IOException {
        if (uploadFiles == null || uploadFiles.length < 1) {
            return;
        }
        for (int i = 0, n = uploadFiles.length; i < n; i++) {
            if (!uploadFiles[i].isEmpty() && uploadFiles[i].getSize() > 0) {
                upload(uploadFiles[i], objectId, type, title[i]);
            }
        }
    }

    /**
     * 添加附件没有标题
     *
     * @param uploadFile
     * @param objectId
     * @param type
     * @throws IOException
     */

    public void uploadNoTitle(MultipartFile uploadFile, Integer objectId, ProfileType type) throws IOException {
        String origName = uploadFile.getOriginalFilename();
        String ext = FilenameUtils.getExtension(origName);
        String fileUrl = fileRepository.storeByExt(UPLOAD_PATH, origName, uploadFile);
        fileUrl = fileRepository.resolverFilePath(fileUrl, UPLOAD_PATH);
        long size = uploadFile.getSize();
        SystemProfile profile = new SystemProfile(fileUrl, null, ext, (int) size,
                type.getType(), objectId, UserUtils.getUserId(), new Date(), null, null);
        profileService.add(profile);
    }

    /**
     * 添加多个无标题附件附件
     *
     * @param uploadFiles
     * @param objectId
     * @param type
     */
    public void uploadNoTitles(MultipartFile[] uploadFiles, Integer objectId, ProfileType type) throws IOException {
        for (int i = 0, n = uploadFiles.length; i < n; i++) {
            if (!uploadFiles[i].isEmpty() && uploadFiles[i].getSize() > 0) {
                uploadNoTitle(uploadFiles[i], objectId, type);
            }
        }
    }

    /**
     * 拼装前台Ajax请求结果
     *
     * @param result
     * @param msg
     * @return
     */
    protected Map<String, String> resultMap(boolean result, final String msg) {
        Map<String, String> map = new HashMap<String, String>();
        if (result) {
            map.put("status", "y");
        } else {
            map.put("status", "n");
        }
        map.put("info", msg);
        return map;
    }

    /**
     * 根据日期来查
     * <p>
     * 1-今天 2-昨天 3-前天 4-本周 5-上周 6-本月 7-上月 0-所有
     * action_date BETWEEN '2015-10-16 00:00:00' AND '2015-10-16 23:59:59'
     *
     * @param selectDate
     * @param dateStart
     * @param dateEnd
     */
    public void betweenDate(String selectDate, Date dateStart, Date dateEnd) {
        Date date = new Date();
        Date startDate;
        Date endDate;

        if ("1".equals(selectDate)) {
            startDate = DateUtils.getDateStart(date);
            endDate = DateUtils.getDateEnd(date);
        } else if ("2".equals(selectDate)) {
            startDate = DateUtils.addDays(DateUtils.getDateStart(date), -1);
            endDate = DateUtils.addDays(DateUtils.getDateEnd(date), -1);
        } else if ("3".equals(selectDate)) {
            startDate = DateUtils.addDays(DateUtils.getDateStart(date), -2);
            endDate = DateUtils.addDays(DateUtils.getDateEnd(date), -2);
        } else if ("4".equals(selectDate)) {
            startDate = DateUtils.getFirstDayOfWeek(date);
            endDate = DateUtils.getLastDayOfWeek(date);
        } else if ("5".equals(selectDate)) {
            startDate = DateUtils.addDays(DateUtils.getFirstDayOfWeek(date), -7);
            endDate = DateUtils.addDays(DateUtils.getLastDayOfWeek(date), -7);
        } else if ("6".equals(selectDate)) {
            startDate = DateUtils.getFirstDayOfMonth(date);
            endDate = DateUtils.getLastDayOfMonth(date);
        } else if ("7".equals(selectDate)) {
            startDate = DateUtils.getFirstDayOfMonth(DateUtils.addMonths(date, -1));
            endDate = DateUtils.getLastDayOfMonth(DateUtils.addMonths(date, -1));
        } else {
            startDate = null;
            endDate = null;
        }
        if (startDate != null) {
            dateStart.setTime(startDate.getTime());
        }
        if (endDate != null) {
            dateEnd.setTime(endDate.getTime());
        }
    }

}
