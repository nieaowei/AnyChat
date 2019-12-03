/**
 * create:	Nov 28, 2019
 * author:	nieaowei
 */
package com.anychat.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.anychat.commons.Dbhelper;
import com.anychat.commons.ExceptionHandler;
import com.anychat.model.UserModel;

/**
 * @author nieaowei
 * 该类中所有的异常都抛给handler处理并定位异常
 */
public class UserDao {
	/**
	 * 在数据库用户表中增加一位用户
	 * @param userModel
	 * @return
	 * @throws SQLException 抛出给上层处理
	 * @throws ExceptionHandler 
	 */
	public boolean addUser(UserModel userModel) throws SQLException, ExceptionHandler{
		String sql = "insert into anychat_user ";
		String sql1="(qq";
		String sql2="values(seq_qq.nextval";
		List<Object> para = new ArrayList<>();
		if (userModel.getPwd()!=null) {
			sql1+=",pwd";
			sql2+=",?";
			para.add(userModel.getPwd());
		}else {
			throw new ExceptionHandler("缺少密码");
		}
		if (userModel.getSign()!=null) {
			sql1+=",sign";
			sql2+=",?";
			para.add(userModel.getSign());
		}
		if (userModel.getPhoto()!=null) {
			sql1+=",photo";
			sql2+=",?";
			para.add(userModel.getPhoto());
		}
		if (userModel.getNickname()!=null) {
			sql1+=",nickname";
			sql2+=",?";
			para.add(userModel.getNickname());
		}else {
			throw new ExceptionHandler("缺少昵称");
		}
		if (userModel.getSex()!=null) {
			sql1+=",sex";
			sql2+=",?";
			para.add(userModel.getSex());
		}else {
			throw new ExceptionHandler("缺少性别");
		}
		if (userModel.getBirthday()!=null) {
			sql1+=",birthday";
			sql2+=",to_date(?,'YYYY-MM-DD')";
			para.add(userModel.getBirthday());
		}
		if (userModel.getTelephone()!=null) {
			sql1+=",telephone";
			sql2+=",?";
			para.add(userModel.getTelephone());
		}
		if (userModel.getEmail()!=null) {
			sql1+=",email";
			sql2+=",?";
			para.add(userModel.getEmail());
		}
		if (userModel.getAddr()!=null) {
			sql1+=",addr";
			sql2+=",?";
			para.add(userModel.getAddr());
		}
		if (userModel.getQuestion()!=null) {
			sql1+=",question";
			sql2+=",?";
			para.add(userModel.getQuestion());
		}else {
			throw new ExceptionHandler("缺少问题");
		}
		sql1+=") ";
		sql2+=")";
		sql+=sql1;
		sql+=sql2;
		System.out.println(sql);
		System.out.println(para.toString());
		Object res = new Dbhelper().insert(sql,"qq",para.toArray());
		if(res!=null) {
			userModel.setQq(res.toString());
			return true;
		}
		return false;
	}
	/**
	 * 验证账号密码，
	 * @param userModel
	 * @return 在数据库中查询到，则返回true，否则false
	 * @throws Exception 异常抛出给上层定位错误
	 */
	public boolean verifyUser(UserModel userModel) throws Exception {
		if (userModel.getQq()==null) {
			throw new ExceptionHandler("账号为空");
		}
		if (userModel.getPwd()==null) {
			throw new ExceptionHandler("密码为空");
		}
		String sql = "select qq from anychat_user where QQ=? and PWD=?";
		ArrayList<Object> params=new ArrayList<>();
		params.add(userModel.getQq());
		params.add(userModel.getPwd());
		if (new Dbhelper().findSingle(sql, params)!=null) {
			return true;
		}
		return false;
	}
	
	public boolean updateStatus(UserModel userModel) throws SQLException {
		String sql = "update anychat_user set status=? , ip=? where qq=?";
		
		if (new Dbhelper().update(sql, userModel.getStatus(),userModel.getIp(),userModel.getQq())!=0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 通过qq查询个人信息 查看个人信息   在个人信息页面显示全部资料  不显示密码 
	 * @param qq  这个参数，在用户点击查看个人资料按钮时获取，并传送给这个方法
	 * 				可以在用户登录成功之后，把用户的密码和账号记录下来
	 * @return null:		is fail,have not data.
	 * @return not null:	success and have data.
	 * @throws Exception 抛出给上层处理
	 */
	public boolean getUserInfo(UserModel userModel) throws Exception{
		String sql = "select qq,sign,photo,nickname,sex,birthday,telephone,email,addr from anychat_user where qq = ?";
		List<Object> params=new ArrayList<>();
		params.add(userModel.getQq());
		Map<String, Object> res = new Dbhelper().findSingle(sql, params);
		if (res!=null) {//if result have not data.
			System.out.println(res.toString());
			userModel.resetUserModel(res);
			System.out.println(userModel.getAddr());
			return true;
		}
		return false;
	}
	
	/**
	 * 不定参数修改个人信息
	 * @param params  修改的内容    未修改的信息也要传入 ，覆盖原来的
	 * @return true
	 * @return false
	 * @throws SQLException 抛出给上层处理
	 */
	public boolean updatePersonal(UserModel userModel) throws SQLException {
		String sql = "update anychat_user set ";
		List<Object> params=new ArrayList<>();
		if (userModel.getSign()!=null) {
			sql+="sign = ?,";
			params.add(userModel.getSign());
		}
		if (userModel.getPhoto()!=null) {
			sql+="photo = ?,";
			params.add(userModel.getPhoto());
		}
		if (userModel.getNickname()!=null) {
			sql+="nickname = ?,";
			params.add(userModel.getNickname());
		}
		if (userModel.getSex()!=null) {
			sql+="sex = ?,";
			params.add(userModel.getSex());
		}
		if (userModel.getBirthday()!=null) {
			sql+="birthday = ?,";
			params.add(userModel.getBirthday());
		}
		if (userModel.getTelephone()!=null) {
			sql+="telephone = ?,";
			params.add(userModel.getTelephone());
		}
		if (userModel.getEmail()!=null) {
			sql+="email = ?,";
			params.add(userModel.getEmail());
		}
		if (userModel.getAddr()!=null) {
			sql+="addr = ?";
			params.add(userModel.getAddr());
		}
		sql+=" where qq = ?";
		params.add(userModel.getQq());
		if (new Dbhelper().update(sql, params.toArray())!=0) {
			return true;
		}
		return false;
	}
	
}
