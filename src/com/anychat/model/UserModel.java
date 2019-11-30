/**
 * create:	Nov 26, 2019
 * author:	nieaowei
 */
package com.anychat.model;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author nieaowei
 *
 */
public class UserModel {
	
	private String qq;
	private String pwd;
	private String sign;
	private byte[] photo;
	private String nickname;
	private String sex;
	private String birthday;
	private String telephone;
	private String email;
	private String addr;
	private String temp;
	
	/**
	 * 
	 */
	public UserModel() {
		// TODO Auto-generated constructor stub
	}
	
	public void resetUserModel(Map<String, Object> daoData) {
			
			this.qq = daoData.get("QQ").toString();
			this.pwd = (String) daoData.get("PWD");
			this.sign = (String) daoData.get("SIGN");
			this.photo = (byte[]) daoData.get("PHOTO");
			this.nickname = (String) daoData.get("NICKNAME");
			this.sex = (String) daoData.get("SEX");
			this.birthday = (String) daoData.get("BIRTHDAY").toString();
			this.telephone = (String) daoData.get("TELEPHONE");
			this.email = (String) daoData.get("EMAIL");
			this.addr = (String) daoData.get("ADDR");
			this.temp = (String) daoData.get("TEMP");
	}
	
	/**
	 * @param qq
	 * @param pwd
	 * @param sign
	 * @param photo
	 * @param nikename
	 * @param sex
	 * @param birthday
	 * @param telephone
	 * @param email
	 * @param addr
	 * @param temp
	 */
	public UserModel(String qq, String pwd, String sign, byte[] photo, String nikename, String sex, String birthday,
			String telephone, String email, String addr, String temp) {
		super();
		this.qq = qq;
		this.pwd = pwd;
		this.sign = sign;
		this.photo = photo;
		this.nickname = nikename;
		this.sex = sex;
		this.birthday = birthday;
		this.telephone = telephone;
		this.email = email;
		this.addr = addr;
		this.temp = temp;
	}
	
	public UserModel(String pwd, String sign, byte[] photo, String nikename, String sex, String birthday,
			String telephone, String email, String addr, String temp) {
		super();
		this.pwd = pwd;
		this.sign = sign;
		this.photo = photo;
		this.nickname = nikename;
		this.sex = sex;
		this.birthday = birthday;
		this.telephone = telephone;
		this.email = email;
		this.addr = addr;
		this.temp = temp;
	}
	/**
	 * 
	 */
	public UserModel(String qq,String pwd) {
		// TODO Auto-generated constructor stub
		this.qq=qq;
		this.pwd=pwd;
	}
	
	public UserModel(Map<String, Object> daoData) {
		JSONObject jsonObject=(JSONObject) JSON.toJSON(daoData);
		UserModel userModel =jsonObject.toJavaObject(UserModel.class);
		this.qq = userModel.qq;
		this.pwd = userModel.pwd;
		this.sign = userModel.sign;
		this.photo = userModel.photo;
		this.nickname = userModel.nickname;
		this.sex = userModel.sex;
		this.birthday = userModel.birthday;
		this.telephone = userModel.telephone;
		this.email = userModel.email;
		this.addr = userModel.addr;
		this.temp = userModel.temp;
	}
	/**
	 * @param qq2
	 */
	public UserModel(String qq) {
		// TODO Auto-generated constructor stub
		this.qq=qq;
	}

	/**
	 * @return the qq
	 */
	public String getQq() {
		return qq;
	}
	/**
	 * @param qq the qq to set
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}
	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}
	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	/**
	 * @return the sign
	 */
	public String getSign() {
		return sign;
	}
	/**
	 * @param sign the sign to set
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}
	/**
	 * @return the photo
	 */
	public byte[] getPhoto() {
		return photo;
	}
	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	/**
	 * @return the nikename
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * @param nikename the nikename to set
	 */
	public void setNikename(String nikename) {
		this.nickname = nikename;
	}
	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the addr
	 */
	public String getAddr() {
		return addr;
	}
	/**
	 * @param addr the addr to set
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}
	/**
	 * @return the temp
	 */
	public String getTemp() {
		return temp;
	}
	/**
	 * @param temp the temp to set
	 */
	public void setTemp(String temp) {
		this.temp = temp;
	}
	
	
	
	
	
	
	
}
