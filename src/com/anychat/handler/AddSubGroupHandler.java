/**
 * create:	Dec 4, 2019
 * author:	nieaowei
 */
package com.anychat.handler;

import java.util.Map;

import com.anychat.commons.ExceptionHandler;
import com.anychat.commons.Result;
import com.anychat.dao.SubGroupDao;
import com.anychat.model.SubGroupModel;

/**
 * @author nieaowei
 *
 */
public class AddSubGroupHandler extends SubGroupModel{
	

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
	public AddSubGroupHandler(Map<String, String[]> map) {
		super(map);
		result=addSubGroup();
	}
	
	private Result addSubGroup() {
		try {
			if (new SubGroupDao().addSubGroup(this)) {
				return new Result(200,"增加分组成功");
			}
		} catch (Exception e) {
//			e.printStackTrace();
			if (new ExceptionHandler(e, "违反唯一约束条件").getResult()) {
				return new Result(ExceptionHandler.FRIEND_EXIST,"您已添加该分组");
			}
		}
		return new Result(400,"增加分组失败");
	}
}
