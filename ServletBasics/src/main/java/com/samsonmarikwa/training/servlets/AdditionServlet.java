package com.samsonmarikwa.training.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class AdditionServlet extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		if (request.getParameter("number1") != null && request.getParameter("number2") != null) {
			int num1 = Integer.parseInt(request.getParameter("number1"));
			int num2 = Integer.parseInt(request.getParameter("number2"));

			PrintWriter out = response.getWriter();
			out.println("The result is " + num1 + num2);
		}

	}

}
