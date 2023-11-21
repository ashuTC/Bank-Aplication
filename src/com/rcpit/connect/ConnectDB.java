package com.rcpit.connect;

import java.sql.*;
import java.sql.DriverManager;

public class ConnectDB {
	static Connection con =null;
	public static Connection connect()
	{
		try{
			if(con==null){
				Class.forName("com.mysql.jdbc.Driver");
				
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/aplication","root","");
				
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return con;
	}
}
