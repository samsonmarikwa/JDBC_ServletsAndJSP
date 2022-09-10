package com.samsonmarikwa.Initilization;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebServlet(urlPatterns = "/preInitServlet", loadOnStartup = 1)
public class InitServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init() {
		System.out.println("Inside the init method");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write("From the pre init servlet");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
