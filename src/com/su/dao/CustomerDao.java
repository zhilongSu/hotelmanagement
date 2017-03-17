package com.su.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.su.po.Customer;
import com.su.po.Room;
import com.su.util.DBconnection;

public class CustomerDao {
	
	//用户注册
	public boolean addCustomer(String name,String password,String phone ){
		
		Connection conn = null;
		
		try{
			conn = DBconnection.getConnection();		
		
			String sql = "insert into customer(name,password,phone) values(?,?,?)";	
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setString(1, name);
			ps.setString(2,password);
			ps.setString(3,phone);			
			ps.executeUpdate();			
			ps.close();
		}
		catch (Exception e) {
            e.printStackTrace();
        }finally{       
        	DBconnection.closeConnection(conn);
        }
		return true;
	}
	
	//用户登陆
	public Customer loginCustomer(String name1,String password1){
		
		Connection conn = null;
		Customer customer = new Customer();
		try{
			conn = DBconnection.getConnection();	
			
			String sql = "select * from customer where name = ? and password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setString(1, name1);
			ps.setString(2, password1); 
			ResultSet rs = ps.executeQuery();			
			if(rs.next()){
				customer.setCustomerId(rs.getInt("customerId"));
				customer.setName(name1);
				customer.setPassword(password1);
				
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			DBconnection.closeConnection(conn);
		}
		return customer;
	}
	//查找顾客
	public Room searchCustomer(String name,String phone){
		Connection conn = null;
		Room room = new Room();
		try{
			conn = DBconnection.getConnection();	
			String sql = "select * from customer where name= ? and = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,name);
			ps.setString(2, phone);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				
				String sql1 = "select * from order where customerId =?";
				PreparedStatement ps1 = conn.prepareStatement(sql);
				ps1.setInt(1,rs.getInt("customerId"));
				ResultSet rs1 = ps1.executeQuery();
				
				if(rs1.next()){
					
					String sql2 = "select * from room where roomId =?";
					PreparedStatement ps2 = conn.prepareStatement(sql2);
					ps1.setInt(1,rs.getInt("roomId"));
					ResultSet rs2 = ps1.executeQuery();
					if(rs2.next()){
						
						room.setFloor(rs2.getInt("floor"));
						room.setNumber(rs2.getInt("number"));
						room.setPrice(rs2.getInt("price"));
						room.setStatus(rs2.getString("status"));
						
						
					}
					
					ps.close();
					ps1.close();
					ps2.close();
					
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			DBconnection.closeConnection(conn);
		}
		return room;
	}
	
	
	
	
	
	
	
	
	
	
}
