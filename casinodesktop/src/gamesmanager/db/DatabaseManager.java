package gamesmanager.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

	public static Connection connect() {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(
					"jdbc:postgresql://localhost/casinolocal", "casindesktopapp",
			"casindesktopapp");
		} catch (ClassNotFoundException cnfe) {
			System.err.println(cnfe.getMessage());
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} 

		return conn;
	}

	public static void close(ResultSet rs){
		try{
			rs.close();
		} catch(Exception e){

		}
	}

	public static void close(Connection conn){
		try{
			conn.close();
		} catch(Exception e){

		}
	}

	public static void close(Statement stmt){
		try{
			stmt.close();
		} catch(Exception e){

		}
	}
}
