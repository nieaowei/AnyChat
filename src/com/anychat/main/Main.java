/**
 * create:	Nov 27, 2019
 * author:	nieaowei
 */
package com.anychat.main;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


/**
 * @author nieaowei
 *
 */
public class Main {
	/**
	 * 
	 */
	public Main() {
	}
	public static void main(String[] args) {
		String string="{\"data\":{\"朋友\":{\"1010\":\"离线\"},\"我的好友\":{\"1008\":\"在线\",\"1007\":\"离线\",\"1006\":\"在线\",\"1009\":\"离线\"}},\"msg\":\"获取成功\",\"status\":200}";
		JSONObject jsonObject = JSON.parseObject(string);
		
		Map<String, Map<String,String>> map = (Map<String, Map<String,  String>>) jsonObject.get("data");
		
		for (Entry<String, Map<String,String>> entry: map.entrySet()) {
//			entry.getKey()
		}
		
		System.out.println(map);
		map.forEach((k,v)->{
//			System.out.println(k+v.toString());
			v.forEach((k1,v1)->{
//				System.out.println(k1+v1);
				System.out.println(k+k1+v1);
			});
		});
	}

}
