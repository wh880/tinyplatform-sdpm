package dict;

import dict.impl.FileScannerImpl;
import dict.inter.FileScanner;

/**
 * Created by wangll13383 on 2015/9/17.
 */
public class DictUtil {
    public DictUtil(){

    }
    public static void main(String[] args){
        FileScannerImpl f = new FileScannerImpl();
        f.process();
    }

}
