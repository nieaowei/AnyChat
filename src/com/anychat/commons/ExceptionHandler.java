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
	public final static int LACK_PHOTO=207;
	public final static int LACK_SEX=208;
	public final static int LACK_NICKNAME=209;
	public final static int LACK_QUSETION=210;
	public final static int FRIEND_EXIST=211;
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
		super(msg);
	}
	/**
	 * 
	 */
	public ExceptionHandler(Exception e,String msg) {
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
		case LACK_NICKNAME:
			return "缺少昵称";
		case LACK_PHOTO:
			return "缺少照片";
		case LACK_PWD:
			return "缺少密码";
		case LACK_SEX:
			return "缺少性别";
		case LACK_QUSETION:
			return "缺少问题";
		case FRIEND_EXIST:
			return "您已添加该好友";
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
		case "缺少照片":
			return LACK_PHOTO;
		case "缺少性别":
			return LACK_SEX;
		case "缺少昵称":
			return LACK_NICKNAME;
		case "缺少问题":
			return LACK_QUSETION;
		case "您已添加该好友":
			return FRIEND_EXIST;
		default:
			return NOKOWN_ERRORS;
		}
	}
}
