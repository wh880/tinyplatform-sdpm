package org.tinygroup.sdpm.common.typeinfo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

@XStreamAlias("type-info")
public class TypeInfo {
	@XStreamAsAttribute
	@XStreamAlias("name")
	private String typesName;
	@XStreamAsAttribute
	@XStreamAlias("url")
	private String typeUrl;
	@XStreamAsAttribute
	@XStreamAlias("parameterName")
	private String typeParameter;
	@XStreamImplicit
	private List<Info> infos;

	public List<Info> getInfos() {
		return infos;
	}

	public void setInfos(List<Info> infos) {
		this.infos = infos;
	}

	public String getTypeUrl() {
		return typeUrl;
	}

	public void setTypeUrl(String typeUrl) {
		this.typeUrl = typeUrl;
	}

	public String getTypesName() {
		return typesName;
	}

	public void setTypesName(String typesName) {
		this.typesName = typesName;
	}

	public String getTypeParameter() {
		return typeParameter;
	}

	public void setTypeParameter(String typeParameter) {
		this.typeParameter = typeParameter;
	}

	
	
	
}
