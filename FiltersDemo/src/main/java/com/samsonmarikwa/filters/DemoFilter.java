package com.samsonmarikwa.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import java.io.IOException;
import java.io.PrintWriter;

//@WebFilter("/filterDemoServlet") // "/*" will make this filter available for all the Servlets in the application
// Filter order is as per filter-mapping entries in the web.xml
public class DemoFilter extends HttpFilter {
       
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		out.print("Pre Servlet\n");
		chain.doFilter(request, response);
		out.print("Post Servlet\n");
	}

	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

}
