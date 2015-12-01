package org.tinygroup.sdpm.util;

import org.tinygroup.sdpm.document.dao.pojo.DocumentDocLib;
import org.tinygroup.sdpm.document.service.impl.DocServiceImpl;
import org.tinygroup.sdpm.document.service.inter.DocService;

import java.util.List;

/**
 * 常用数据获取工具集
 * Created by Hulk on 2015/10/21.
 */
public class CmsUtils {
    private static final String CMS_CACHE = "cmsCache";
    private static final String CMS_CACHE_DOC_LIB_LIST = "docLibList";


    private static DocService docService = SpringContextHolder.getBean(DocServiceImpl.class);

    /**
     * 获得文档库
     */
    public static DocumentDocLib getDocLib(String libId) {
        List<DocumentDocLib> libList = getDocLibList();
        if (libId == null && libList != null && !libList.isEmpty()) {
            return libList.get(0);
        }
        for (DocumentDocLib docLib : libList) {
            if (docLib.getDocLibId().toString().equals(libId)) {
                return docLib;
            }
        }
        return new DocumentDocLib();
    }

    /**
     * 获得文档库列表
     */
    public static List<DocumentDocLib> getDocLibList() {
        List<DocumentDocLib> libList = (List<DocumentDocLib>) CacheUtils.get(CMS_CACHE, CMS_CACHE_DOC_LIB_LIST);
        if (libList == null) {
            libList = docService.findDoclibList(null);
            CacheUtils.put(CMS_CACHE, CMS_CACHE_DOC_LIB_LIST, libList);
        }
        return libList;
    }

    /**
     * 清除文档库列表
     */
    public static void removeDocLibList() {
        CacheUtils.remove(CMS_CACHE, CMS_CACHE_DOC_LIB_LIST);
    }

}
