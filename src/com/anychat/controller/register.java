package com.anychat.controller;


import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.alibaba.fastjson.JSON;
import com.anychat.handler.RegisterHandler;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
@MultipartConfig(location="/Users/nieaowei/Desktop/AnyChat",maxFileSize=8388608,fileSizeThreshold=819200)
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //照片获取
		System.out.println("rigester...");
		Part part = request.getPart("file");
		Map<String, String[]> map = request.getParameterMap();
		InputStream inputStream = part.getInputStream();
		byte[] photo=new byte[(int) part.getSize()];
		inputStream.read(photo);
		inputStream.close();
//		response.setCharacterEncoding("UTF-8");
		response.getOutputStream().write(JSON.toJSONString(new RegisterHandler(map,photo).getResult()).getBytes());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
