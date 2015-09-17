package org.tinygroup.sdpm.product.service;

import java.io.Serializable;
import java.util.List;

import org.tinygroup.tinysqldsl.Pager;

public class PagerSort<T> extends PagerPojo implements Serializable{
	
	private List<T> records;//当前页的记录
	private String field;//排序依据的字段
	private String sorting;//sorting_desc为逆序，sorting_asc为倒序
	private Object object;//待定
	private Object[] objects;//待定
	
	
	public PagerSort(int totalCount, int start, List<T> records, String field,String sorting) {
		super(totalCount, start);
		this.records = records;
		this.field = field;
		this.sorting = sorting;
		
	}
	
	public PagerSort(int totalCount, int start,int limit, List<T> records, String field,String sorting) {
		super(totalCount, start, limit);
		this.records = records;
		this.field = field;
		this.sorting = sorting;
		
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
