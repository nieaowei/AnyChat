/**
 * create:	Nov 27, 2019
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
 *
 */
public class LoginHandler extends UserModel{
	
	private Result result;
	
	/**
	 * @return the result
	 */
	public Result getResult() {
		return result;
	}

	public LoginHandler(Map<String, String[]> map,String ip) {
		// TODO Auto-generated constructor stub
		super(map);
		this.setIp(ip);
		this.setStatus("在线");
		result = login();
	}
	
	private Result login() {
		try {
			//验证账号密码
			if (new UserDao().verifyUser(this)) {
				if (new UserDao().updateStatus(this)) {
					return new Result(200, "登录成功",this.getStatus());
				}
			}else {
				//由于dao层已处理空值
				return new Result(ExceptionHandler.USER_ERROR, "账号或密码错误");
			}
			
		} catch (Exception e) {//捕获并定位其他错误
//			return new Result(ExceptionHandler.NOKOWN_ERRORS, "username format is error.");
//			e.printStackTrace()
			return new Result((ExceptionHandler) e);
		}
		//账号密码错误
		return new Result(ExceptionHandler.NOKOWN_ERRORS, "未知错误");
	}
	
}
