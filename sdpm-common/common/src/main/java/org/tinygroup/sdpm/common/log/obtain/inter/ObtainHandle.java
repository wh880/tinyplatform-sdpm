package org.tinygroup.sdpm.common.log.obtain.inter;

import java.util.Map;

/**
 * Created by wangll13383 on 2015/9/16.
 */
public interface ObtainHandle {
    public static String Obtain_XSTREAM_= "obtain";

    public void addObtain(Obtains obtains, String filePath);

    public Obtain getDict(String key);

    public void removeObtains(Obtains obtains);
}
