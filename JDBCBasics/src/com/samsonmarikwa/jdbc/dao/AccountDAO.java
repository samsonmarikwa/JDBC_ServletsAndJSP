package com.samsonmarikwa.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccountDAO {
	public static void main(String[] args) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "P@ssW0rd");
			System.out.println(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
