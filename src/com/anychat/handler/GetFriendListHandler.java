/**
 * create:	Dec 2, 2019
 * author:	nieaowei
 */
package com.anychat.handler;

import java.util.Map;

import com.anychat.commons.Result;
import com.anychat.dao.FriendDao;
import com.anychat.model.UserModel;


/**
 * @author nieaowei
 *
 */
public class GetFriendListHandler extends UserModel{ 
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
	public GetFriendListHandler(Map<String, String[]> map) {
		super(map);
		result=getFriendList();
	}
	
	private Result getFriendList() {
		try {
			
			Map<String, Map<String, Map<String,String>>> data=new FriendDao().getFriendList(this);
			if (data!=null) {
				return new Result(200,"获取成功",data);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new Result(400, "获取失败");
	}
	
}
