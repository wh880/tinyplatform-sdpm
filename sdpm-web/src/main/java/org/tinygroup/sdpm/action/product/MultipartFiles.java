package org.tinygroup.sdpm.action.product;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class MultipartFiles {
	private List<MultipartFile> multipartFiles;

	public List<MultipartFile> getMultipartFiles() {
		return multipartFiles;
	}

	public void setMultipartFiles(List<MultipartFile> multipartFiles) {
		this.multipartFiles = multipartFiles;
	}
	

}
