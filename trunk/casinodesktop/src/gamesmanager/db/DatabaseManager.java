package gamesmanager.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
	
	private static Connection conn;

	public static boolean connect() {
		boolean connected = false;

		if(conn != null){
			try {
				Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection(
						"jdbc:postgresql://localhost/casinolocal", "casindesktopapp",
				"casindesktopapp");
				connected = true;
			} catch (ClassNotFoundException cnfe) {
				System.err.println(cnfe.getMessage());
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			} 
		} else {
			connected  = true;
		}

		return connected;
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
