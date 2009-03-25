package gamesmanager.db;

import gamesmanager.beans.Address;
import gamesmanager.beans.Employee;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class EmployeeManager {

	private static String INSERT = "{call insertemployee[(?, ?, ?, ?, ?, ?, ?,"
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?)]}";
	private static String UPDATE = "{call updateemployee[(?, ?, ?, ?, ?, ?, ?,"
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?)]}";
	private static String DELETE = "{call deleteemployee[(?)]}";
	private static String FIND = "{call findemployee[(?)]}";

	public static boolean insertEmployee(Employee e) {
		Connection conn = DatabaseManager.connect();
		CallableStatement cs = null;
		if (conn == null) {
			return false;
		}
		try {
			cs = conn.prepareCall(INSERT);
			cs.setString(2, e.getNombres());
			cs.setString(3, e.getAppaterno());
			cs.setString(4, e.getApmaterno());
			cs.setString(5, e.getSexo() + "");
			cs.setDate(6, (Date) e.getFechanac());
			cs.setBlob(7, e.getFoto());
			cs.setString(8, e.getTelcasa());
			cs.setString(9, e.getTelcel());

			Address d = e.getAddress();
			cs.setString(10, d.getCallenum());
			cs.setString(11, d.getNumint());
			cs.setString(12, d.getColonia());
			cs.setString(13, d.getMunicipio());
			cs.setString(14, d.getCodigopostal());
			cs.setString(15, d.getEstado());
			cs.setString(16, d.getPais());

			int rows = cs.executeUpdate();
			if (rows == 1) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException sqle) {
			// e.printStackTrace();
			System.out.println("Error inserting employee: " + sqle.getMessage());
		} finally {
			DatabaseManager.close(cs);
			DatabaseManager.close(conn);
		}
		return false;
	}

	public static Employee findEmployee(String id) {
		return null;
	}
}
