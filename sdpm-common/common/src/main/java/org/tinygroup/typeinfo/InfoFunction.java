package org.tinygroup.typeinfo;

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
		// TODO Auto-generated constructor stub
	}

	public Object execute(Template template, TemplateContext context,
						  Object... parameters) throws TemplateException {
		// TODO Auto-generated method stub
		int id = 0;
		if(parameters.length>1&&parameters[1]!=null&&!"".equals(parameters[1].toString())){
			id = Integer.valueOf(parameters[1].toString());
		}
		return TypeInfoUtil.getUrlMap(parameters[0].toString(),id);
	}
}