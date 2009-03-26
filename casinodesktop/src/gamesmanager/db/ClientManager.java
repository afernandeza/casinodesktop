package gamesmanager.db;

import gamesmanager.beans.Address;
import gamesmanager.beans.Client;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class ClientManager {

    private static String INSERT = "{call insertclient[(?, ?, ?, ?, ?, ?, ?,"
            + "?, ?, ?, ?, ?, ?, ?, ?, ?)]}";
    private static String UPDATE = "{call updateclient[(?, ?, ?, ?, ?, ?, ?,"
            + "?, ?, ?, ?, ?, ?, ?, ?, ?)]}";
    private static String DELETE = "{call deleteclient[(?)]}";
    private static String FIND = "select * from findclient(?)";

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
            c = new Client();
            c.setNombres(cs.getString(1));
            c.setAppaterno(cs.getString(2));
            c.setApmaterno(cs.getString(3));
            c.setSexo(cs.getString(4).charAt(0));
            c.setFechanac(cs.getDate(5));
            // c.setFoto(cs.getBlob(6));
            c.setTelcasa(cs.getString(7));
            c.setTelcel(cs.getString(8));

            System.out.println(c);

            Address a = new Address();
            a.setCallenum(cs.getString(9));
            a.setNumint(cs.getString(10));
            a.setColonia(cs.getString(11));
            a.setMunicipio(cs.getString(12));
            a.setCodigopostal(cs.getString(13));
            a.setEstado(cs.getString(14));
            a.setPais(cs.getString(15));

            System.out.println(a);

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
