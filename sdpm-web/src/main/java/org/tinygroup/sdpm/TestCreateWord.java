package org.tinygroup.sdpm;

import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.template.TemplateContext;
import org.tinygroup.template.TemplateEngine;
import org.tinygroup.template.TemplateException;
import org.tinygroup.template.impl.TemplateContextDefault;
import org.tinygroup.template.impl.TemplateEngineDefault;
import org.tinygroup.template.loader.FileObjectResourceLoader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangll13383 on 2015/11/18.
 */
public class TestCreateWord {
    public static  void main(String[] args) throws TemplateException, IOException {
        List<QualityBug> bugList = new ArrayList<QualityBug>();
        for(int i =0;i<10;i++){
            QualityBug bug = new QualityBug();
            bug.setProductName("product"+i);
            bug.setBugTitle("bugTitle"+i);
            bugList.add(bug);
        }
        List<ProductStory> storyList = new ArrayList<ProductStory>();
        for(int i =0;i<10;i++){
            ProductStory story = new ProductStory();
            story.setProductName("product"+i);
            story.setStoryTitle("storyTitle" + i);
            storyList.add(story);
        }
        final TemplateEngine engine = new TemplateEngineDefault();
        FileObjectResourceLoader tinySample = new FileObjectResourceLoader("xml", "layout", null, "src/main/resources");
        engine.addResourceLoader(tinySample);
        TemplateContext context = new TemplateContextDefault();
        context.put("bugList",bugList);
        context.put("storyList",storyList);
        File file = new File("D:/java/eclipseworkspace/sdpm/sdpm-web/src/main/resources/releaseDoc.doc");
        file.createNewFile();
        OutputStream out = new FileOutputStream(file);
        engine.renderTemplate("release.doc.xml",context,out);
        out.close();
    }
}
