package org.tinygroup.sdpm.action.system;

import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.sdpm.common.log.obtain.impl.ObtainHandleImpl;
import org.tinygroup.sdpm.common.log.obtain.inter.Obtain;
import org.tinygroup.sdpm.common.log.obtain.inter.ObtainHandle;
import org.tinygroup.sdpm.common.log.obtain.inter.ObtainParameter;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.service.impl.LogServiceImpl;
import org.tinygroup.sdpm.system.service.inter.LogService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by wangll13383 on 2015/10/13.
 */
public class LogUtil {
    //之后要换成服务包装类型
    public final static LogService LOGSERVICE = BeanContainerFactory.getBeanContainer(LogUtil.class.getClassLoader()).getBean(LogServiceImpl.class);

    private final static ObtainHandle OBTAIN_HANDLE = BeanContainerFactory.getBeanContainer(LogUtil.class.getClassLoader()).getBean(ObtainHandleImpl.class);

    public static void log(String objectType, int objectId, int project, int product, String actor, String action ,String comment ,Object oldObject, Object newObject){
        SystemAction systemAction = new SystemAction();
        systemAction.setActionAction(action);
        systemAction.setActionProduct(String.valueOf(product));
        systemAction.setActionProject(project);
        systemAction.setActionObjectType(objectType);
        systemAction.setActionDate(new Date());
        systemAction.setActionObjectId(objectId);
        systemAction.setActionActor(actor);
        systemAction.setActionComment(comment);
        LOGSERVICE.log(oldObject,newObject,systemAction);
    }

    public static void log(String objectType, int objectId, int project, int product, String actor, String action ,String comment){
        log(objectType,objectId,project,product,actor,action,comment,null,null);
    }

    public static void log(String objectType, int objectId, int project, int product, String actor, String action){
        log(objectType,objectId,project,product,actor,action,null,null,null);
    }

    public static Object getObjectById(int id,String type){
        Obtain obtain = OBTAIN_HANDLE.getDict(type);
        return getValue(id,obtain);
    }

    public static String getInfoUrl(String type){
        return OBTAIN_HANDLE.getDict(type).getUrl();
    }

    private static Object getValue(int id, Obtain obtain){
        Class[] parameter = new Class[obtain.getParameters().size()];
        Object value = null;
        for(int i = 0;i<obtain.getParameters().size(); i++){
            try {
                parameter[i]=Class.forName(obtain.getParameters().get(i).getType());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        try {
            Object serviceWapper = BeanContainerFactory
                    .getBeanContainer(LogUtil.class.getClassLoader())
                    .getBean(Class.forName(obtain.getType()));
            Method method = serviceWapper.getClass().getMethod(obtain.getMethod(),parameter);
            value = method.invoke(serviceWapper,id);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("obtain moduleType不存在");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return value;
    }

    static class Oprate{
        public final static String CREATED = "created";          //创建
        public final static String OPENED = "opened";             //创建
        public final static String CHANGED = "changed";           //变更了
        public final static String EDITED = "edited";          //编辑了
        public final static String ASSIGNED = "assigned";           //指派了
        public final static String CLOSED = "closed";             //关闭了
        public final static String DELETED = "deleted";            //删除了
        public final static String DELETEDFILE = "deletedfile";         //删除附件
        public final static String EDITFILE = "editfile";            //编辑附件
        public final static String ERASED = "erased";             //删除了
        public final static String UNDELETED = "undeleted";          //还原了
        public final static String HIDDEN = "hidden";             //隐藏了
        public final static String COMMENTED = "commented";          //评论了
        public final static String ACTIVATED = "activated";          //激活了
        public final static String RESOLVED = "resolved";           //解决了
        public final static String REVIEWED = "reviewed";           //评审了
        public final static String MOVED = "moved";              //移动了
        public final static String CONFIRMED = "confirmed";          //确认了需求，
        public final static String BUGCONFIRMED = "bugconfirmed";       //确认了
        public final static String TOSTORY = "tostory";            //转需求
        public final static String FROMBUG = "frombug";            //来源bug
        public final static String TOTASK = "totask";             //转任务
        public final static String SVNCOMMITED = "svncommited";        //提交代码
        public final static String GITCOMMITED = "gitcommited";        //提交代码
        public final static String LINKED2PLAN = "linked2plan";        //关联计划
        public final static String UNLINKEDFROMPLAN = "unlinkedfromplan";   //移除计划
        public final static String MARKED = "marked";             //编辑了
        public final static String LINKED2PROJECT = "linked2project";     //关联{$lang->projectCommon}
        public final static String UNLINKEDFROMPROJECT = "unlinkedfromproject"; //移除{$lang->projectCommon}
        public final static String STARTED = "started";            //开始了
        public final static String RESTARTED = "restarted";          //继续了
        public final static String RECORDESTIMATE = "recordestimate";     //记录了工时
        public final static String EDITESTIMATE = "editestimate";       //编辑了工时
        public final static String CANCELED = "canceled";           //取消了
        public final static String FINISHED = "finished";           //完成了
        public final static String PAUSED = "paused";             //暂停了
        public final static String DELAYED = "delayed";            //延期
        public final static String SUSPENDED = "suspended";          //挂起
        public final static String LOGIN = "login";              //登录系统
        public final static String LOGOUT = "logout";             //退出登录
        public final static String DELETEESTIMATE = "deleteestimate";     //删除了工时
    }

    static class OprateObject{
        public final static String USER= "user";
        public final static String STORY= "story";
        public final static String TASK= "task";
        public final static String PRODUCTPLAN= "productplan";
        public final static String RELEASE= "release";
        public final static String PROJECT= "project";
        public final static String PRODUCT= "product";
        public final static String BUILD= "build";
        public final static String BUG= "bug";
        public final static String CASE= "case";
        public final static String TESTTASK= "testtask";
        public final static String TODO= "todo";
        public final static String DOCLIB= "doclib";
        public final static String DOC= "doc";
    }
}

