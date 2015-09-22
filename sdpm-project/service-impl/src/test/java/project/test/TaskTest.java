package project.test;

import junit.framework.TestCase;
import org.tinygroup.context.Context;
import org.tinygroup.context.impl.ContextImpl;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import util.ServiceTestUtil;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-22.
 */
public class TaskTest extends TestCase {
    public void testFindList() {
        Context context = new ContextImpl();
        Integer id = 1;
        context.put("id", id);
        ServiceTestUtil.execute("task_findListTask", context);

        List<ProjectTask> list = context.get("tasksList");

    }
}
