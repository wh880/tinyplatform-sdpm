package org.tinygroup.sdpm.product.biz.impl;

public class FieldUtil {
	public static String stringFormat(String field){
		StringBuffer buffer = new StringBuffer();
		for (char cs : field.toCharArray()) {
			if(cs<=90&&cs>=65){
				cs +=32;
				buffer.append('_');
			}
			buffer.append(cs);
		}
		
		return buffer.toString();
	}
}
