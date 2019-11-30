package com.anychat.commons;

import java.util.Properties;

/**
 * 继承  单例模式
 * 加载配置信息
 * @author 40779
 *
 */
public class MyProperties  extends Properties {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static MyProperties instance =new MyProperties();
	//  构造函数私有化
	private MyProperties() {
		//加载日志文件
		//instance.load(new FileInputStream(new File("src/db.properties")));   //相对路径创建
		//类加载器获取信息
						try {
							this.load(MyProperties.class.getClassLoader().getResourceAsStream("db.properties"));
						} catch (Exception e) {
							e.printStackTrace();
						}
	}
	public  static MyProperties  getInstance() {
		return instance;
	}
}
