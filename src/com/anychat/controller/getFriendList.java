package com.anychat.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.anychat.handler.GetFriendListHandler;

/**
 * Servlet implementation class getFriendList
 */
@WebServlet("/getFriendList")
public class getFriendList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getFriendList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
//		response.setCharacterEncoding("UTF-8");
//		response.getWriter().print(JSON.toJSONString(new GetFriendListHandler(request.getParameterMap()).getResult()));
		response.getOutputStream().write(JSON.toJSONString(new GetFriendListHandler(request.getParameterMap()).getResult()).getBytes());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
