package com.samsonmarikwa.servlets.sessionmanagement;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SourceServlet extends HttpServlet {

	private static final long serialVersionUID = -8582813157012285897L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				System.out.println(cookies[i].getName());
				System.out.println(cookies[i].getValue());
			}
		}

		response.addCookie(new Cookie("securityToken", "1234567890"));

		String userName = request.getParameter("userName");

		HttpSession session = request.getSession();
		session.setAttribute("user", userName);

		response.setContentType("text/html");
		try {
			PrintWriter out = response.getWriter();
			String urlWriting = "targetServletUri?sessionId=123"; // WebServer can automatically generate this url, with
																	// a unique id assigned to the sessionId
			out.print("<a href='" + urlWriting + "'>Click Here To Get The User Name</a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
