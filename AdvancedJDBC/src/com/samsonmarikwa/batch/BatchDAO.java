package com.samsonmarikwa.batch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchDAO {

	public static void main(String[] args) {
		
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "P@ssW0rd");
				Statement stmt = con.createStatement();
				) {
			stmt.addBatch("insert into account values(10, 'Marikwa', 'Cynthia', 15000)");
			stmt.addBatch("insert into account values(20, 'Kanakembizi', 'Ariel', 25000)");
			stmt.addBatch("insert into account values(30, 'Kanakembizi', 'Tonderai', 15000)");
			
			int[] result = stmt.executeBatch();
			
			for (int i = 0; i < result.length; i++) {
				System.out.println(result[i]);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}
