package org.tinygroup.sdpm.project.dao.impl;

public class FieldUtil {
	
	public static final int DELETE_NO = 0;
	public static final int DELETE_YES = 1;


	public static String stringFormat(String field) {
		StringBuffer buffer = new StringBuffer();
		for (char cs : field.toCharArray()) {
			if (cs <= 90 && cs >= 65) {
				cs += 32;
				buffer.append('_');
			}
			buffer.append(cs);
		}

		return buffer.toString();
	}
	

}
