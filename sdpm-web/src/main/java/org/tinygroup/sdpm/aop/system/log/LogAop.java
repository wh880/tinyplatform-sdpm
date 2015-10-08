package org.tinygroup.sdpm.aop.system.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.sdpm.common.log.annotation.LogClass;
import org.tinygroup.sdpm.common.log.annotation.LogMethod;
import org.tinygroup.sdpm.common.log.obtain.inter.Obtain;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by wangll13383 on 2015/9/21.
 */
public class LogAop {
    private final static Log log = LogFactory.getLog(LogAop.class);

    //配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
    @Pointcut("@annotation(org.tinygroup.sdpm.common.log.annotation.LogMethod)")
    public void aspect(){	}

    @Around("aspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object target = joinPoint.getTarget();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Class[] parameterTypes = ((MethodSignature)joinPoint.getSignature()).getMethod().getParameterTypes();
        //LogClass为类级注解
        LogClass logClass = target.getClass().getAnnotation(LogClass.class);

        Object result = joinPoint.proceed();
        HttpSession session = LogUtil.getSession();

        String classType = logClass.value();
        Obtain obtain = LogUtil.getObtain(classType);
        //LogMethod为方法级注解
        LogMethod logMethod = null;
        try {

            logMethod = target.getClass().getMethod(methodName,parameterTypes).getAnnotation(LogMethod.class);


        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        if(logMethod!=null){
            Object service = serviceInstance(obtain.getType());
            if("edit".equals(logMethod.value())){
                //之后调用时的id
                // Object id = reflectValue(args[0],toMethod(obtain.getPrimaryName()),null);
                // 1 之后需改成 对象id值
                Object old = reflectValue(service,obtain.getMethod(),1);
                if((Boolean)result){
                    //生成日志 (String)session.getAttribute("user") 之后需改成用户名
                    recordEdit(classType,logMethod.value(),obtain,old,args[0],(String)session.getAttribute("user"));
                }
            }else if("add".equals(logMethod.value())){

            }else if("delete".equals(logMethod.value())){

            }else if("batchAdd".equals(logMethod.value())){

            }else if("batchDelete".equals(logMethod.value())){

            }




        }






        return joinPoint.proceed();
    }


    public void recordEdit(String type, String operatorType , Obtain obtain, Object oldObject, Object newObject, String user){
        if("edit".equals(operatorType)){
            Field[] fields =  oldObject.getClass().getDeclaredFields();
            for(Field field : fields){
                Object oldValue = reflectValue(oldObject,toMethod(field.getName()),null);
                Object newValue = reflectValue(newObject,toMethod(field.getName()),null);
                if(compare(oldValue,newValue)){
                    continue;
                }else {
                    System.out.println("操作人："+user+",操作对象："+type+",操作类型："+operatorType+",操作字段："+field.getName()+",旧值："+oldValue+",新值："+newValue);
                }

            }
        }

    }

    public String toMethod(String fieldName){
        char[] chars = fieldName.toCharArray();
        chars[0] =(char)(chars[0]-32);
        return "get"+String.valueOf(chars);
    }

    public Object reflectValue(Object object, String methodName, Object arg){
        Object value = null;
        try {
            if(arg ==null){
                value = object.getClass().getDeclaredMethod(methodName).invoke(object);
            }else{
                Class clz = null;
                if(arg instanceof Integer){
                    clz = int.class;
                }else if(arg instanceof String){
                    clz = String.class;
                }
                value = object.getClass().getDeclaredMethod(methodName,clz).invoke(object,arg);
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return value;
    }

    private Class toClass(String type){
        Class clz = null;
        try {
            clz = Class.forName(type);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clz;
    }

    private Object serviceInstance(String type){
        Object o = null;
        try {
            o = BeanContainerFactory.getBeanContainer(getClass().getClassLoader()).getBean(Class.forName(type));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
/*        if(o == null){
            try {
                o = toClass(type).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }*/
        return o;
    }
    public boolean compare(Object oldOne, Object newOne){
        return oldOne.equals(newOne);
    }
}
