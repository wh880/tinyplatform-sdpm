package org.tinygroup.sdpm.product.dao.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StoryCollection implements Serializable{
	
	private List<ProductStory> productStories;

	public List<ProductStory> getProductStories() {
		return productStories;
	}

	public void setProductStories(List<ProductStory> productStories) {
		this.productStories = productStories;
	}

	public StoryCollection(List<ProductStory> productStories) {
		super();
		this.productStories = productStories;
	}

	public StoryCollection() {
		super();
	}
	
	
	
}
