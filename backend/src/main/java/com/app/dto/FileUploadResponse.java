package com.app.dto;

public class FileUploadResponse {

	
	private String fileName;
	
	private String contentType;
	
	private String url;
	
	public FileUploadResponse() {
		// TODO Auto-generated constructor stub
		System.out.println("in constructor of "+getClass().getName());
	}

	public FileUploadResponse(String fileName, String contentType, String url) {
		super();
		this.fileName = fileName;
		this.contentType = contentType;
		this.url = url;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}

