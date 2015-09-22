package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.project.service.inter.TaskRelationService;

/**
 * Created by wangying14938 on 2015-09-22.用户关系（甘特图）
 */
public class TestrelatitonAction extends BaseController {
    @Autowired
    private TaskRelationService taskRelationService;

}
