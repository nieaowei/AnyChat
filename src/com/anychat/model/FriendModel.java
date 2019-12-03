/**
 * create:	Dec 2, 2019
 * author:	nieaowei
 */
package com.anychat.model;

import java.util.Map;

/**
 * @author nieaowei
 *
 */
public class FriendModel {
	private String fid;
	private String qq;
	private String fqq;
	private String sid;
	private String fstatus;
	private String fip;
	private String fdate;
	
	/**
	 * 
	 */
	public FriendModel(Map<String, String[]> map) {
		// TODO Auto-generated constructor stub
		if (map.get("qq")!=null) {
			this.qq=map.get("qq")[0];
		}
		if (map.get("fid")!=null) {
			this.fid=map.get("fid")[0];
		}
		if (map.get("fqq")!=null) {
			this.fqq=map.get("fqq")[0];
		}
		if (map.get("sid")!=null) {
			this.sid=map.get("sid")[0];
		}
		if (map.get("fstatus")!=null) {
			this.fstatus=map.get("fstatus")[0];
		}
		if (map.get("fip")!=null) {
			this.fip=map.get("fip")[0];
		}
		if (map.get("fdate")!=null) {
			this.fdate=map.get("fdate")[0];
		}
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
	 * @return the fqq
	 */
	public String getFqq() {
		return fqq;
	}
	/**
	 * @param fqq the fqq to set
	 */
	public void setFqq(String fqq) {
		this.fqq = fqq;
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
	 * @return the fstatus
	 */
	public String getFstatus() {
		return fstatus;
	}
	/**
	 * @param fstatus the fstatus to set
	 */
	public void setFstatus(String fstatus) {
		this.fstatus = fstatus;
	}
	/**
	 * @return the fip
	 */
	public String getFip() {
		return fip;
	}
	/**
	 * @param fip the fip to set
	 */
	public void setFip(String fip) {
		this.fip = fip;
	}
	/**
	 * @return the fdate
	 */
	public String getFdate() {
		return fdate;
	}
	/**
	 * @param fdate the fdate to set
	 */
	public void setFdate(String fdate) {
		this.fdate = fdate;
	}
	
	
}
