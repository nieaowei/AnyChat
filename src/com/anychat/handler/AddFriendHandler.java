/**
 * create:	Dec 3, 2019
 * author:	nieaowei
 */
package com.anychat.handler;

import java.util.Map;

import com.anychat.commons.ExceptionHandler;
import com.anychat.commons.Result;
import com.anychat.dao.FriendDao;
import com.anychat.model.FriendModel;

/**
 * @author nieaowei
 *
 */
public class AddFriendHandler extends FriendModel{
	

	private transient Result result;

	/**
	 * @return the result
	 */
	public Result getResult() {
		return result;
	}
	
	/**
	 * @param map
	 */
	public AddFriendHandler(Map<String, String[]> map) {
		super(map);
		result=addFriend();
	}
	
	private Result addFriend() {
		try {
			if (new FriendDao().addFriend(this)) {
				return new Result(200,"添加好友成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
			if (new ExceptionHandler(e, "违反唯一约束条件").getResult()) {
				return new Result(ExceptionHandler.FRIEND_EXIST,"您已添加该好友");
			}
		}
		return new Result(400,"添加好友失败");
	}
}
