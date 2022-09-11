package com.samsonmarikwa.servlets.sessionmanagement;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class TargetServlet extends HttpServlet {
	
	private static final long serialVersionUID = -5439760644476598855L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("user");
		
		response.setContentType("text/html");
		try {
			PrintWriter out = response.getWriter();
			out.print("<h1>User Name Is: " + userName + "</h1>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
