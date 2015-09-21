package org.tinygroup.sdpm.common.util.std.processor;

import org.tinygroup.application.Application;
import org.tinygroup.application.ApplicationProcessor;
import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.database.table.TableProcessor;
import org.tinygroup.database.table.impl.TableProcessorImpl;
import org.tinygroup.sdpm.common.util.std.StdUtil;
import org.tinygroup.xmlparser.node.XmlNode;

/**
 * Created by wangll13383 on 2015/9/21.
 */
public class StdFieldProcessor implements ApplicationProcessor{
    public void start() {
        TableProcessor tp = BeanContainerFactory.getBeanContainer(this.getClass().getClassLoader()).getBean(TableProcessorImpl.class);
        StdUtil.stdProcess(tp.getTables());
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
