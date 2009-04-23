package gamesmanager.db;

import gamesmanager.beans.Casino;
import gamesmanager.ui.Helpers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class CasinoManager {

    private static String INSERT = "{ ? = call insertcasino( ?, ? ) }";
    private static String UPDATE = "{ ? = call updatecasino( ?, ? ) }";
    private static String DELETE = "{ ? = call deletecasino( ? ) }";

    public static boolean updateCasino(Casino casino) {
        if (casino == null) {
            if (Helpers.DEBUG) {
                throw new NullPointerException("Casino nulo");
            } else {
                return false;
            }
        }
        Connection conn = DatabaseManager.connect();
        CallableStatement cs = null;
        if (conn == null) {
            return false;
        }
        try {
            cs = conn.prepareCall(UPDATE);
            cs.registerOutParameter(1, Types.BOOLEAN);

            cs.setString(2, casino.getCasinoid());
            cs.setString(3, casino.getJdbcurl());

            cs.execute();
            return cs.getBoolean(1);
        } catch (SQLException e) {
            if (Helpers.DEBUG) {
                // e.printStackTrace();
                System.out.println("Error updating casino: " + e.getMessage());
            }
        } finally {
            DatabaseManager.close(cs);
            DatabaseManager.close(conn);
        }
        return false;
    }
    
    public static boolean insertCasino(Casino casino) {
        if (casino == null) {
            if (Helpers.DEBUG) {
                throw new NullPointerException("Casino nulo");
            } else {
                return false;
            }
        }
        Connection conn = DatabaseManager.connect();
        CallableStatement cs = null;
        if (conn == null) {
            return false;
        }
        try {
            cs = conn.prepareCall(INSERT);
            cs.registerOutParameter(1, Types.BOOLEAN);

            cs.setString(2, casino.getCasinoid());
            cs.setString(3, casino.getJdbcurl());

            cs.execute();
            return cs.getBoolean(1);
        } catch (SQLException e) {
            if (Helpers.DEBUG) {
                // e.printStackTrace();
                System.out.println("Error inserting casino: " + e.getMessage());
            }
        } finally {
            DatabaseManager.close(cs);
            DatabaseManager.close(conn);
        }
        return false;
    }

    public static boolean deleteCasino(String casinoid) {
        return DatabaseOperations.runStoredProcedure(casinoid, DELETE);
    }

}
