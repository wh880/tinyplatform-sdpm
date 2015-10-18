package org.tinygroup.sdpm.product.dao.pojo;

import java.io.Serializable;

public class ProductAndLine implements Serializable {
	
	private int productId;
	
	private String productName;
	
	private int productLineId;
	
	private String productLineName;

	/**
	 * 版本id
	 */
	private Integer buildId;
	
	/**
	 * 版本名称
	 */
	private String buildName;

	public Integer getBuildId() {
		return buildId;
	}

	public void setBuildId(Integer buildId) {
		this.buildId = buildId;
	}

	public String getBuildName() {
		return buildName;
	}

	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductLineId() {
		return productLineId;
	}

	public void setProductLineId(int productLineId) {
		this.productLineId = productLineId;
	}

	public String getProductLineName() {
		return productLineName;
	}

	public void setProductLineName(String productLineName) {
		this.productLineName = productLineName;
	}
	
	
}
