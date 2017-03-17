package com.su.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.su.dao.CustomerDao;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String json = request.getParameter("json");		
		
		JSONObject jsonobj = JSONObject.fromObject(json);
		String name = jsonobj.getString("name");
		String password = jsonobj.getString("password");
		String phone = jsonobj.getString("phone");
		
		CustomerDao customerdao = new CustomerDao();
		 customerdao.addCustomer(name,password,phone);
		 
		 JSONObject info = new JSONObject();
			
		PrintWriter out  = response.getWriter();
			
		if(customerdao.addCustomer(name,password,phone)){
				
				info.put("success", "success");	
				out.print(info);
				 out.flush();
		}else{
			info.put("success", "dsd");
			out.print(info);
			 out.flush();
			
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
