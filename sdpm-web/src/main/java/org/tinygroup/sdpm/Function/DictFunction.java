package org.tinygroup.sdpm.Function;

import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.sdpm.system.service.util.DictUtil;
import org.tinygroup.template.*;
import org.tinygroup.template.function.AbstractTemplateFunction;

/**
 * Created by wangll13383 on 2015/9/29.
 */
public class DictFunction extends AbstractTemplateFunction {

    public DictFunction() {
        super("getDict");
    }

    public Object execute(Template template, TemplateContext context, Object... parameters) throws TemplateException {

        DictUtil dictUtil = BeanContainerFactory.getBeanContainer(this.getClass().getClassLoader()).getBean(DictUtil.class);
        if(parameters.length == 0){
            throw new RuntimeException("字典项名称不能为空");
        }
        return dictUtil.getDict(parameters[0].toString());
    }
}