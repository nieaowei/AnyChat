package com.anychat.controller;

import java.io.IOException;
import java.io.OutputStream;

import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jni.Mmap;

import com.alibaba.fastjson.JSON;
import com.anychat.dao.FriendDao;
import com.anychat.dao.MessageDao;
import com.anychat.model.FriendModel;
import com.anychat.model.MessageModel;

/**
 * Servlet implementation class sendMessage
 */
@WebServlet("/sendMessage")
public class sendMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
//	static final Map<String, Socket> Chat=new HashMap<String, Socket>();
//	Lock lock=new ReentrantLock();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sendMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

//    public void init() throws ServletException
//	{
//    	super.init();
//		System.out.println("=================hello world=================");
//		new Thread() {
//			private ServerSocket serversocket;
//
//			public void run()
//			{
//				try {
//					serversocket = new ServerSocket(6666);
//					while(true) {
//						
//						Socket socket=serversocket.accept();  //进程会阻塞在这句，直到有socket连接进来，就往下执行
//				        System.out.println("one client has connected1");
//				        System.out.println(socket.getRemoteSocketAddress().toString());
//				        String [] strings=socket.getRemoteSocketAddress().toString().split(":");
//				        String [] strings2=strings[0].split("/");
//				        System.out.println(strings2[1]);
//				        lock.lock();
//				        Chat.put(strings2[1], socket);
//				        lock.unlock();
//					}
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}.start();
//		System.out.println("_______________________hello world___________________________");
//  }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		FriendModel friendModel=new FriendModel(request.getParameterMap());
		try {
			if (!new FriendDao().findFidAndFip(friendModel)) {
				return;
			}
			System.out.println(JSON.toJSONString(friendModel));
			String msgString=request.getParameter("content");
			MessageModel messageModel=new MessageModel(friendModel.getFid(),msgString);
			if (!new MessageDao().addMessage(messageModel)) {
				return;
			}
			System.out.println(friendModel.getFip());
			System.out.println(login.Chat.toString());
			OutputStream outputStream =login.Chat.get(friendModel.getFip()).getOutputStream();
//			msgString+="\n";
//			String resultString="{\"qq\":\""+request.getParameter("qq")+"\","+"\"fqq\":\""+request.getParameter("fqq")+"\","+"\"msg\":";
			Map<String, String> mappMap=new HashMap<String, String>();
			mappMap.put("fqq", request.getParameter("qq"));
			mappMap.put("qq", request.getParameter("fqq"));
			mappMap.put("msg", msgString);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			mappMap.put("create", df.format(new Date()));
			System.out.println(mappMap.toString());
			outputStream.write(JSON.toJSONBytes(mappMap));
			outputStream.write("\n".getBytes());
			outputStream.flush();
			response.getWriter().print("helloworld");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
