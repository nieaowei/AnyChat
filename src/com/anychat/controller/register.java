package com.anychat.controller;


import java.io.IOException;
import java.io.InputStream;


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
		//普通参数获取 
		String pwd=request.getParameter("pwd");
		 String sign=request.getParameter("sign");;
		 String nickname=request.getParameter("nickname");;
		 String sex=request.getParameter("sex");;
		 String birthday=request.getParameter("birthday");;
		 String telephone=request.getParameter("telephone");;
		 String email=request.getParameter("email");;
		 String addr=request.getParameter("addr");;
		 String temp=request.getParameter("temp");;
		 //照片获取
		Part part = request.getPart("file");
		
		InputStream inputStream = part.getInputStream();
		byte[] photo=new byte[(int) part.getSize()];//最大10m
//		inputStream.read(photo, 0, (int) part.getSize());
		inputStream.read(photo);
		inputStream.close();
		response.setCharacterEncoding("UTF-8");
		response.getOutputStream().write(JSON.toJSONString(new RegisterHandler(pwd, sign, photo, nickname, sex, birthday, telephone, email, addr, temp).getResult()).getBytes("UTF-8"));
		//response.getWriter().print();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
