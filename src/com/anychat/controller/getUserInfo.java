package com.anychat.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.anychat.commons.Result;
import com.anychat.handler.GetUserInfoHandler;
import com.anychat.model.UserModel;
import com.sun.org.apache.bcel.internal.generic.NEW;
/**
 * Servlet implementation class getUserInfo
 */
@WebServlet("/getUserInfo")
public class getUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getUserInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String qq=request.getParameter("qq");
		Result result=new GetUserInfoHandler(qq).getResult();
		byte[] resul=JSON.toJSONString(result).getBytes();
		UserModel userModel=(UserModel) result.getData();
		response.setIntHeader("text-lenth", resul.length);
		response.setIntHeader("file-lenth", userModel.getPhoto().length);
		response.getOutputStream().write(resul);//文本数据
		response.getOutputStream().write(userModel.getPhoto());//文件数据

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
