package com.samsonmarikwa.user.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ReadUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(ReadUserServlet.class);
	private Connection connection;
	
	public void init(ServletConfig config) {
		logger.info("init() started");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(
					config.getInitParameter("dbUrl"), config.getInitParameter("dbUser"), config.getInitParameter("dbPassword"));
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("doGet() started");

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from user");
			PrintWriter out = response.getWriter();
			
			out.print("<table>");
			out.print("<tr>");
			out.print("<th>");
			out.print("First Name");
			out.print("</th>");
			out.print("<th>");
			out.print("Last Name");
			out.print("</th>");
			out.print("<th>");
			out.print("Email");
			out.print("</th>");
			out.print("</tr>");
			
			while (resultSet.next()) {
				out.print("<tr>");
				out.print("<td>");
				out.print(resultSet.getString("firstName"));
				out.print("</td>");
				out.print("<td>");
				out.print(resultSet.getString("lastName"));
				out.print("</td>");
				out.print("<td>");
				out.print(resultSet.getString("email"));
				out.print("</td>");
				out.print("</tr>");
			}
			out.print("</table>");
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
