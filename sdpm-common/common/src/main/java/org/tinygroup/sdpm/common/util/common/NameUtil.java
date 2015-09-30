package org.tinygroup.sdpm.common.util.common;

/**
 * Created by wangll13383 on 2015/9/30.
 */
public class NameUtil {
    public static String resolveNameDesc(String name){
        if(!name.contains("_")){
            char[] n = name.toCharArray();
            StringBuffer result = new StringBuffer();
            for(char c :n){
                if(c>=65&&c<=97){
                    result.append("_").append((char)(c+32));
                }else{
                    result.append((c));
                }

            }
            return result.toString();
        }
        return name;
    }

    public static String toMethod(String fieldName){
        char[] chars = fieldName.toCharArray();
        chars[0] =(char)(chars[0]-32);
        return "get"+String.valueOf(chars);
    }

    public static String resolveNameAsc(String name){
        if(name.contains("_")){
            String[] names = name.split("_");
            StringBuffer result = new StringBuffer().append(names[0]);
            for(int i = 1; i < names.length; i++){
                char[] c = names[i].toCharArray();
                c[0] = (char)(c[0]-32);
                result.append(String.valueOf(c));
            }
            return result.toString();
        }
        return name;
    }
}
