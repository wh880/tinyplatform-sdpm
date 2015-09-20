package org.tinygroup.sdpm.common.typeinfo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

@XStreamAlias("type-infos")
public class TypeInfos {
	
	@XStreamAsAttribute
	@XStreamAlias("types-name")
	private String typesName;
	@XStreamImplicit
	private List<TypeInfo> typeInfoList;

	public String getTypesName() {
		return typesName;
	}

	public void setTypesName(String typesName) {
		this.typesName = typesName;
	}

	public List<TypeInfo> getTypeInfoList() {
		return typeInfoList;
	}

	public void setTypeInfoList(List<TypeInfo> typeInfoList) {
		this.typeInfoList = typeInfoList;
	}
}
