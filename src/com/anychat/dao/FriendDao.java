/**
 * create:	Dec 2, 2019
 * author:	nieaowei
 */
package com.anychat.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.anychat.commons.Dbhelper;
import com.anychat.model.FriendModel;
import com.anychat.model.UserModel;

/**
 * @author nieaowei
 *
 */
public class FriendDao {

	public Map<String, Map<String, String>> getFriendList(UserModel userModel) throws Exception {
		String sql="select ANYCHAT_SUBGROUP.SNAME,fqq,fstatus from ANYCHAT_FRIEND,"
				+ "ANYCHAT_SUBGROUP where ANYCHAT_FRIEND.QQ=? and ANYCHAT_FRIEND.SID=ANYCHAT_SUBGROUP.SID";
		List<Object> params=new ArrayList<Object>();
		params.add(userModel.getQq());
		List<Map<String, Object>> result =new Dbhelper().findMutipl(sql, params);
		if (result!=null) {
			Map<String, Map<String, String>> map = new HashMap<>();
			for (Map<String, Object> map1 : result) {
				String[] values=new String[3];
				Map<String, String> temp = new HashMap<>();
				map1.forEach((k,v)->{
					if (k.toString().equals("SNAME")) {
						values[0]=v.toString();
					}else if (k.toString().equals("FQQ")) {
						values[1]=v.toString();
					}else {
						values[2]=v.toString();
					}
				});
				if (map.containsKey(values[0])) {
					map.get(values[0]).put(values[1], values[2]);
				}else {
					temp.put(values[1], values[2]);
					map.put(values[0], temp);
				}
			}	
			return map;
		}
		return null;
	}
	
	public boolean addFriend(FriendModel friendModel) throws Exception {
		String sql="insert into anychat_friend (fid,qq,fqq";
		String sql2=") values(seq_fid.nextval,?,?";
		List<Object> params=new ArrayList<>();
		params.add(friendModel.getQq());
		params.add(friendModel.getFqq());
		if (friendModel.getSid()!=null) {
			sql+=",sid";
			sql2+=",?";
			params.add(friendModel.getSid());
		}
		sql2+=")";
		sql+=sql2;
		System.out.println(sql);
		if (new Dbhelper().update(sql, params.toArray())!=0) {
			return true;
		}
		return false;
	}
}
