package com.su.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.su.dao.*;
import com.su.po.Customer;


import net.sf.json.JSONObject;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
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
		
		Customer customer = new Customer();
		CustomerDao customerdao = new CustomerDao();
		customer = customerdao.loginCustomer(name,password);
		
		JSONObject info = new JSONObject();
		
		PrintWriter out  = response.getWriter();
		
		request.getSession().setAttribute("customer", customer);
		
		
		if(customer.getName()!=null&&customer.getName().equals("邬莉莉")){
			System.out.println(customer.getName());
			info.put("status", "manager");	
			out.print(info);
			 out.flush();
			 out.close();
		}else if(customer.getName()!=null&&!customer.getName().equals("邬莉莉")){
			info.put("status", "customer");
			out.print(info);
			 out.flush();
			 out.close(); 
		}else{
			info.put("status", "failure");
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
