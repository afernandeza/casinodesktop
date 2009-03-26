package gamesmanager.db;

import gamesmanager.beans.Address;
import gamesmanager.beans.Employee;
import gamesmanager.beans.EmployeeType;
import gamesmanager.beans.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeManager {

	private static String INSERT = "{call insertemployee[(?, ?, ?, ?, ?, ?, ?,"
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?)]}";
	private static String UPDATE = "{call updateemployee[(?, ?, ?, ?, ?, ?, ?,"
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?)]}";
	private static String DELETE = "{call deleteemployee[(?)]}";
	private static String FIND = "SELECT * FROM findemployee(?)";

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
		if (conn == null) {
			return null;
		}
		PreparedStatement pstmt = null;
		Employee e = null;
		try {
			pstmt = conn.prepareStatement(FIND);
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			e = new Employee();
			e.setId(rs.getString(1));
			e.setNombres(rs.getString(2));
			e.setAppaterno(rs.getString(3));
			String appaterno = null;
			if((appaterno = rs.getString(4)) !=  null){
				if(! appaterno.trim().equals("") ){
					e.setApmaterno(appaterno);		
				}
			}
			e.setSexo(rs.getString(5).charAt(0));
			e.setFechanac(rs.getDate(6));
			//c.setFoto(cs.getBlob(7));
			e.setTelcasa(rs.getString(8));
			e.setTelcel(rs.getString(9));
			
			Address a = new Address();
			a.setAddressid(rs.getInt(10));
			a.setCallenum(rs.getString(11));
			a.setNumint(rs.getString(12));
			a.setColonia(rs.getString(13));
			a.setMunicipio(rs.getString(14));
			a.setCodigopostal(rs.getString(15));
			a.setEstado(rs.getString(16));
			a.setPais(rs.getString(17));
			
			e.setAddress(a);
			User u = new User(rs.getInt(18), rs.getString(19), rs.getString(20));
			e.setUser(u);
			
			EmployeeType et = new EmployeeType(rs.getInt(21), rs.getString(22));
			e.setEmployeetype(et);
			
			System.out.println(e);
			System.out.println(a);
			System.out.println(u);
			System.out.println(et);

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("Error finding employee: " + sqle.getMessage());
		} finally {
			DatabaseManager.close(pstmt);
			DatabaseManager.close(conn);
		}
		return e;
	}
}
