/**
 * create:	Nov 28, 2019
 * author:	nieaowei
 */
package com.anychat.handler;

import java.util.Map;

import com.anychat.commons.ExceptionHandler;
import com.anychat.commons.Result;
import com.anychat.dao.UserDao;
import com.anychat.model.UserModel;

/**
 * @author nieaowei
 *	注册请求处理
 */
public class RegisterHandler extends UserModel{
	private Result result;
	/**
	 * @return the result
	 */
	public Result getResult() {
		return result;
	}
	/**
	 * 注册请求处理
	 * @param pwd
	 * @param sign
	 * @param photo
	 * @param nickname
	 * @param sex
	 * @param birthday
	 * @param telephone
	 * @param email
	 * @param addr
	 * @param tempa
	 */
	public RegisterHandler(String pwd,String sign,byte[] photo,
							String nickname,String sex,String birthday,
							String telephone,String email,String addr,
							String temp) {
		super(pwd, sign, photo, nickname, sex, birthday, telephone, email, addr, temp);
		result = register();
	}
	

	/**
	 * @param map
	 */
	public RegisterHandler(Map<String, String[]> map,byte[] photo) {
		// TODO Auto-generated constructor stub
		super(map,photo);
		result = register();
	}
	public Result register() {
		// TODO 一些判空处理
		try {
			if (new UserDao().addUser(this) ){
				return new Result(200, "注册成功",this.getQq());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new Result((ExceptionHandler) e);
		}
		return new Result(ExceptionHandler.NOKOWN_ERRORS, "未知错误");
	}
}
