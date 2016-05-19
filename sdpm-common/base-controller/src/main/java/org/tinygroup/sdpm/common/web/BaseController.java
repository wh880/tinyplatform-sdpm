package org.tinygroup.sdpm.common.web;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.tinygroup.commons.tools.ArrayUtil;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.convert.objectjson.fastjson.ObjectToJson;
import org.tinygroup.logger.Logger;
import org.tinygroup.logger.LoggerFactory;
import org.tinygroup.sdpm.common.beanvalidator.BeanValidators;
import org.tinygroup.sdpm.common.util.DateUtils;
import org.tinygroup.sdpm.dto.UploadProfile;
import org.tinygroup.sdpm.system.dao.pojo.ProfileType;
import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;
import org.tinygroup.sdpm.system.service.inter.ConfigService;
import org.tinygroup.sdpm.system.service.inter.LogService;
import org.tinygroup.sdpm.system.service.inter.ProfileService;
import org.tinygroup.sdpm.util.ProductUtils;
import org.tinygroup.sdpm.util.ProjectOperate;
import org.tinygroup.sdpm.util.UploadUtils;
import org.tinygroup.sdpm.util.UserUtils;
import org.tinygroup.springmvc.multipart.TinyMultipartFile;
import org.tinygroup.weblayer.WebContext;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.*;

/**
 * 基础控制器
 * Created by Hulk on 2015/9/20.
 */
public abstract class BaseController {

    @Autowired
    protected ProjectOperate projectOperate;
    @Autowired
    protected UserUtils userUtils;
    @Autowired
    protected ProductUtils productUtils;
    @Autowired
    protected ConfigService configService;

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
    /**
     * 用户url前缀
     */
    @Value("${userFiles.prefix}")
    protected String UPLOAD_PREFIX;
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
    @Autowired
    private ProfileService profileService;

    private WebContext webContext;

    protected BaseController() {
    }

    public void initSearchBar(Model model, String label) {
        model.addAttribute("searchCurrentName", label);
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


    /**
     * 客户端返回JSON字符串
     *
     * @param response
     * @param object
     * @return
     */
    protected String renderString(HttpServletResponse response, Object object) {
        ObjectToJson objectToJson = new ObjectToJson();
        return renderString(response, objectToJson.convert(object), "application/json");
    }

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
     * 不存在异常返回
     */
    public String notFoundView() {
        return "error/notFound";
    }

    /**
     * 错误信息页面
     *
     * @param msg   错误信息
     * @param model
     * @return
     */
    public String errorPage(String msg, Model model) {
        if (model != null) {
            model.addAttribute("msg,", msg);
        }
        return "error/error";
    }

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
        model.addAttribute("globalMessage", sb.toString());
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
        } catch (IOException e) {
        }
        return null;
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
     * 处理附件
     *
     * @param uploadProfile
     * @param objectId
     * @param type
     * @throws IOException
     */
    public void processProfile(UploadProfile uploadProfile, Integer objectId, ProfileType type) throws IOException {
        uploadMultiFiles(uploadProfile, objectId, type);
        updateUploadFile(uploadProfile, objectId, type);
    }

    /**
     * 更新附件
     *
     * @param uploadProfile
     * @throws IOException
     */
    public void updateUploadFile(UploadProfile uploadProfile, Integer objectId, ProfileType type) throws IOException {
        List<SystemProfile> updateProfileList = uploadProfile.getUpdateProfileList();
        List<TinyMultipartFile> updateFileList = uploadProfile.getUpdateFileList();
        List<SystemProfile> updateList = new ArrayList<SystemProfile>();
        if (CollectionUtil.isEmpty(updateFileList) || CollectionUtil.isEmpty(updateProfileList) ||
                updateProfileList.size() != updateFileList.size()) {
            return;
        }
        for (int i = 0, n = updateFileList.size(); i < n; i++) {
            TinyMultipartFile multipartFile = updateFileList.get(i);
            SystemProfile profile = updateProfileList.get(i);
            if (profile.getFileId() == null) {
                continue;
            }
            SystemProfile systemProfile = saveProfile(multipartFile, objectId, type, null);
            if (systemProfile != null) {
                systemProfile.setFileTitle(profile.getFileTitle());
                systemProfile.setFileId(profile.getFileId());
                updateList.add(systemProfile);
            } else {
                SystemProfile oldProfile = profileService.findSystemProfileById(profile.getFileId());
                oldProfile.setFileTitle(profile.getFileTitle());
                updateList.add(oldProfile);
            }
        }
        profileService.batchUpdateSystemProfile(updateList);
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
    public void upload(TinyMultipartFile uploadFile, Integer objectId, ProfileType type, String title) throws IOException {
        SystemProfile systemProfile = saveProfile(uploadFile, objectId, type, title);
        profileService.addSystemProfile(systemProfile);
    }

    protected SystemProfile saveProfile(TinyMultipartFile uploadFile, Integer objectId, ProfileType type, String title) throws IOException {
        if (uploadFile == null || uploadFile.isEmpty()) {
            return null;
        }
        String origName = uploadFile.getOriginalFilename();
        String ext = FilenameUtils.getExtension(origName);
        String fileUrl = uploadFile.toFileObject().getAbsolutePath();
        fileUrl = UploadUtils.resolverFilePath(fileUrl, UPLOAD_PATH);
        int size = (int) uploadFile.getSize();
        return new SystemProfile(fileUrl, title, ext, size,
                type.getType(), objectId, userUtils.getUserAccount(), new Date());

    }

    /**
     * 添加多个附件
     *
     * @param uploadProfile
     * @param objectId
     * @param type
     * @throws IOException
     */
    public void uploadMultiFiles(UploadProfile uploadProfile, Integer objectId, ProfileType type) throws IOException {
        TinyMultipartFile[] uploadFiles = uploadProfile.getNewUploadFile();
        String[] title = uploadProfile.getNewUploadFileTitle();
        if (ArrayUtil.isEmptyArray(uploadFiles)) {
            return;
        }
        List<SystemProfile> list = new ArrayList<SystemProfile>();
        for (int i = 0, n = uploadFiles.length; i < n; i++) {
            SystemProfile systemProfile = saveProfile(uploadFiles[i], objectId, type, title[i]);
            if (systemProfile != null) {
                list.add(systemProfile);
            }
        }
        if (!CollectionUtil.isEmpty(list)) {
            profileService.batchAddSystemProfile(list);
        }
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
    public void uploadMultiFiles(TinyMultipartFile[] uploadFiles, Integer objectId, ProfileType type, String[] title) throws IOException {

        if (ArrayUtil.isEmptyArray(uploadFiles)) {
            return;
        }
        List<SystemProfile> list = new ArrayList<SystemProfile>();
        for (int i = 0, n = uploadFiles.length; i < n; i++) {
            SystemProfile systemProfile = saveProfile(uploadFiles[i], objectId, type, title[i]);
            if (systemProfile != null) {
                list.add(systemProfile);
            }
        }
        if (!CollectionUtil.isEmpty(list)) {
            profileService.batchAddSystemProfile(list);
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

    public void uploadNoTitle(TinyMultipartFile uploadFile, Integer objectId, ProfileType type) throws IOException {
        SystemProfile profile = saveProfile(uploadFile, objectId, type, null);
        profileService.addSystemProfile(profile);
    }


    /**
     * 添加多个无标题附件附件
     *
     * @param uploadFiles
     * @param objectId
     * @param type
     */
    public void uploadNoTitles(TinyMultipartFile[] uploadFiles, Integer objectId, ProfileType type) throws IOException {
        if (ArrayUtil.isEmptyArray(uploadFiles)) {
            return;
        }
        List<SystemProfile> list = new ArrayList<SystemProfile>();
        for (int i = 0, n = uploadFiles.length; i < n; i++) {
            SystemProfile systemProfile = saveProfile(uploadFiles[i], objectId, type, null);
            list.add(systemProfile);
        }
        if (!CollectionUtil.isEmpty(list)) {
            profileService.batchAddSystemProfile(list);
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
        Date startDate = null;
        Date endDate = null;

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
            dateStart = null;
            dateEnd = null;
        }
        if (startDate != null) {
            dateStart.setTime(startDate.getTime());
        }
        if (endDate != null) {
            dateEnd.setTime(endDate.getTime());
        }
    }

    public String redirectProjectForm() {
        return "redirect:" + adminPath + "/project/form";
    }

    public void setContext(WebContext webContext) {
        this.webContext = webContext;
    }

}
