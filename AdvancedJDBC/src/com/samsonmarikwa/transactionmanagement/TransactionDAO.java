package com.samsonmarikwa.transactionmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionDAO {
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "P@ssW0rd");
			stmt = con.createStatement();
			
			con.setAutoCommit(false);
			stmt.executeUpdate("update account set bal=bal-500 where accno=10");
			stmt.executeUpdate("update account set bal=bal+500 where accno=20");
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.rollback();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
