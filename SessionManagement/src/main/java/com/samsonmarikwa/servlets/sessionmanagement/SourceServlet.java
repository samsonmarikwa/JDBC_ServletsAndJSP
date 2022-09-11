package com.samsonmarikwa.servlets.sessionmanagement;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SourceServlet extends HttpServlet {
	
	private static final long serialVersionUID = -8582813157012285897L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("userName");
		
		HttpSession session = request.getSession();
		session.setAttribute("user", userName);
		
		response.setContentType("text/html");
		try {
			PrintWriter out = response.getWriter();
			out.print("<a href='targetServletUri'>Click Here To Get The User Name</a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
