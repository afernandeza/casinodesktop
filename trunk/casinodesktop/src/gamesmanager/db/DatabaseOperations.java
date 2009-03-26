package gamesmanager.db;

import gamesmanager.beans.EmployeeType;
import gamesmanager.beans.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DatabaseOperations {

	private final static String AUTH = "? = call authenticate(?, ?)";
	private final static String GETEMPLOYEETYPES = "SELECT * FROM tipoempleados " +
			"ORDER BY tipo";
	private final static String GETCOUNTRIES = "SELECT printable_name " +
			"FROM country ORDER BY printable_name";
	private final static String GETSTATES = "SELECT estado " +
			"FROM estadosmexico ORDER BY estado";
	
	public static boolean login(User u) {
		boolean authorized = true;
		System.out.println("authorizing");
		return authorized;
	}
	
	public static List<String> getCountries(){
		return getList(GETCOUNTRIES);
	}
	
	public static List<String> getStates(){
		return getList(GETSTATES);
	}
	
	public static List<EmployeeType> getEmployeeTypes(){
		Connection conn = DatabaseManager.connect();
		if (conn == null) {
			return null;
		}
		List<EmployeeType> types = new LinkedList<EmployeeType>();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(GETEMPLOYEETYPES);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				types.add(new EmployeeType(rs.getInt(1), rs.getString(2)));
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("Error getting employee types: " + sqle.getMessage());
		} finally {
			DatabaseManager.close(pstmt);
			DatabaseManager.close(conn);
		}
		
		return types;
	}

	
	private static List<String> getList(String query){
		Connection conn = DatabaseManager.connect();
		if (conn == null) {
			return null;
		}
		List<String> types = new LinkedList<String>();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				types.add(rs.getString(1));
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("Error getting types: " + sqle.getMessage());
		} finally {
			DatabaseManager.close(pstmt);
			DatabaseManager.close(conn);
		}
		
		return types;
	}
}