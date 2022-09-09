package com.samsonmarikwa.isc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/loginServlet", initParams = {
		@WebInitParam(name="dbUrl", value="jdbc:mysql://localhost/mydb"),
		@WebInitParam(name="dbUsername", value="root"),
		@WebInitParam(name="dbPassword", value="P@ssW0rd")
		})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;
	
	public void init(ServletConfig config) {
		try {		
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String dbUrl = config.getInitParameter("dbUrl");
			String dbUsername = config.getInitParameter("dbUsername");
			String dbPassword = config.getInitParameter("dbPassword");
			connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			
			statement = connection.prepareStatement("select * from user where email = ? and password = ?");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		try {
			statement.setString(1, userName);
			statement.setString(2, password);
			resultSet = statement.executeQuery();
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("homeServlet");
			
			if (resultSet.next()) {
				request.setAttribute("message", "Welcome to InterServlet Communication " + userName);
				dispatcher.forward(request, response);
			} else {
				dispatcher = request.getRequestDispatcher("login.html");
				dispatcher.include(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}
	
	public void destroy() {
		try {
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
