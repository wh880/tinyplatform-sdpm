package org.tinygroup.sdpm.system.service.inter;

import org.tinygroup.template.TemplateException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wangll13383 on 2015/11/18.
 */
public interface ExportService {
    void exportReleaseDoc(HttpServletResponse response,Integer releaseId) throws IOException, TemplateException;
}
