package org.tinygroup.sdpm.common.util.sql;

import java.util.List;

/**
 * Created by wangll13383 on 2015/9/18.
 */
public class SqlUtil {

    private static boolean isNull = true;

    public static String toSql(List<SearchInfo> searchInfos, String groupOperate){
        StringBuffer sql = new StringBuffer();
        int size = searchInfos.size();
        int nullMark = 0;
        SearchInfo searchInfo;
        int num =(size/3)+ (size%3==0?0:1);
        int total[] = totalFields(searchInfos,num);
        if(isNull){
            return null;
        }
        for(int i = 0;i<num;i++){
            int count = 0;
            if(total[i]!=0){
                for(int j = 0; j<3; j++){
                    searchInfo = searchInfos.get(j+3*i);
                    if(searchInfo.getValue()!=null&&!"".equals(searchInfo.getValue())){
                        count++;
                        if(nullMark == 0){
                            if(j>0 && count -1 < total[i]){
                                sql.append(" "+searchInfo.getRelation());
                            }
                        }
                        if((j+3*i)%3 == 0){
                            if(total[i] == 3){
                                sql.append("((");
                            }else if(total[i] == 2){
                                sql.append("(");
                            }
                            sql.append(seanSearchInfoAppend(searchInfo));
                        }else if((j+3*i)%3 == 1){
                            String end = "";
                            if(!sql.toString().endsWith("(")&&count>1){
                                end = ")";
                            }
                            sql.append(seanSearchInfoAppend(searchInfo)+end);
                        }else{
                            String end = "";
                            if(!sql.toString().endsWith("(")&&count>1){
                                end = ")";
                            }
                            sql.append(seanSearchInfoAppend(searchInfo)+end);
                        }
                        nullMark = 0;
                    }else{
                        if(total[i] -j> 0){
                            nullMark = 1;
                        }
                    }
                    if(count == total[i])break;
                }
            }
            if(i == 0 && total[1]!=0&&total[0]!=0){
                sql.append(" "+groupOperate);
            }
        }

        return sql.toString();
    }

    private static String seanSearchInfoAppend( SearchInfo searchInfo){
        StringBuffer sql = new StringBuffer();
        String operate = searchInfo.getOperate();
        String operateAndValue = null;
        if("include".equals(operate)){
            operateAndValue = " like '%"+searchInfo.getValue()+"%'";
        }else if("between".equals(operate)){
            String[] values = searchInfo.getValue().split(",");
            operateAndValue = " between "+values[0]+" and "+values[1]+" ";
        }else if("noinclude".equals(operate)){
            operateAndValue = " not like '%"+searchInfo.getValue()+"%'";
        }else if("belong".equals(operate)){
            operateAndValue = " in ("+searchInfo.getValue()+")";
        }else{
            operateAndValue =" "+ operate+" "+searchInfo.getValue();
        }
        return sql.append(" "+searchInfo.getField()).append(operateAndValue).toString();
    }

    private static int[] totalFields(List<SearchInfo> searchInfos, int n){
        int result[] = new int[n];
        for(int i = 0;i<n;i++) {
            int count = 0;
            for (int j = 0; j < 3; j++) {
                SearchInfo searchInfo = searchInfos.get(j+3*i);
                if(searchInfo.getValue()!=null&&!"".equals(searchInfo.getValue())){
                    count++;
                }
            }
            if(count != 0){
                isNull = false;
            }
            result[i] = count;
        }
        return result;
    }

}
