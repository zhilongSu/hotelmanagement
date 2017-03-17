package com.su.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.su.dao.RoomDao;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class AddRoomServlet
 */
@WebServlet("/AddRoomServlet")
public class AddRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRoomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("charset=utf-8");//控制请求响应编码格式
		
		String json = request.getParameter("json");  //获取请求头部传递的参数
		JSONObject jsonobj =JSONObject.fromObject(json);	//转化成json格式的对象
		int floor = jsonobj.getInt("floor");
		int number = jsonobj.getInt("number");
		String size = jsonobj.getString("size");
		int price = jsonobj.getInt("price");
		String status = jsonobj.getString("status");
				
		RoomDao roomdao = new RoomDao();		//调用dao层的方法
		Boolean re =  roomdao.addRoom(floor, number, size, price, status);
		
		JSONObject info = new JSONObject();
		PrintWriter out = response.getWriter();		//建造一个响应
		
		if(re){
			info.put("status", "true");			//给响应赋予数据
			out.print(info);
			out.flush();
			out.close();
		} else{
			info.put("status", "fail");
			out.print(info);
			out.flush();
			out.close();
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
