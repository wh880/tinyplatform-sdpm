package org.tinygroup.sdpm.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.docTemplate.inter.DocTemplateResolver;
import org.tinygroup.sdpm.org.biz.inter.UserManager;
import org.tinygroup.sdpm.product.biz.inter.ProductManager;
import org.tinygroup.sdpm.product.biz.inter.ReleaseManger;
import org.tinygroup.sdpm.product.biz.inter.StoryManager;
import org.tinygroup.sdpm.product.dao.pojo.ProductRelease;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.quality.biz.inter.BugManager;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.sdpm.system.service.inter.ExportService;
import org.tinygroup.template.TemplateContext;
import org.tinygroup.template.TemplateEngine;
import org.tinygroup.template.TemplateException;
import org.tinygroup.template.impl.TemplateContextDefault;
import org.tinygroup.template.impl.TemplateEngineDefault;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.vfs.FileObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by wangll13383 on 2015/11/18.
 */
@Component
public class ExportServiceImpl implements ExportService{
    @Autowired
    private DocTemplateResolver docTemplateResolver;
    @Autowired
    private TemplateEngine docTemplateEngine;
    @Autowired
    private ReleaseManger releaseManger;
    @Autowired
    private StoryManager storyManager;
    @Autowired
    private BugManager bugManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private ProductManager productManager;
    public void exportReleaseDoc(HttpServletResponse response, Integer releaseId) throws IOException, TemplateException {
        TemplateContext context = new TemplateContextDefault();
        if(releaseId!=null) {
            ProductRelease release = releaseManger.find(releaseId);
            String releaseStory = release.getReleaseStories();
            List<Integer> sIds = null;
            if(!StringUtil.isBlank(releaseStory)){
                sIds = new ArrayList<Integer>();
                String[] ids = releaseStory.split(",");
                for(String id : ids){
                    sIds.add(Integer.parseInt(id));
                }
            }
            List<ProductStory> stories = new ArrayList<ProductStory>();
            if(sIds.size()>0){
                Integer[] nIds = new Integer[sIds.size()];
                stories = storyManager.findList(sIds.toArray(nIds));
            }
            context.put("storyList",stories);

            QualityBug bug = new QualityBug();
            bug.setBugOpenedBuild(String.valueOf(release.getBuildId()));
            bug.setDeleted(0);
            List<QualityBug> bugAllList = bugManager.findList(bug);
            String releaseBugs = release.getReleaseBugs();
            String[] bugIds = releaseBugs==null?null:releaseBugs.split(",");
            List<QualityBug> inBugList = new ArrayList<QualityBug>();
            List<QualityBug> notInBugList = new ArrayList<QualityBug>();
            if(bugIds!=null&&bugIds.length>0){
                for(String id : bugIds){
                    for(QualityBug bug1 : bugAllList){
                        if(bug1.getBugId().equals(Integer.parseInt(id))){
                            inBugList.add(bug1);
                        }else{
                            notInBugList.add(bug1);
                        }
                    }
                }
            }else{
                notInBugList = bugAllList;
            }
            context.put("bugInList",inBugList);
            context.put("bugNoInList", notInBugList);
            context.put("userManager",userManager);
            context.put("productManager",productManager);
        }
        mergeTemplate(DocTemplateResolver.RELEASE,context,response,"发布文档");
    }

    private void mergeTemplate(String type,TemplateContext context,HttpServletResponse response,String name) throws IOException, TemplateException {
        Calendar today = Calendar.getInstance();
        FileObject fileObject = docTemplateResolver.getDocTemplate(type);
        response.reset();
        response.setContentType("application/msword");
        response.setHeader("Content-Disposition", "attachment; filename="
                + (toUTF8(name)+today.get(Calendar.YEAR)+today.get(Calendar.MONTH)+today.get(Calendar.DAY_OF_MONTH)+today.get(Calendar.HOUR)+today.get(Calendar.MINUTE)+".doc"));
        OutputStream out = response.getOutputStream();
        docTemplateEngine.renderTemplate(fileObject.getFileName(),context,out);
        out.close();
    }

    public String toUTF8(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes("utf-8");
                } catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }
}
