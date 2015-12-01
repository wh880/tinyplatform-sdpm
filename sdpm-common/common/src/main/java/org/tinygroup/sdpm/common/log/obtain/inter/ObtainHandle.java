package org.tinygroup.sdpm.common.log.obtain.inter;

/**
 * Created by wangll13383 on 2015/9/16.
 */
public interface ObtainHandle {
    String OBTAIN_XSTREAM = "obtain";

    void addObtain(Obtains obtains, String filePath);

    Obtain getDict(String key);

    void removeObtains(Obtains obtains);
}
