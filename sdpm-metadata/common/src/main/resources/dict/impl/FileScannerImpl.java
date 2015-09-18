package dict.impl;

import com.thoughtworks.xstream.XStream;
import org.tinygroup.database.config.table.Table;
import org.tinygroup.database.config.table.TableField;
import org.tinygroup.database.config.table.Tables;
import org.tinygroup.logger.LogLevel;
import org.tinygroup.metadata.config.stdfield.StandardField;
import org.tinygroup.metadata.config.stdfield.StandardFields;
import org.tinygroup.util.dict.inter.CallBackFunction;
import org.tinygroup.vfs.FileObject;
import org.tinygroup.vfs.VFS;
import org.tinygroup.xstream.XStreamFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangll13383 on 2015/9/11.
 */
public class FileScannerImpl extends AbstractFileScanner {

    private static final String STD_FILE_EXT = ".stdfield";

    private  static final String TABLE_FILE_EXT = ".table";

    private List<FileObject> stdFiles = new ArrayList<FileObject>();

    private Map<String,StandardField> stdMap = new ConcurrentHashMap<String, StandardField>();

    private Map<String,Map<String,String>> tableMap = new ConcurrentHashMap<String, Map<String, String>>();

    private List<FileObject> tableFiles = new ArrayList<FileObject>();

    public void fileProcess() {
       resolverFile(VFS.resolveFile("./"), new CallBackFunction() {
           public void process(FileObject fileObject) {
                if (isMatchSTD(fileObject)){
                    stdFiles.add(fileObject);
                }
                if (isMatchTable(fileObject)){
                    tableFiles.add(fileObject);
                }
           }
       });
    }

    public void process() {
        fileProcess();
        resolverStd();
        resolverTable();
    }

    private void resolverStd(){
        XStream stream = XStreamFactory
                .getXStream();
        for (FileObject fileObject : stdFiles) {
            LOGGER.logMessage(LogLevel.INFO, "正在加载stdField文件[{0}]",
                    fileObject.getAbsolutePath());
            stream.addImmutableType(StandardFields.class);
            StandardFields standardFields = (StandardFields) stream
                    .fromXML(fileObject.getInputStream());
            if(standardFields!=null){
                for(StandardField standardField:standardFields.getStandardFieldList()){
                    stdMap.put(standardField.getId(),standardField);
                }
            }
            LOGGER.logMessage(LogLevel.INFO, "加载stdField文件[{0}]结束",
                    fileObject.getAbsolutePath());
        }
    }

    private void resolverTable(){
        XStream stream = XStreamFactory
                .getXStream();
        for (FileObject fileObject : stdFiles) {
            LOGGER.logMessage(LogLevel.INFO, "正在加载table文件[{0}]",
                    fileObject.getAbsolutePath());
            stream.addImmutableType(Tables.class);
            Tables tables = (Tables) stream
                    .fromXML(fileObject.getInputStream());
            if(tables != null){
                Map<String,String> fieldMap;
                for(Table table : tables.getTableList()){
                    fieldMap = new HashMap<String, String>();
                    for (TableField tableField:table.getFieldList()){
                        StandardField standardField = stdMap.get(tableField.getStandardFieldId());
                        if(standardField!=null){
                            fieldMap.put(standardField.getName(),standardField.getTitle());
                        }
                    }
                    tableMap.put(table.getName(),fieldMap);
                }
            }
            LOGGER.logMessage(LogLevel.INFO, "加载table文件[{0}]结束",
                    fileObject.getAbsolutePath());
        }
    }

    public boolean isMatchSTD(FileObject fileObject) {
        return fileObject.getFileName().endsWith(STD_FILE_EXT);
    }

    public boolean isMatchTable(FileObject fileObject){
        return fileObject.getFileName().endsWith(TABLE_FILE_EXT);
    }

    public static void main(String[] args){
        FileScannerImpl f = new FileScannerImpl();
        f.process();
    }
}
