package dict.inter;

import org.tinygroup.vfs.FileObject;

/**
 * Created by wangll13383 on 2015/9/9.
 *
 */
public interface FileScanner {

    public void resolverFile(FileObject file, CallBackFunction callBackFunction);

    public void fileProcess();

    public void init();

    public void process();

}
