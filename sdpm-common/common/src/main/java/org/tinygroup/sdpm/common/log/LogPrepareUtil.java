package org.tinygroup.sdpm.common.log;

import javax.servlet.http.HttpSession;

/**
 * Created by wangll13383 on 2015/10/8.
 */
public class LogPrepareUtil {

    private static ThreadLocal<HttpSession> session = new ThreadLocal<HttpSession>();

    public static void setSession(HttpSession httpSession){
        session.set(httpSession);
    }

    public static HttpSession getSession(){
        return session.get();
    }
}
