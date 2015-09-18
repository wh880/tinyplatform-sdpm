package org.tinygroup.util.sql;

import java.util.List;

/**
 * Created by wangll13383 on 2015/9/18.
 */
public class SqlUtil {

    public String toSql(List<SearchInfo> searchInfos){
        StringBuffer sql = new StringBuffer();
        SearchInfo searchInfo;
        for(int i = 0 ;i<searchInfos.size();i++){
            searchInfo = searchInfos.get(i);
            if(searchInfo.getField()!=null&&!"".equals(searchInfo.getField())){
                sql.append(searchInfo.getRelation()==null||"".equals(searchInfo.getRelation())?" ":" "+searchInfo.getRelation());
                if(i%3 == 0){
                    sql.append("((").append(seanSearchInfoAppend(searchInfo));
                }else if(i%3 == 1){
                    sql.append(seanSearchInfoAppend(searchInfo)).append(")");
                }else{
                    sql.append(seanSearchInfoAppend(searchInfo)).append(")");
                }
            }
        }
        return sql.toString();
    }

    public String seanSearchInfoAppend( SearchInfo searchInfo){
        StringBuffer sql = new StringBuffer();
        return sql.append(" "+searchInfo.getField()).append(" "+searchInfo.getOperate()).append(" "+searchInfo.getValue()).toString();
    }

}
