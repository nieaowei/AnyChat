/**
 * create:	Dec 7, 2019
 * author:	nieaowei
 */
package com.anychat.dao;

import java.sql.SQLException;

import com.anychat.commons.Dbhelper;
import com.anychat.model.MessageModel;

/**
 * @author nieaowei
 *
 */
public class MessageDao {
	public boolean addMessage(MessageModel messageModel) throws SQLException {
		String sqString="insert into anychat_message (mid,fid,content) values(seq_mid.nextval,?,?)";
		
		if (new Dbhelper().update(sqString, messageModel.getFid(),messageModel.getContent())!=0) {
			return true;
		}
		return false;
	}
}
