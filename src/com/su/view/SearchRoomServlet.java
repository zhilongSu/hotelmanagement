package com.su.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.su.dao.RoomDao;
import com.su.po.Room;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class SearchRoomServlet
 */
@WebServlet("/SearchRoomServlet")
public class SearchRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchRoomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String json = request.getParameter("json");
		JSONObject jsonobj =JSONObject.fromObject(json);
		int floor = jsonobj.getInt("floor");
		int number = jsonobj.getInt("number");
				
		RoomDao roomdao = new RoomDao();
		Room room = new Room();
		room =  roomdao.checkRoom(floor, number);
		
		PrintWriter out = response.getWriter();
		JSONObject info = new JSONObject();
		
		if(room.getRoomId()!=0){
			JSONObject roomjson = JSONObject.fromObject(room);		
			out.print(roomjson);
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
