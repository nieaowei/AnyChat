package com.anychat.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.anychat.handler.LoginHandler;


/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static  Map<String, Socket> Chat=new HashMap<String, Socket>();
	public static  Lock lock=new ReentrantLock();
	public static String CurrentIP=null;
	private ServerSocket serversocket;
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void init() throws ServletException
	{
    	super.init();
		System.out.println("=================hello world=================");
		try {
			serversocket=new ServerSocket(6666);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		new Thread() {
//			
//
//			public void run()
//			{
//				lock.lock();
//				try {
//					serversocket = new ServerSocket(6666);
//					while(true) {
//						lock.lock();
//						Socket socket=serversocket.accept();  //进程会阻塞在这句，直到有socket连接进来，就往下执行
//						
//				        System.out.println("one client has connected1");
//				        System.out.println(socket.getRemoteSocketAddress().toString());
//				        String [] strings=socket.getRemoteSocketAddress().toString().split(":");
//				        String [] strings2=strings[0].split("/");
//				        System.out.println(strings2[1]+strings[1]);
//				        
//				        Chat.put(strings2[1], socket);
//				        
//				        CurrentIP=strings2[1];
//				        lock.unlock();
//					}
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}.start();
		System.out.println("_______________________hello world___________________________");
  }
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> map=request.getParameterMap();
		String ip=request.getRemoteHost();
		System.out.println(request.getRemoteAddr());
		lock.lock();
		Socket socket=serversocket.accept();  //进程会阻塞在这句，直到有socket连接进来，就往下执行
        System.out.println("one client has connected1");
        System.out.println(socket.getRemoteSocketAddress().toString());
        String [] strings=socket.getRemoteSocketAddress().toString().split("/");
//        String [] strings2=strings[0].split("/");
//        System.out.println(strings2[1]+strings[1]);
        Chat.put(strings[1], socket);
        
//        CurrentIP=strings2[1];
		System.out.println(strings[1]);
		response.getOutputStream().write(JSON.toJSONString(new LoginHandler(map,strings[1]).getResult()).getBytes());
		lock.unlock();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
