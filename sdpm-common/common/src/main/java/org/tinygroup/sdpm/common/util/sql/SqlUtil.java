package org.tinygroup.sdpm.common.util.sql;

import java.util.List;

/**
 * Created by wangll13383 on 2015/9/18.
 */
public class SqlUtil {

    public String toSql(List<SearchInfo> searchInfos){
        StringBuffer sql = new StringBuffer();
        int size = searchInfos.size();
        int nullMark = 0;
        SearchInfo searchInfo;
        int num =(size/3)+ (size%3==0?0:1);
        int total[] = totalFields(searchInfos,num);
        for(int i = 0;i<num;i++){
            int count = 0;
            for(int j = 0 ;j<3;j++){
                count++;
                searchInfo = searchInfos.get(j+3*i);
                if(searchInfo.getField()!=null&&!"".equals(searchInfo.getField())){
                    sql.append(searchInfo.getRelation()==null||"".equals(searchInfo.getRelation())?" ":" "+searchInfo.getRelation());
                    if(nullMark == 1){
                        if(total[i] - j >0){
                            sql.append(" (");
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
            }
        }

        return sql.toString();
    }

    public String seanSearchInfoAppend( SearchInfo searchInfo){
        StringBuffer sql = new StringBuffer();
        return sql.append(" "+searchInfo.getField()).append(" "+searchInfo.getOperate()).append(" "+searchInfo.getValue()).toString();
    }

    public int[] totalFields(List<SearchInfo> searchInfos, int n){
        int result[] = new int[n];
        for(int i = 0;i<n;i++) {
            int count = 0;
            for (int j = 0; j < 3; j++) {
                SearchInfo searchInfo = searchInfos.get(j+3*i);
                if(searchInfo.getField()!=null&&!"".equals(searchInfo.getField())){
                    count++;
                }
            }
            result[i] = count;
        }
        return result;
    }

}
