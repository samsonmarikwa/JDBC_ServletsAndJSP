package com.samsonmarikwa.preparedstatement;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/UpdateProductServlet")
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	PreparedStatement stmt;
	
	public void init() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			ServletContext context = getServletContext();
			String dbUrl = context.getInitParameter("dbUrl");
			String dbUser = context.getInitParameter("dbUser");
			String dbPassword = context.getInitParameter("dbPassword");
			con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			
			stmt = con.prepareStatement("update product set name = ?, description = ?, price = ? where id = ?");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		int price = Integer.parseInt(request.getParameter("price"));
		
		try {	
			stmt.setString(1, name);
			stmt.setString(2, description);
			stmt.setInt(3, price);
			stmt.setInt(4, id);
			
			int result = stmt.executeUpdate();
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("<b>"+result+" Product(s) updated</b>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void destroy() {
		try {
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
