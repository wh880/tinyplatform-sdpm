package org.tinygroup.sdpm.product.dao.impl;

public class ProductTest {
	/*public static DslSession getDslSession(){
		BasicDataSource source =new BasicDataSource();
		source.setDriverClassName("com.mysql.jdbc.Driver");
		source.setUrl("jdbc:mysql://127.0.0.1/sdmp?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf-8");
		source.setUsername("root");
		source.setPassword("root");
		return new SimpleDslSession(source);
	}
	
	@Test
	public void productTest(){

		ProductDaoImpl daoImpl = new ProductDaoImpl();
		daoImpl.setDslSession(getDslSession());
		Product product = new Product();
		product.setProductName("产品1");
		product.setCompanyId(1);
		product.setProductCode("1");
		product.setProductStatus("正常");
		product.setProductCreatedBy("xubin");
		product.setProductCreatedDate(new Date());
		product.setDeleted(Product.DELETE_NO);
		daoImpl.add(product);
	
		
	}
	
	@Test
	public void planTest(){
		ProductPlan plan = new ProductPlan();
		plan.setPlanName("计划1");
		plan.setCompanyId(1);
		plan.setProductId(1);
		plan.setDeleted(0);
		
		ProductPlanDaoImpl daoImpl = new ProductPlanDaoImpl();
		daoImpl.setDslSession(getDslSession());
		daoImpl.add(plan);
	}
	*/
}
