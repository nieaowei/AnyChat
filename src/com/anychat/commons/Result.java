/**
 * create:	Nov 27, 2019
 * author:	nieaowei
 */
package com.anychat.commons;

/**
 * @author nieaowei
 * 结果集
 */
public class Result {
	private int 	status;//返回此次操作状态
	private String	msg;//附加信息
	private Object	data;//返回的数据
	/**
	 * @param status
	 * @param msg
	 * @param data
	 */
	public Result(int status, String msg, Object data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	/**
	 * 
	 */
	public Result(ExceptionHandler e) {
		// TODO Auto-generated constructor stub
		this.status=e.getStatus(e.getMessage());
		this.msg=e.getMessage();
	}
	
	public Result(int status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}
	
}
