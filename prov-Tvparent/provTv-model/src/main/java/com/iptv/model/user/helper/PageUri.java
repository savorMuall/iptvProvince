package com.iptv.model.user.helper;

import java.io.Serializable;

/**
 * 需要权限验证的页面
 *
 */
public class PageUri implements Serializable{

	private static final long serialVersionUID = -5229034218741689105L;

	private int id;
	
	private int page_id;
	
	private int type;
	
	private String uri;
	
	private String create_time;
	
	private String update_time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String createTime) {
		create_time = createTime;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String updateTime) {
		update_time = updateTime;
	}

	public int getPage_id() {
		return page_id;
	}

	public void setPage_id(int pageId) {
		page_id = pageId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
}
