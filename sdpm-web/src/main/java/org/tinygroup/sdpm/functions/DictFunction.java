package org.tinygroup.sdpm.functions;


import org.tinygroup.sdpm.dict.util.DictUtil;
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
            return null;
        }else if(parameters.length<=1){
            return DictUtil.getItemList(parameters[0].toString());
        }else if(parameters.length<=2){
            return DictUtil.getValue(parameters[0].toString(),parameters[1].toString());
        }
        return null;
    }
}
