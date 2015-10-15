package org.tinygroup.sdpm.system.dictinit;

import org.tinygroup.application.Application;
import org.tinygroup.application.ApplicationProcessor;
import org.tinygroup.xmlparser.node.XmlNode;

/**
 * Created by wangll13383 on 2015/9/30.
 */
public class DictInitProcessor implements ApplicationProcessor{
    public void start() {
        DictUtil.init();
    }

    public void init() {

    }

    public void stop() {

    }

    public void setApplication(Application application) {

    }

    public String getApplicationNodePath() {
        return null;
    }

    public String getComponentConfigPath() {
        return null;
    }

    public void config(XmlNode applicationConfig, XmlNode componentConfig) {

    }

    public XmlNode getComponentConfig() {
        return null;
    }

    public XmlNode getApplicationConfig() {
        return null;
    }

    public int getOrder() {
        return 0;
    }
}
