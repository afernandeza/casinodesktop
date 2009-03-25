package gamesmanager.db;

import gamesmanager.beans.Address;
import gamesmanager.beans.Client;
import gamesmanager.beans.Employee;
import gamesmanager.beans.EmployeeType;
import gamesmanager.beans.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

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
		Connection conn = DatabaseManager.connect();
		CallableStatement cs = null;
		if (conn == null) {
			return null;
		}
		Employee e = null;
		try {
			cs = conn.prepareCall(FIND);
			cs.setString(1, id);
						  
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.registerOutParameter(2, Types.VARCHAR);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.CHAR);
			cs.registerOutParameter(5, Types.DATE);
			cs.registerOutParameter(6, Types.BLOB);
			cs.registerOutParameter(7, Types.VARCHAR);
			cs.registerOutParameter(8, Types.VARCHAR);
			cs.registerOutParameter(9, Types.VARCHAR);
			cs.registerOutParameter(10, Types.VARCHAR);
			cs.registerOutParameter(11, Types.VARCHAR);
			cs.registerOutParameter(12, Types.VARCHAR);
			cs.registerOutParameter(13, Types.VARCHAR);
			cs.registerOutParameter(14, Types.VARCHAR);
			cs.registerOutParameter(15, Types.VARCHAR);
			cs.registerOutParameter(16, Types.VARCHAR);
			cs.registerOutParameter(17, Types.VARCHAR);
			
			ResultSet rs = cs.executeQuery();
			rs.next();
			e = new Employee();
			e.setNombres(cs.getString(1));
			e.setAppaterno(cs.getString(2));
			e.setApmaterno(cs.getString(3));
			e.setSexo(cs.getString(4).charAt(0));
			e.setFechanac(cs.getDate(5));
			//c.setFoto(cs.getBlob(6));
			e.setTelcasa(cs.getString(7));
			e.setTelcel(cs.getString(8));
			
			Address a = new Address();
			a.setCallenum(cs.getString(9));
			a.setNumint(cs.getString(10));
			a.setColonia(cs.getString(11));
			a.setMunicipio(cs.getString(12));
			a.setCodigopostal(cs.getString(13));
			a.setEstado(cs.getString(14));
			a.setPais(cs.getString(15));
			
			
			e.setAddress(a);
			User u = new User(cs.getString(16));
			e.setUser(u);
			
			EmployeeType et = new EmployeeType(cs.getString(17));
			e.setEmployeetype(et);
			
			System.out.println(e);
			System.out.println(a);
			System.out.println(u);
			System.out.println(et);


		} catch (SQLException sqle) {
			// e.printStackTrace();
			System.out.println("Error finding employee: " + sqle.getMessage());
		} finally {
			DatabaseManager.close(cs);
			DatabaseManager.close(conn);
		}
		return e;
	}
}
