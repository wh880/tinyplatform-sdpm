package org.tinygroup.sdpm;

import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.template.TemplateException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangll13383 on 2015/11/18.
 */
public class TestCreateWord {
    public static  void main(String[] args) throws TemplateException, IOException {
//        List<QualityBug> bugList = new ArrayList<QualityBug>();
//        for(int i =0;i<10;i++){
//            QualityBug bug = new QualityBug();
//            bug.setProductName("product"+i);
//            bug.setBugTitle("bugTitle"+i);
//            bugList.add(bug);
//        }
//        List<ProductStory> storyList = new ArrayList<ProductStory>();
//        for(int i =0;i<10;i++){
//            ProductStory story = new ProductStory();
//            story.setProductName("product"+i);
//            story.setStoryTitle("storyTitle" + i);
//            storyList.add(story);
//        }
//        final TemplateEngine engine = new TemplateEngineDefault();
//        FileObjectResourceLoader tinySample = new FileObjectResourceLoader("xml", "layout", null, "src/main/resources");
//        engine.addResourceLoader(tinySample);
//        TemplateContext context = new TemplateContextDefault();
//        context.put("bugList",bugList);
//        context.put("storyList",storyList);
//        File file = new File("D:/java/eclipseworkspace/sdpm/sdpm-web/src/main/resources/releaseDoc.doc");
//        file.createNewFile();
//        OutputStream out = new FileOutputStream(file);
//        engine.renderTemplate("release.doc.xml",context,out);
//        out.close();

        List<Product> products = new ArrayList<Product>();
        for(int i =0;i<10;i++){
            Product product = new Product();
            product.setProductName("name"+i);
            products.add(product);
        }

        List<Product> clone = deepCopy(products);
    }

    public static List deepCopy(List src) {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(byteOut);
            out.writeObject(src);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = null;
        List dest = null;
        try {
            in = new ObjectInputStream(byteIn);
            dest = (List) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return dest;
    }
}
