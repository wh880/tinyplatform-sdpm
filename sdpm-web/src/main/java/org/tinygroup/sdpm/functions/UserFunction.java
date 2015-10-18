package org.tinygroup.sdpm.functions;

import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.sdpm.dict.util.DictUtil;
import org.tinygroup.sdpm.org.service.impl.UserServiceImpl;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.system.service.impl.DictServiceImpl;
import org.tinygroup.sdpm.system.service.inter.DictService;
import org.tinygroup.template.Template;
import org.tinygroup.template.TemplateContext;
import org.tinygroup.template.TemplateException;
import org.tinygroup.template.function.AbstractTemplateFunction;

public class UserFunction extends AbstractTemplateFunction {
	 private static final UserService userService = BeanContainerFactory.getBeanContainer(UserFunction.class.getClassLoader()).getBean(UserServiceImpl.class);

	public UserFunction() {
		super("getUserName");

	}

	public Object execute(Template template, TemplateContext context,
			Object... parameters) throws TemplateException {

		if (parameters.length == 0) {
			return "";
		}else{
			return userService.getNameById((String) parameters[0]);
		}
	}

}
