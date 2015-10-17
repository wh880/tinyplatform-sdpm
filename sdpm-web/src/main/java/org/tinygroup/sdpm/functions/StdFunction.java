package org.tinygroup.sdpm.functions;

import org.tinygroup.sdpm.common.util.std.StdUtil;
import org.tinygroup.template.Template;
import org.tinygroup.template.TemplateContext;
import org.tinygroup.template.TemplateException;
import org.tinygroup.template.function.AbstractTemplateFunction;

/**
 * Created by wangll13383 on 2015/10/16.
 */
public class StdFunction extends AbstractTemplateFunction {

    public StdFunction() {
        super("getStd");
    }

    public Object execute(Template template, TemplateContext context, Object... parameters) throws TemplateException {
        if(parameters.length < 2){
           return null;
        }
        String field  = StdUtil.getField(parameters[0].toString(),parameters[1].toString());

        return field;
    }
}
