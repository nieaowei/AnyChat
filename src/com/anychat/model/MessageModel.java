/**
 * create:	Dec 7, 2019
 * author:	nieaowei
 */
package com.anychat.model;

import java.util.Map;

/**
 * @author nieaowei
 *
 */
public class MessageModel {
	private String mid;
	private String fid;
	private String content;
	private String created;
	/**
	 * 
	 */
	public MessageModel(Map<String, String[]> map) {
		// TODO Auto-generated constructor stub
		if (map.get("mid")!=null) {
			this.mid=map.get("mid")[0];
		}
		if (map.get("fid")!=null) {
			this.fid=map.get("fid")[0];
		}
		if (map.get("content")!=null) {
			this.content=map.get("content")[0];
		}
		if (map.get("created")!=null) {
			this.content=map.get("created")[0];
		}
	}
	/**
	 * @param fid2
	 * @param msgString
	 */
	public MessageModel(String fid2, String msgString) {
		// TODO Auto-generated constructor stub
		this.fid=fid2;
		this.content=msgString;
	}
	/**
	 * @return the mid
	 */
	public String getMid() {
		return mid;
	}
	/**
	 * @param mid the mid to set
	 */
	public void setMid(String mid) {
		this.mid = mid;
	}
	/**
	 * @return the fid
	 */
	public String getFid() {
		return fid;
	}
	/**
	 * @param fid the fid to set
	 */
	public void setFid(String fid) {
		this.fid = fid;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the created
	 */
	public String getCreated() {
		return created;
	}
	/**
	 * @param created the created to set
	 */
	public void setCreated(String created) {
		this.created = created;
	}
	
	
}
