/**
 * create:	Nov 27, 2019
 * author:	nieaowei
 */
package com.anychat.main;

import java.io.File;


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
		File file = new File(".");
		System.out.println(file.getAbsolutePath());
		
	}

}
