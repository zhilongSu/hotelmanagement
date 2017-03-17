package com.su.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.su.po.Room;
import com.su.util.DBconnection;

public class RoomDao {
	
	//订房
	public Room registerRoom(int customerId1,String size1,String start,String stop,int money){
		
		Connection conn = null;
		Room room= new Room();
		try{
			conn = DBconnection.getConnection();				
			String sql = "select * from room where size = ? and roomStatus = ?";	
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setString(1, size1);
			ps.setString(2,"空房");			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
			
			int roomId = rs.getInt("roomId");
			
			String sql1 = "insert into t_order(customerId,roomId,startTime,stopTime,money)values(?,?,?,?,?)";
			PreparedStatement ps1 = conn.prepareStatement(sql1);
			ps1.setInt(1, customerId1);
			ps1.setInt(2,roomId);
			ps1.setString(3,start);
			ps1.setString(4,stop);
			ps1.setInt(5,money);
			ps1.executeUpdate();
			ps1.close();	
			
			String sql2 = "select * from room where roomId=? ";	
			PreparedStatement ps2 = conn.prepareStatement(sql2);			
			ps2.setInt(1, rs.getInt("roomId"));
			ResultSet rs1 = ps2.executeQuery();
			if(rs1.next()){
				room.setRoomId(rs1.getInt("roomId"));
				room.setFloor(rs1.getInt("floor"));
				room.setNumber(rs1.getInt("number"));
				room.setSize(rs1.getString("size"));
				room.setPrice(rs1.getInt("price"));
				room.setStatus(rs1.getString("roomStatus"));					
			}
			
			String sql3 = "update room set roomStatus = ? where roomId= ? ";
			PreparedStatement ps3 = conn.prepareStatement(sql3);
			ps3.setString(1, "预订");
			ps3.setInt(2,roomId);
			ps3.executeUpdate();
			ps3.close();	
						
			rs.close();
			rs1.close();
			}
			
		}
		catch (Exception e) {
            e.printStackTrace();
        }finally{       
        	DBconnection.closeConnection(conn);
        }
		return room;
	}
	
	//增加房间
	public Boolean addRoom(int floor1,int number1,String size1,int price,String status1){
		
		Connection conn = null;
		
		try{
			conn = DBconnection.getConnection();		
		
			String sql = "insert into room(floor,number,size,price,roomStatus) values(?,?,?,?,?)";	
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setInt(1,floor1);
			ps.setInt(2,number1);
			ps.setString(3,size1);
			ps.setInt(4,price);
			ps.setString(5,status1);
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
	
	//删除房间
	public Boolean deleteRoom(int floor1,int number1){
		
		Connection conn = null;
		String status = null;
		try{
			conn = DBconnection.getConnection();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		String sql = "select * from room where floor = ? and number = ?";
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,floor1);
			ps.setInt(2, number1);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				 status = rs.getString("roomStatus");
				 if(status=="空房"){
					 String sql1 = "delete from room where floor = ? and number = ?";
					 PreparedStatement ps1 = conn.prepareStatement(sql1);
					 ps1.setInt(1,floor1);
					ps1.setInt(2, number1);
					 ps1.executeUpdate();
					ps1.close();	
				 }
				
			}
			ps.close();
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			DBconnection.closeConnection(conn);
		}
		if(status=="空房"){
			return true;
		}else{
			return false;
		}
		
	}
	
	//修改房间信息
	public Boolean updateRoom(int floor1,int number1,int price1,String status1 ){
		Connection conn = null;
		
		try{
			conn = DBconnection.getConnection();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		try{	
			String sql = "update room set price = ?,roomStatus = ? where floor = ? and number = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, price1);
			ps.setString(2, status1);
			ps.setInt(3, floor1);
			ps.setInt(4, number1);
			ps.executeUpdate();
			ps.close();	
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			DBconnection.closeConnection(conn);
		}
		return true;
	}
	
	//查看房间
	public Room checkRoom(int floor1,int number1){
		
		Connection conn=null;
		Room room1 = new Room();
		
		try {
			conn = DBconnection.getConnection();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String sql = "select * from room where floor = ? and number = ?";
		try {		
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, floor1);
			ps.setInt(2, number1);
			
			ResultSet rs = ps.executeQuery();	
			if(rs.next()){
				room1.setRoomId(rs.getInt("roomId"));
				room1.setFloor(rs.getInt("floor"));
				room1.setNumber(rs.getInt("number"));
				room1.setSize(rs.getString("size"));
				room1.setPrice(rs.getInt("price"));
				room1.setStatus(rs.getString("roomStatus"));					
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBconnection.closeConnection(conn);
		}
		return room1;					
		
	}
	//退房
	public void stopRoom(int floor,int number){
		
		Connection conn = null;
		
		try{
			conn = DBconnection.getConnection();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		try{	
			String sql = "update room set roomStatus = ? where floor = ? and number = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "空房");
			ps.setInt(2, floor);
			ps.setInt(3, number);
			ps.executeUpdate();
			ps.close();	
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			DBconnection.closeConnection(conn);
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
