package org.tinygroup.sdpm.common.util.ComplexSearch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangll13383 on 2015/9/18.
 */
public class SearchInfos implements Serializable{
    private List<SearchInfo> infos = new ArrayList<SearchInfo>();

    public List<SearchInfo> getInfos() {
        return infos;
    }

    public void setInfos(List<SearchInfo> infos) {
        this.infos = infos;
    }
}
