package org.tinygroup.sdpm.common.util.dict.impl;

import org.tinygroup.logger.Logger;
import org.tinygroup.logger.LoggerFactory;
import org.tinygroup.sdpm.common.util.dict.inter.CallBackFunction;
import org.tinygroup.sdpm.common.util.dict.inter.FileScanner;
import org.tinygroup.vfs.FileObject;

/**
 * Created by wangll13383 on 2015/9/9.
 * 文件扫描抽象类
 */
public abstract class AbstractFileScanner implements FileScanner {

    protected static final Logger LOGGER = LoggerFactory
            .getLogger(AbstractFileScanner.class);

    public void resolverFile(FileObject file, CallBackFunction callBackFunction) {
        if(file.isFolder()&&!file.getFileName().contains("classes")){
            for(FileObject f:file.getChildren()){
                if(file.getFileName().contains(".jar")){
                    if(file.getFileName().matches("sdpm-metadata*\\.jar")){
                        resolverFile(f,callBackFunction);
                    }else{
                        continue;
                    }
                }else {
                    resolverFile(f,callBackFunction);
                }

            }
        }else {
            callBackFunction.process(file);
        }
    }

    public void init(){}

}
