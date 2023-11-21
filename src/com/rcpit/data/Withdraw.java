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
 * Servlet implementation class Withdraw
 */
public class Withdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Withdraw() {
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
	    //int balance;
		int id=Integer.parseInt(request.getParameter("account"));
		int wamount=Integer.parseInt(request.getParameter("withdrawamount"));

		
		try{
			Connection con=ConnectDB.connect();
			PreparedStatement ps1=con.prepareStatement("Select * from users where id=?");
			ps1.setInt(1, id);
			
			ResultSet rs=ps1.executeQuery();
			while(rs.next())
			{
				pbal=rs.getInt("balance");
			}
			if(wamount<=pbal)
			{
			    if(wamount<0)
			    {
			    	response.sendRedirect("404.html");
			    }
			    else
			 
			 pbal = pbal-wamount;
				PreparedStatement ps2=con.prepareStatement("update users set balance=? where id=?");

				ps2.setInt(1,pbal);
				ps2.setInt(2,id);	
				
				int i=ps2.executeUpdate();
				
				if(i>wamount)
				{					
				    //response.sendRedirect("withdraw.html");
				    response.sendRedirect("404.html");
				}
				else
				{
					//response.sendRedirect("404.html");
					response.sendRedirect("withdraw.html");
					
				}
			
			
			
			}
			else
			{
				response.sendRedirect("404.html");
			}
		}
			
		
		
	catch(Exception e)
		{
		System.out.println(e);
		e.printStackTrace();
	     }
       }
	}


