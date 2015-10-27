package org.tinygroup.sdpm.product.dao.pojo;

import java.io.Serializable;

public class ProductAndLine implements Serializable {
	
	private Integer productId;
	
	private String productName;
	
	private Integer productLineId;
	
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

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductLineId() {
		return productLineId;
	}

	public void setProductLineId(Integer productLineId) {
		this.productLineId = productLineId;
	}

	public String getProductLineName() {
		return productLineName;
	}

	public void setProductLineName(String productLineName) {
		this.productLineName = productLineName;
	}
	
	
}
