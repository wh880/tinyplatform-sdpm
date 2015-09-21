package org.tinygroup.sdpm.common.dao;

import org.tinygroup.sdpm.common.util.IdGen;
import org.tinygroup.tinysqldsl.KeyGenerator;
import org.tinygroup.tinysqldsl.base.InsertContext;

/**
 * Created by Hulk on 2015/9/21.
 */
public class StringIdGenerator implements KeyGenerator {
    public <T> T generate(InsertContext insertContext) {
        return (T) IdGen.uuid();
    }
}
