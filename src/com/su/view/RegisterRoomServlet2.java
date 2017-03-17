package com.su.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.su.dao.RoomDao;
import com.su.po.Customer;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class RegisterRoomServlet2
 */
@WebServlet("/RegisterRoomServlet2")
public class RegisterRoomServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterRoomServlet2() {
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
		int account = jsonobj.getInt("account");
		String size = jsonobj.getString("size");
		String start = jsonobj.getString("start");
		String stop = jsonobj.getString("stop");
		
		int days = Integer.parseInt(stop,10)-Integer.parseInt(start,10);
		int money = days*30;
		
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		int customerid = customer.getCustomerId();
		System.out.print(customerid);
		RoomDao roomdao = new RoomDao();
		JSONArray arr = new JSONArray();
		for(int i =0;i<account;i++){
			arr.add(i, roomdao.registerRoom(customerid, size,start,stop,money));
		}
		
		JSONObject jsonobj2 = new JSONObject();
		jsonobj2.put("arr", arr);
		jsonobj2.put("money", money);
				
		PrintWriter out = response.getWriter();
		out.print(jsonobj2);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
