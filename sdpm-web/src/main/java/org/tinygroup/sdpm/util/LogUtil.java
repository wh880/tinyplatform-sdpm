package org.tinygroup.sdpm.util;

import org.tinygroup.sdpm.common.log.obtain.impl.ObtainHandleImpl;
import org.tinygroup.sdpm.common.log.obtain.inter.ObtainHandle;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.service.impl.LogServiceImpl;
import org.tinygroup.sdpm.system.service.inter.LogService;

import java.util.Date;

/**
 * Created by wangll13383 on 2015/10/13.
 */
public class LogUtil {
    //之后要换成服务包装类型
    public final static LogService logService = SpringContextHolder.getBean(LogServiceImpl.class);

    public static void log(LogOperateObject objectType, LogAction action, String objectId, String userId) {
        saveLog(objectType.getOperateObject(), action.getAction(), objectId, userId, null, null, null, null, null);
    }

    public static void logWithComment(LogOperateObject objectType, LogAction action, String objectId, String userId,
                                      String productId, String projectId,
                                      Object oldObject, Object newObject, String comment) {
        saveLog(objectType.getOperateObject(), action.getAction(), objectId, userId, productId, projectId, oldObject, newObject, comment);
    }

    public static void changeLog(LogOperateObject objectType, LogAction action, String objectId, String userId,
                                 String productId, String projectId,
                                 Object oldObject, Object newObject) {
        saveLog(objectType.getOperateObject(), action.getAction(), objectId, userId, productId, projectId, oldObject, newObject, null);
    }

    protected static void saveLog(String objectType, String action, String objectId, String userId,
                                  String project, String product,
                                  Object oldObject, Object newObject, String comment) {
        SystemAction systemAction = new SystemAction();
        systemAction.setActionAction(action);
        systemAction.setActionProduct(String.valueOf(product));
        systemAction.setActionProject(String.valueOf(project));
        systemAction.setActionObjectType(objectType);
        systemAction.setActionDate(new Date());
        systemAction.setActionObjectId(String.valueOf(objectId));
        systemAction.setActionActor(userId);
        systemAction.setActionComment(comment);
        logService.log(oldObject, newObject, systemAction);
    }

    public enum LogAction {
        CREATED("created"),          //创建
        OPENED("opened"),             //创建
        CHANGED("changed"),           //变更了
        EDITED("edited"),          //编辑了
        ASSIGNED("assigned"),           //指派了
        CLOSED("closed"),             //关闭了
        DELETED("deleted"),            //删除了
        DELETEDFILE("deletedfile"),         //删除附件
        EDITFILE("editfile"),            //编辑附件
        ERASED("erased"),             //删除了
        UNDELETED("undeleted"),          //还原了
        HIDDEN("hidden"),             //隐藏了
        COMMENTED("commented"),          //评论了
        ACTIVATED("activated"),          //激活了
        RESOLVED("resolved"),           //解决了
        REVIEWED("reviewed"),           //评审了
        MOVED("moved"),              //移动了
        CONFIRMED("confirmed"),          //确认了需求，
        BUGCONFIRMED("bugconfirmed"),       //确认了
        TOSTORY("tostory"),            //转需求
        FROMBUG("frombug"),            //来源bug
        TOTASK("totask"),             //转任务
        SVNCOMMITED("svncommited"),        //提交代码
        GITCOMMITED("gitcommited"),        //提交代码
        LINKED2PLAN("linked2plan"),        //关联计划
        UNLINKEDFROMPLAN("unlinkedfromplan"),   //移除计划
        MARKED("marked"),             //编辑了
        LINKED2PROJECT("linked2project"),     //关联{$lang->projectCommon}
        UNLINKEDFROMPROJECT("unlinkedfromproject"), //移除{$lang->projectCommon}
        STARTED("started"),            //开始了
        RESTARTED("restarted"),          //继续了
        RECORDESTIMATE("recordestimate"),     //记录了工时
        EDITESTIMATE("editestimate"),       //编辑了工时
        CANCELED("canceled"),           //取消了
        FINISHED("finished"),           //完成了
        PAUSED("paused"),             //暂停了
        DELAYED("delayed"),            //延期
        SUSPENDED("suspended"),          //挂起
        LOGIN("login"),              //登录系统
        LOGOUT("logout"),             //退出登录
        DELETEESTIMATE("deleteestimate");     //删除了工时

        private final String action;

        LogAction(String value) {
            this.action = value;
        }

        public String getAction() {
            return action;
        }
    }

    public enum LogOperateObject {
        USER("user"),
        STORY("story"),
        TASK("task"),
        PRODUCTPLAN("productplan"),
        RELEASE("release"),
        PROJECT("project"),
        PRODUCT("product"),
        BUILD("build"),
        BUG("bug"),
        CASE("case"),
        TESTTASK("testtask"),
        TODO("todo"),
        DOCLIB("doclib"),
        DOC("doc"),
        SLA("sla"),
        CLIENT("client"),
        FAQ("faq"),
        REPLY("reply"),
        REVIEW("review"),
        REQUEST("request");
        private final String operateObject;

        LogOperateObject(String value) {
            this.operateObject = value;
        }

        public String getOperateObject() {
            return operateObject;
        }
    }
}

