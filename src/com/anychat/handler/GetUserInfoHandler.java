/**
 * create:	Nov 30, 2019
 * author:	nieaowei
 */
package com.anychat.handler;

import com.anychat.commons.ExceptionHandler;
import com.anychat.commons.Result;
import com.anychat.dao.UserDao;
import com.anychat.model.UserModel;

/**
 * @author nieaowei
 *
 */
public class GetUserInfoHandler extends UserModel{
	
	private transient Result result;

	/**
	 * @return the result
	 */

	public Result getResult() {
		return result;
	}

	/**
	 * 
	 */
	public GetUserInfoHandler(String qq) {
		// TODO Auto-generated constructor stub
		super(qq);
		result=getUserInfo();
//		
//		try {
//			FileOutputStream fileOutputStream=new FileOutputStream("/Users/nieaowei/Desktop/AnyChat/1.png");
//			fileOutputStream.write(this.getPhoto());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	private Result getUserInfo() {
		try {
			if (new UserDao().getUserInfo(this)) {
				return new Result(200, "successed",this);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Result(ExceptionHandler.NOKOWN_ERRORS, "no konwn error");
	}
}
