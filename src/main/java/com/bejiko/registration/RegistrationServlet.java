package com.bejiko.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uname = request.getParameter("name");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		String nmber = request.getParameter("contact");
		RequestDispatcher dispatcher = null;
		Connection con = null;
		
		PrintWriter out = response.getWriter();
		out.print(uname);
		out.print(email);
		out.print(pass);
		out.print(nmber);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalprojectjava" , "root" , "kitasleptopi");
			PreparedStatement pst = con.prepareStatement("insert into users(uname ,nmber , email , pass) values(?,?,?,?) ");
			pst.setString(0, uname);
			pst.setString(1, nmber);
			pst.setString(2, email);
			pst.setString(3, pass);
			
			int rowCount = pst.executeUpdate();
			dispatcher = request.getRequestDispatcher("registration.jsp");
			
			if(rowCount > 0)
			{
				request.setAttribute("status", "success");
				
			}else
			{
				request.setAttribute("status", "failed");
			}
			
			dispatcher.forward(request , response);
			
			
		} catch (Exception e){
			e.printStackTrace();		
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}      
	}
  }
