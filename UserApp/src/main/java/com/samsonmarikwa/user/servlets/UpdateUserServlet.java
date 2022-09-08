package com.samsonmarikwa.user.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/updateServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(UpdateUserServlet.class);
	private Connection connection;
	
	public void init() {
		logger.info("init() started");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "P@ssW0rd");
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("doPost() started");

		try {
			String emailInput = request.getParameter("email");
			String passwordInput = request.getParameter("password");
			
			Statement statement = connection.createStatement();
			int result = statement.executeUpdate("update user set password = '" + passwordInput + "' where email = '" + emailInput +"'");
			PrintWriter out = response.getWriter();
			out.print("<H1>" + result + " Password Updated</H1>");
			out.close();
		
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}
	
	public void destroy() {
		logger.info("destroy() started");
		try {
			connection.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		
	}

}
