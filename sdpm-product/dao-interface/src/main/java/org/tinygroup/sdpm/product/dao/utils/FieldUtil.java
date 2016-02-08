package org.tinygroup.sdpm.product.dao.utils;

public class FieldUtil {

    public static final int DELETE_NO = 0;
    public static final int DELETE_YES = 1;

    public static final String DELETE_NO_S = "0";
    public static final String DELETE_YES_S = "1";

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
