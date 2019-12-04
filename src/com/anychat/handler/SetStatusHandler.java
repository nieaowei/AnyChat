/**
 * create:	Dec 4, 2019
 * author:	nieaowei
 */
package com.anychat.handler;

import java.util.Map;

import com.anychat.commons.Result;
import com.anychat.dao.UserDao;
import com.anychat.model.UserModel;

/**
 * @author nieaowei
 *
 */
public class SetStatusHandler extends UserModel{
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
		public SetStatusHandler(Map<String, String[]> map,String ip) {
			// TODO Auto-generated constructor stub
			super(map);
			this.setIp(ip);
			result=setStatus();
		}
		private Result setStatus() {
			try {
				if (new UserDao().updateStatus(this)) {
					return new Result(200,"修改状态成功",this.getStatus());
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				return new Result(400,"请输入正确的状态",this.getStatus());
			}
			return new Result(400,"修改状态失败",this.getStatus());
		}
}
