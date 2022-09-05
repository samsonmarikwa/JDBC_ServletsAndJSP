package com.samsonmarikwa.training.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

/*
 * @WebServlet(urlPatterns = "/hello")
 * The above annotation can be configured in place of the following entry in the web.xml file.
 * 	<servlet>
		<servlet-name>HelloServlet</servlet-name>
		<servlet-class>com.samsonmarikwa.training.servlets.HelloWorldServlet</servlet-class>
	</servlet>
	<servlet-mapping>
		<servlet-name>HelloServlet</servlet-name>
		<url-pattern>/hello</url-pattern>
	</servlet-mapping>
 */
public class HelloWorldServlet extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>Hello Servlets World");
		out.println("</body>");
		out.println("</html>");

	}

}
