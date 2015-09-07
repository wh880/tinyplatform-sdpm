package org.tinygroup.typeinfo;

import com.thoughtworks.xstream.XStream;
import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.vfs.FileObject;
import org.tinygroup.vfs.VFS;
import org.tinygroup.xstream.XStreamFactory;

import java.util.*;

public class TypeInfoUtil {
	private final static String TYPE_INFOS_FILE_EXT = "typeinfo";
	
	private static List<FileObject> typeInfosFile = new ArrayList<FileObject>();
	
	private static Map<String, TypeInfo> typeDict;

	private static Map<String,String> result = new LinkedHashMap<String, String>();
	static{
		init();
	}

	public static Map getUrlMap(String type,int id){
		result.clear();
		if(typeDict.get(type)!=null) {
			for (Info info : typeDict.get(type).getInfos()) {
				if(typeDict.get(info.getUrlResource())!=null)
				result.put(info.getInfoTile(), formatUrl(info.getInfoUrl(), info.getInfoParameter(), id));
			}
		}
		return result;
	}

	public static String formatUrl(String url,String parameter, int id){
		if(parameter != null&&!"".equals(parameter)){
					url = url+"?"+parameter+"="+id;
				}else{
					url = url;
				}
		return url;
	}

	private static void init(){
		TypeInfoResolvor resolvor = (TypeInfoResolvor)BeanContainerFactory.getBeanContainer(TypeInfoUtil.class.getClassLoader()).getBean("typeInfoResolvor");
		typeDict = resolvor.getDict();
	}
	
//	private static void resolveFileDir(FileObject file){
//		if(file.isFolder()){
//			for(FileObject f:file.getChildren()){
//				resolveFileDir(f);
//			}
//		}else{
//			if(file.getExtName().endsWith(TYPE_INFOS_FILE_EXT)){
//				typeInfosFile.add(file);
//			}
//		}
//	}
	
//	private static void resolverTypeInfosFiles(){
//		for(FileObject file : typeInfosFile){
//			XStream xstream = XStreamFactory.getXStream();
//			xstream.processAnnotations(new Class[]{TypeInfos.class});
//			TypeInfos typeinfos = (TypeInfos) xstream.fromXML(file.getInputStream());
//			for(TypeInfo typeinfo :typeinfos.getTypeInfoList()){
//				typeDict.put(typeinfo.getTypesName(), typeinfo);
//			}
//
//		}
//	}

//	private static void resolverNote(List<TypeInfo> typeinfos){
//
//	}
}
