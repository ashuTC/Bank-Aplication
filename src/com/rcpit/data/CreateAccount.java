package com.rcpit.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rcpit.connect.ConnectDB;



/**
 * Servlet implementation class CreateAccount
 */
public class CreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAccount() {
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
	    int id=0;
		String name=request.getParameter("name");
		String mobile=request.getParameter("mobile");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		int balance=Integer.parseInt(request.getParameter("balance"));
        
		if(balance>0)
		{
		try
		{
			Connection con=ConnectDB.connect();
			PreparedStatement ps1=con.prepareStatement("Insert into users values(?,?,?,?,?,?)");
			
			ps1.setInt(1,id);
			ps1.setString(2,name);
			ps1.setString(3,mobile);
			ps1.setString(4,email);
			ps1.setString(5,password);
			ps1.setInt(6, balance);
		
		
			int res =ps1.executeUpdate();
			if(res>0 && balance>0)
			{
				
				System.out.println("Account Created");
				response.sendRedirect("creat.html");
				
				
			}
			else
			{
				response.sendRedirect("404.html");
			}
				
			
					
		}
		catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
		}
		else
		{
			response.sendRedirect("404.html");
		}
	}

}
