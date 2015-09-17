package org.tinygroup.sdpm.product.service;

import java.io.Serializable;
import java.util.List;

public class PagerPojo implements Serializable{
	
	private int totalCount;//总记录数
	
	private int currentPage;//当前页
	
	private int limit;//每页记录数
	
	private int start;//开始记录数
	
	private int totalPages;//总页数
	
	private String field;//排序依据的字段
	
	private String sorting;//sorting_desc为逆序，sorting_asc为倒序
	
	private Object object;//待定
	
	private Object[] objects;//待定
	
	private static final int DEFAULT_LIMIT=10;
	
	/*public PagerPojo(int currentPage,int limit){
		this.currentPage = currentPage;
		this.limit = limit;
	}*/
	
	public PagerPojo(int totalCount,int start){
		this(totalCount, start, DEFAULT_LIMIT);
	}
	
	public PagerPojo(int totalCount,int start,String field,String sorting){
		this(totalCount, start, DEFAULT_LIMIT);
		this.field = field;
		this.sorting = sorting;
	}
	
    public PagerPojo(int totalCount,int start,int limit){
		init(totalCount, start, limit);
	}
    
    private void init(int totalCount, int start, int limit){
        //设置基本参数
        this.totalCount=totalCount;
        if(limit==0){
        	limit=DEFAULT_LIMIT;
        }
        if(start<0){//如果传人参数是小于0，那么设置为0
        	start=0;
        }
        this.limit=limit;
        this.start=start;
        this.totalPages=totalCount%limit==0?totalCount/limit:totalCount/limit+1;
        this.currentPage=start/limit==0?1:start/limit+1;
        if(currentPage>totalPages){
        	currentPage=totalPages;
        }
    }

	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getSorting() {
		return sorting;
	}

	public void setSorting(String sorting) {
		this.sorting = sorting;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public Object[] getObjects() {
		return objects;
	}

	public void setObjects(Object[] objects) {
		this.objects = objects;
	}
}
