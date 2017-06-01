package com.demo.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Request {
	private String url;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
