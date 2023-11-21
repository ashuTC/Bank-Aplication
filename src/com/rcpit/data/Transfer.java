package com.rcpit.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rcpit.connect.ConnectDB;



/**
 * Servlet implementation class Transfer
 */
public class Transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transfer() {
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
		int pbal = 0;
        int id = Integer.parseInt(request.getParameter("account1"));
        int ract = Integer.parseInt(request.getParameter("account2"));
        int tamt = Integer.parseInt(request.getParameter("amount"));

        try {
            Connection con = ConnectDB.connect();
            PreparedStatement ps1 = con.prepareStatement("SELECT * FROM users WHERE id=?");
            ps1.setInt(1, id);

            ResultSet rs = ps1.executeQuery();
            if (rs.next()) {
                pbal = rs.getInt("balance");

                if (tamt >= 0 && pbal >= tamt) {
                    // Subtract amount from sender's account
                    pbal = pbal - tamt;
                    PreparedStatement ps2 = con.prepareStatement("UPDATE users SET balance=? WHERE id=?");
                    ps2.setInt(1, pbal);
                    ps2.setInt(2, id);
                    int updateSender = ps2.executeUpdate();

                    // Add amount to receiver's account
                    PreparedStatement ps3 = con.prepareStatement("UPDATE users SET balance=balance + ? WHERE id=?");
                    ps3.setInt(1, tamt);
                    ps3.setInt(2, ract);
                    int updateReceiver = ps3.executeUpdate();

                    if (updateSender > 0 && updateReceiver > 0) {
                        response.sendRedirect("tranfer.html");
                    } else {
                        response.sendRedirect("404.html");
                    }
                } else {
                    response.sendRedirect("404.html");
                }
            } else {
                response.sendRedirect("404.html");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
            response.sendRedirect("404.html");
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
            response.sendRedirect("404.html");
        }
    }
}