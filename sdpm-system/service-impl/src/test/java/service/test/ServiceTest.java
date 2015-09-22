package service.test;

import junit.framework.TestCase;
import org.tinygroup.context.Context;
import org.tinygroup.context.impl.ContextImpl;
import org.tinygroup.sdpm.system.dao.pojo.SystemDict;
import org.tinygroup.sdpm.system.service.inter.DictService;
import util.ServiceTestUtil;

/**
 * Created by wangll13383 on 2015/9/22.
 */
public class ServiceTest extends TestCase{
    /**
     * 测试正常用法 对象入参 对象出参
     */
    public void testUserAdd() {
        Context context = new ContextImpl();
        SystemDict dict = new SystemDict();
        dict.setDictKey("id");
        dict.setDictValue("啊啊啊.");
        context.put("dict",dict);
        ServiceTestUtil.execute("addDict", context);

        Object user2 = context.get("");
    }
}
