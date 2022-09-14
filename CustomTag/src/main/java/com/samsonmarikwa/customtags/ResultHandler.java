package com.samsonmarikwa.customtags;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.Tag;
import jakarta.servlet.jsp.tagext.TagSupport;

public class ResultHandler extends TagSupport {
	// instead of implementing Tag, we extend TagSupport
	// This allows us to implement only the methods we need

	Connection con;
	PreparedStatement stmt;

	public ResultHandler() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "P@ssW0rd");
			stmt = con.prepareStatement("select * from user where email = ?");
						
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int doStartTag() throws JspException {
		ServletRequest request = pageContext.getRequest();
		String email = request.getParameter("email");
		
		try {
			stmt.setString(1, email);
			ResultSet resultSet = stmt.executeQuery();
			JspWriter out = pageContext.getOut();
			if (resultSet.next()) {
				out.print("User Details are: <br/> First Nmae: ");
				out.print(resultSet.getString(1));
				out.print("<br/>Last Nmae: ");
				out.print(resultSet.getString(2));
			} else {
				out.print("Invalid email entered");
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return Tag.SKIP_BODY; // we do not have a body in the request
	}
	
	@Override
	public void release() {
		try {
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
