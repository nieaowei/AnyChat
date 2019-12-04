/**
 * create:	Dec 3, 2019
 * author:	nieaowei
 */
package com.anychat.handler;

import java.util.Map;

import com.anychat.commons.ExceptionHandler;
import com.anychat.commons.Result;
import com.anychat.dao.FriendDao;
import com.anychat.dao.SubGroupDao;
import com.anychat.model.FriendModel;
import com.anychat.model.SubGroupModel;

/**
 * @author nieaowei
 *
 */
public class AddFriendHandler extends FriendModel{
	

	private transient Result result;
	SubGroupModel subGroupModel;
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
		subGroupModel=new SubGroupModel(map);
		System.out.println(subGroupModel.getQq());
		result=addFriend();
	}
	
	private Result addFriend() {
		try {
			if(!new SubGroupDao().findSid(subGroupModel)) {
				return new Result(400,"添加失败，分组不存在");
			}
			this.setSid(subGroupModel.getSid());
			//
			if (new FriendDao().addFriend(this)) {
				return new Result(200,"添加好友成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			if (new ExceptionHandler(e, "违反唯一约束条件").getResult()) {
				return new Result(ExceptionHandler.FRIEND_EXIST,"您已添加该好友");
			}
		}
		return new Result(400,"添加好友失败");
	}
}
