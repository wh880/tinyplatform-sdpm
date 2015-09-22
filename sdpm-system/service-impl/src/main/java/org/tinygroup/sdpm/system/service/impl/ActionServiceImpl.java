package org.tinygroup.sdpm.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.system.biz.inter.ActionManager;
import org.tinygroup.sdpm.system.dao.pojo.Action;
import org.tinygroup.sdpm.system.service.inter.ActionService;
@Component
public class ActionServiceImpl implements ActionService {
	@Autowired
    private ActionManager actionManager;
	public Action add(Action action) {
		// TODO Auto-generated method stub
		return actionManager.add(action);
	}

	public Action updata(Action action) {
		// TODO Auto-generated method stub
		
		return actionManager.updata(action);
	}

	public Integer delete(Action action) {
		// TODO Auto-generated method stub
		return actionManager.delete(action);
	}

	public List<Action> find(Action action) {
		// TODO Auto-generated method stub
		return actionManager.find(action);
	}

}
