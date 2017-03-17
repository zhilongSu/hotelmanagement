package com.su.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
	private static final String Driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/hotel";
	private static final String username = "root";
	private static final String password = "123456";
	private static Connection conn = null;
		
	//连接数据库
	public static Connection getConnection() throws Exception{ 
		
		
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, username, password);
			return conn;
	}
	
	//关闭数据库
	public static void closeConnection(Connection conn){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}


