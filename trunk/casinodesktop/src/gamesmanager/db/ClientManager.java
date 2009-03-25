package gamesmanager.db;

import gamesmanager.beans.Address;
import gamesmanager.beans.Client;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientManager {

	private static String INSERT = "{call insertclient[(?, ?, ?, ?, ?, ?, ?,"
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?)]}";
	private static String UPDATE = "{call updateclient[(?, ?, ?, ?, ?, ?, ?,"
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?)]}";
	private static String DELETE = "{call deleteclient[(?)]}";
	private static String FIND = "{call findclient[(?)]}";

	public static boolean insertClient(Client c) {
		Connection conn = DatabaseManager.connect();
		CallableStatement cs = null;
		if (conn == null) {
			return false;
		}
		try {
			cs = conn.prepareCall(INSERT);
			cs.setDouble(1, c.getCredito());
			cs.setString(2, c.getNombres());
			cs.setString(3, c.getAppaterno());
			cs.setString(4, c.getApmaterno());
			cs.setString(5, c.getSexo() + "");
			cs.setDate(6, (Date) c.getFechanac());
			cs.setBlob(7, c.getFoto());
			cs.setString(8, c.getTelcasa());
			cs.setString(9, c.getTelcel());

			Address d = c.getAddress();
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

		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println("Error inserting client: " + e.getMessage());
		} finally {
			DatabaseManager.close(cs);
			DatabaseManager.close(conn);
		}
		return false;
	}

	public static Client findClient(String id) {
		Connection conn = DatabaseManager.connect();
		CallableStatement cs = null;
		if (conn == null) {
			return null;
		}
		Client c = null;
		try {
			cs = conn.prepareCall(FIND);
			cs.setString(1, id);
			
			ResultSet rs = cs.executeQuery();
			rs.next();
			


		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println("Error finding client: " + e.getMessage());
		} finally {
			DatabaseManager.close(cs);
			DatabaseManager.close(conn);
		}
		return c;
	}
}
