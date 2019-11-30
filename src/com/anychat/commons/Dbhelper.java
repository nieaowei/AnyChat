package com.anychat.commons;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.sql.BLOB;


/* 
 * 数据库帮助类
 */
public class Dbhelper {
//加载驱动
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	static {

		try {
			Class.forName(MyProperties.getInstance().getProperty("driverName")); // 加载驱动 因为只需执行一次 所以放在静态块中 静态块首先执行（再加载JVM时）
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 获取连接对象
	 */
	public Connection getConn() throws SQLException {
		Connection conn = DriverManager.getConnection(MyProperties.getInstance().getProperty("url"),MyProperties.getInstance());
		return conn;
	}

	/*
	 * 关闭所有连接
	 * 
	 */
	public void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		if (null != rs) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (null != pstmt) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (null != conn) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * * 单条更新语句 增删改操作 sql 语句 params 插入的参数 不定长数组
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public int update(String sql, Object... params) throws SQLException {
		int result = 0;
		try {
			conn = this.getConn(); // 获取连接
			pstmt = conn.prepareStatement(sql);
			setParamsObject(pstmt, params); // 设置参数 这里的pstmt是连接对象 params是不定长数组 用于赋予？值
			// 执行更新操作
			result = pstmt.executeUpdate();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		return result;
	}
	/**
	 * 插入并返回指定键值
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public Object insert(String sql,String keyValue ,Object... params) throws SQLException {
		int result = 0;
		Object reString = null;
		try {
			conn = this.getConn(); // 获取连接
			String generatedColumns[] = { keyValue };
			pstmt = conn.prepareStatement(sql,generatedColumns);
			setParamsObject(pstmt, params); // 设置参数 这里的pstmt是连接对象 params是不定长数组 用于赋予？值
			// 执行更新操作
			result = pstmt.executeUpdate();
			if (result!=0) {
				ResultSet resultSet=pstmt.getGeneratedKeys();
				while (resultSet.next()) {
					System.out.println(resultSet.getString(1));
					return resultSet.getString(1);
				}
			}
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		return reString;
	}
	
	/**
	 * 
	 * @param pstmt
	 * @param params
	 * @throws SQLException
	 */
	private void setParamsObject(PreparedStatement pstmt, Object... params) throws SQLException {
		// 循环数组
		if (null == params || params.length <= 0) {
			return;
		}
		for (int i = 0; i < params.length; i++) {
			pstmt.setObject(i + 1, params[i]); // 这里的setObject方法： 因为不知道我们赋予什么样的值进去所以干脆使用Object
		}
	}

	/**
	 * 单个查询的结果 以键值对的方式存储在Map中，多个Map存在List中。
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> findMutipl(String sql, List<Object> params) throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		try {
			
				conn = this.getConn();
				pstmt = conn.prepareStatement(sql);
				
             setParamsList(pstmt, params);
				rs = pstmt.executeQuery();

			// 获取所有列名
			List<String> columnNames = getColumnNames(rs);
			while (rs.next()) {
				map = new HashMap<String, Object>();
				for (String name : columnNames) {
					Object obj = rs.getObject(name);
					if (null == obj) {
						continue;
					}
					String typeName = obj.getClass().getName(); // 获取类型名
					if ("oracle.sql.BLOB".equals(typeName)) {
						// 说明是图片 存储到map集合中的字节数组
						BLOB blob = (BLOB) rs.getBlob(name);
						InputStream in = blob.getBinaryStream();
						byte[] bt = new byte[(int) blob.length()];
						in.read(bt);
						map.put(name, bt);
					} else {
						map.put(name, obj);
					}
				}
				// 将map添加到list集合
				list.add(map);
			}
		} finally {
			
			this.closeAll(conn, pstmt, rs);
		}
		return list;
	}

	/**
	 * 查询语句 最多只返回一条数据 sql 查询语句 params 所需参数List<>
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> findSingle(String sql, List<Object> params) throws Exception {
		Map<String, Object> map = null;
		try {
			// 获取连接对象
			conn = this.getConn();
			// 获取预编译对象
			pstmt = conn.prepareStatement(sql);
			// 设置参数
			setParamsList(pstmt, params);
			// 执行查询，返回结果集对象
			rs = pstmt.executeQuery();
			// 获取所有列的名称
			List<String> columnNames = getColumnNames(rs);

			if (rs.next()) {
				map = new HashMap<String, Object>();
				// 循环所有的列名
				for (String name : columnNames) {
					// 根据字段名获取值
					Object obj = rs.getObject(name); // 获取值 这个地方获取的是 值 不是类型名
					if (null == obj) {
						continue;
					}
					// 获取值的数据类型
					String typeName = obj.getClass().getName();
					if ("oracle.sql.BLOB".equals(typeName)) {
						// 说明是图片 存储到map集合中的字节数组
						BLOB blob = (BLOB) rs.getBlob(name);
						InputStream in = blob.getBinaryStream();
						long length = blob.length();
						byte[] bt = new byte[(int) length];
						in.read(bt);
						map.put(name, bt);
					} else {
						// 字段名作为键
						map.put(name, rs.getObject(name));
					}
				}
			}

		} finally {
			this.closeAll(conn, pstmt, rs);
		}

		return map;
	}

	private List<String> getColumnNames(ResultSet rs) throws SQLException {
		List<String> list = new ArrayList<String>();
		ResultSetMetaData data = rs.getMetaData();
		// 获取总列
		int count = data.getColumnCount();
		for (int i = 1; i <= count; i++) {
			//System.out.println(data.getColumnName(i));
			list.add(data.getColumnName(i));
		}
		return list;
	}

	/*
	 * 设置参数 参数是List
	 * 
	 */
	private void setParamsList(PreparedStatement pstmt, List<Object> params) throws SQLException {
		if (null == params || params.isEmpty()) {
			return;
		}
		for (int i = 0; i < params.size(); i++) {
			pstmt.setObject(i + 1, params.get(i));
		}

	}

	/**
	 * 多条sql语句更新操作 批处理
	 * 
	 * @param sqls
	 * @param params
	 * @return
	 * @throws SQLException
	 */

	public int update(List<String> sqls, List<List<Object>> params) throws SQLException {
		int result = 0;
		try {
			conn = getConn(); // 事务自动提交
			// 事务设置成为手动提交
			conn.setAutoCommit(false);
			// 循环sql语句
			if (null == sqls || sqls.isEmpty()) {
				return result;
			}
			for (int i = 0; i < sqls.size(); i++) {
				// 获取sql语句
				String sql = sqls.get(i);
				// 获取预编译对象
				pstmt = conn.prepareStatement(sql);
				// 设置参数
				setParamsList(pstmt, params.get(i)); // 获取第i个小list设置参数
				result = pstmt.executeUpdate();
				if (result <= 0) { // 此sql语句未执行成功
					conn.rollback(); // 设置事务回滚
					return result;
				}
			}
			// 事务提交
			conn.commit();
		} catch (Exception e) {
			// 当发生异常后 需要对其进行回滚操作
			conn.rollback();
		} finally {
			// 还原conn事务状态
			conn.setAutoCommit(true);
			closeAll(conn, pstmt, null);
		}
		return result;
	}
/**
 *  聚合函数
 * @param sql
 * @param params
 * @return
 * @throws SQLException
 */
	public double getPolymer(String sql, List<Object> params) throws SQLException {
		double result = 0;
		try {
				conn=getConn();
				pstmt=conn.prepareStatement(sql);
				//设置参数
				setParamsList(pstmt, params);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					result=rs.getDouble(1);
				}
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return result;
	}
}
