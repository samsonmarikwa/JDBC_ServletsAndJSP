package com.samsonmarikwa.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDAO {
	public static void main(String[] args) {
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "P@ssW0rd");
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from account");
				){
			
			int result = statement.executeUpdate("insert into account values(1, 'Marikwa', 'Samson', 10000)");
			System.out.println(result + " rows got inserted");
			
			result = statement.executeUpdate("update account set bal = 25000 where accno = 1");
			System.out.println(result + " rows got updated");
		
			while (resultSet.next()) {
				System.out.println(resultSet.getString(2));
				System.out.println(resultSet.getString(3));
				System.out.println(resultSet.getInt(4));
			}
			
			result = statement.executeUpdate("delete from account where accno = 1");
			System.out.println(result + " rows got deleted");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
