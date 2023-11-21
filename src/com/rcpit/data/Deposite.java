package com.rcpit.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rcpit.connect.ConnectDB;



/**
 * Servlet implementation class Deposite
 */
public class Deposite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deposite() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		int pbal=0;
		int balance;
		int id=Integer.parseInt(request.getParameter("account"));
		int amount=Integer.parseInt(request.getParameter("depositamount"));		

		try{
			Connection con=ConnectDB.connect();
			PreparedStatement ps1=con.prepareStatement("Select * from users where id=?");
			ps1.setInt(1, id);
			ResultSet rs=ps1.executeQuery();
			try
			{
			while(rs.next()){
				pbal=rs.getInt("balance");
				
			}
			if(amount<0)
			{
				response.sendRedirect("404.html");
			}
			else{
			balance=pbal+amount;
			PreparedStatement ps2=con.prepareStatement("update users set balance=? where id=?");
			ps2.setInt(1,balance);
			ps2.setInt(2,id);
			int res=ps2.executeUpdate();
			if(res>0){
				System.out.println("Amount deposited");
				response.sendRedirect("deposite.html");
			}
			
			else
			{
				System.out.println("Failed");
				response.sendRedirect("404.html");
			}
			
			}
			}
		catch(Exception e){
			e.printStackTrace();
		}
		}
		
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
