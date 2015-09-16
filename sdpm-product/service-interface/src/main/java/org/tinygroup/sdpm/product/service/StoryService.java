package org.tinygroup.sdpm.product.service;

import org.tinygroup.sdpm.product.dao.pojo.Story;

public interface StoryService {
	
	/**
	 * 添加需求
	 * @param story
	 * @return
	 */
	Story add(Story story);
	
	/**
	 * 编辑需求
	 * @param story
	 * @return
	 */
	int edit(Story story);
	
}
