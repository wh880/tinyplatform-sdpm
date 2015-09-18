package dict.impl;

import org.tinygroup.logger.Logger;
import org.tinygroup.logger.LoggerFactory;
import org.tinygroup.util.dict.inter.CallBackFunction;
import org.tinygroup.util.dict.inter.FileScanner;
import org.tinygroup.vfs.FileObject;

/**
 * Created by wangll13383 on 2015/9/9.
 * 文件扫描抽象类
 */
public abstract class AbstractFileScanner implements FileScanner {

    protected static final Logger LOGGER = LoggerFactory
            .getLogger(AbstractFileScanner.class);

    public void resolverFile(FileObject file, CallBackFunction callBackFunction) {
        if(file.isFolder()&&!file.getFileName().equals("test-classes")){
            for(FileObject f:file.getChildren()){
                resolverFile(f,callBackFunction);
            }
        }else {
            callBackFunction.process(file);
        }
    }

    public void init(){}

}
