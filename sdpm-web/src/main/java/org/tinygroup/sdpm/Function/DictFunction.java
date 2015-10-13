package org.tinygroup.sdpm.Function;

import org.tinygroup.sdpm.system.dictinit.DictUtil;
import org.tinygroup.template.Template;
import org.tinygroup.template.TemplateContext;
import org.tinygroup.template.TemplateException;
import org.tinygroup.template.function.AbstractTemplateFunction;

/**
 * Created by wangll13383 on 2015/9/29.
 */
public class DictFunction extends AbstractTemplateFunction {

    public DictFunction() {
        super("getDict");
    }

    public Object execute(Template template, TemplateContext context, Object... parameters) throws TemplateException {

        if(parameters.length == 0){
            throw new RuntimeException("字典项名称不能为空");
        }
        if(parameters.length>1){
            return DictUtil.getDict(parameters[0].toString(),parameters[1].toString());
        }
        return DictUtil.getDict(parameters[0].toString());
    }
}
