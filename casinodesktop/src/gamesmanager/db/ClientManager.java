package gamesmanager.db;

import gamesmanager.beans.Address;
import gamesmanager.beans.Client;
import gamesmanager.beans.Person;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class ClientManager {
	
	private static String INSERT = "{? = call insertclient[(?, ?, ?, ?, ?, ?, ?," +
			"?, ?, ?, ?, ?, ?, ?, ?, ?)]}";
	
	public static boolean insertClient(Client c){
		Connection conn = DatabaseManager.connect();
		if(conn == null){
			return false;
		}
		try {
			CallableStatement cs = conn.prepareCall(INSERT);
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
			if(rows == 1){
				return true;
			} else {
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.close(conn);			
		}
		return false;
	}
	
	public static Person findClient(String id){
		return null;
	}
	
	
	
}
