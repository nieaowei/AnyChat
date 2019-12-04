/**
 * create:	Dec 3, 2019
 * author:	nieaowei
 */
package com.anychat.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.anychat.commons.Dbhelper;
import com.anychat.model.SubGroupModel;
import com.anychat.model.UserModel;

/**
 * @author nieaowei
 *
 */
public class SubGroupDao {
	
	public boolean findSid(SubGroupModel subGroupModel) throws Exception {
		String sql = "select sid from anychat_subgroup where qq=? and sname=?";
		List<Object> params=new ArrayList<Object>();
		params.add(subGroupModel.getQq());
		params.add(subGroupModel.getSname());
		System.out.println(subGroupModel.getSname());
		Map<String, Object> map=new Dbhelper().findSingle(sql, params);
		if (map!=null) {
			subGroupModel.setSid(map.get("SID").toString());
			return true;
		}
		return false;
	}
	
	public boolean addSubGroup(SubGroupModel subGroupModel) throws SQLException {
		String sql="insert into anychat_subgroup (sid,qq,sname) values(seq_sid.nextval,?,?)";
		List<Object> params=new ArrayList<Object>();
		params.add(subGroupModel.getQq());
		params.add(subGroupModel.getSname());
		System.out.println(subGroupModel.getQq()+subGroupModel.getSname());
		if (new Dbhelper().update(sql, params.toArray())!=0) {
			return true;
		}
		return false;
	}
	
	public boolean findSubGroupList(UserModel userModel,List<SubGroupModel> list) {
		String sql = "select sname from anychat_subgroup where qq=?";
		List<Object> params=new ArrayList<Object>();
		params.add(userModel.getQq());
		
		return false;
	}
	
}
