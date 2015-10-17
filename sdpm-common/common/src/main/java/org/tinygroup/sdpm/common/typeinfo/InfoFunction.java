package org.tinygroup.sdpm.common.typeinfo;

import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.template.Template;
import org.tinygroup.template.TemplateContext;
import org.tinygroup.template.TemplateException;
import org.tinygroup.template.function.AbstractTemplateFunction;

/**
 * Created by wangll13383 on 2015/8/27.
 */
public class InfoFunction extends AbstractTemplateFunction {

    public InfoFunction() {
        super("getInfo");
    }

    public Object execute(Template template, TemplateContext context,
                          Object... parameters) throws TemplateException {
        int id = 0;
        if (parameters[0] == null) {
            throw new TemplateException("信息类别不能为空");
        }
        if (parameters.length > 1 && !StringUtil.isBlank(parameters[1].toString())) {
            id = Integer.valueOf(parameters[1].toString());
        }
        return TypeInfoUtil.getUrlMap(parameters[0].toString(), id);
    }
}
