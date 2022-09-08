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

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(DeleteUserServlet.class);
	private Connection connection;
	private Statement statement;
	private PrintWriter out;

	public void init() {
		logger.info("init() started");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "P@ssW0rd");
		} catch (SQLException | ClassNotFoundException e) {
			logger.error("Exception thrown {}", e.getMessage());
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("doPost() started");
		try {
			statement = connection.createStatement();
			int result = statement
					.executeUpdate("delete from user where email = '" + request.getParameter("email") + "'");

			out = response.getWriter();

			if (result > 0) {
				out.print("<H1>" + result + " Record(s) deleted</H1>");
			} else {
				out.print("<H1>Invalid user</H1>");
			}
			out.close();
		} catch (SQLException e) {
			logger.error("Exception thrown {}", e.getMessage());
			e.printStackTrace();
		}
	}

	public void destroy() {
		logger.info("destroy() started");
		try {

			statement.close();
			connection.close();
		} catch (SQLException e) {
			logger.error("Exception thrown {}", e.getMessage());
			e.printStackTrace();
		}
	}

}
