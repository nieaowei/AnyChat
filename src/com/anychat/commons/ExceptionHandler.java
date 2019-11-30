/**
 * create:	Nov 28, 2019
 * author:	nieaowei
 */
package com.anychat.commons;

/**
 * @author nieaowei
 * 该类用于异常处理
 */
public class ExceptionHandler extends Exception{
	
	private static final long serialVersionUID = 1L;
	private boolean Result;//本次异常解析的结果
	
	//下列为异常代码
	public final static int NOKOWN_ERRORS=100;
	public final static int USER_EXIST=201;
	public final static int USENAME_EMPTY=202;
	public final static int PASSWORD_EMPTY=203;
	public final static int USER_ERROR=204;
	public final static int LACK_PARA=205;//缺少参数
	public final static int LACK_PWD=206;
	/**
	 * @return the result
	 */
	public boolean getResult() {
		return Result;
	}
	/**
	 * 抛出异常
	 */
	public ExceptionHandler(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}
	/**
	 * 
	 */
	public ExceptionHandler(Exception e,String msg) {
		// TODO Auto-generated constructor stub
		Result=compare(e, msg);
	}
	/**
	 * 该私有方法用于分析异常
	 * @param e
	 * @param msg
	 * @return
	 */
	private boolean compare(Exception e,String msg) {
		if (e.getMessage().contains(msg)) {
			return true;
		}
		return false;
	}
	/**
	 * 该方法用于客户端和服务端的异常统一
	 * @param status
	 * @return
	 */
	public final String getMsg(int status) {
		switch (status) {
		case USER_EXIST:
			return "用户已存在";
		case USER_ERROR:
			return "用户账号或密码错误";
		case USENAME_EMPTY:
			return "账号为空";
		case PASSWORD_EMPTY:
			return "密码为空";
		default:
			return "未知错误";
		}
	}
	public final int getStatus(String msg) {
		switch (msg) {
		case "用户已存在":
			return USER_EXIST;
		case "缺少密码":
			return LACK_PWD;
		case "用户账号或密码错误":
			return USER_ERROR;
		case "密码为空":
			return PASSWORD_EMPTY;
		case "账号为空":
			return USENAME_EMPTY;
		default:
			return NOKOWN_ERRORS;
		}
	}
}
