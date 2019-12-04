/**
 * create:	Dec 3, 2019
 * author:	nieaowei
 */
package com.anychat.model;

import java.util.Map;

/**
 * @author nieaowei
 *
 */
public class SubGroupModel {
	private String sid;
	private String qq;
	private String sname;
	private String created;
	/**
	 * @param map
	 */
	public SubGroupModel(Map<String, String[]> map) {
		// TODO Auto-generated constructor stub
		if (map.get("sid")!=null) {
			this.sid=map.get("sid")[0];
		}
		if (map.get("qq")!=null) {
			this.qq=map.get("qq")[0];
		}
		if (map.get("sname")!=null) {
			this.sname=map.get("sname")[0];
		}
		if (map.get("created")!=null) {
			this.created=map.get("created")[0];
		}
	}
	/**
	 * @return the sid
	 */
	public String getSid() {
		return sid;
	}
	/**
	 * @param sid the sid to set
	 */
	public void setSid(String sid) {
		this.sid = sid;
	}
	/**
	 * @return the qq
	 */
	public String getQq() {
		return qq;
	}
	/**
	 * @param qq the qq to set
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}
	/**
	 * @return the sname
	 */
	public String getSname() {
		return sname;
	}
	/**
	 * @param sname the sname to set
	 */
	public void setSname(String sname) {
		this.sname = sname;
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
