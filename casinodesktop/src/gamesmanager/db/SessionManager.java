package gamesmanager.db;

import gamesmanager.beans.GameSession;
import gamesmanager.ui.Helpers;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class SessionManager {

    public static String[] TABLECOLUMNS = { "ID", "ID Mesa", "Juego",
            "Fichas inicio", "Fichas cierre", "Responsable", "Apertura",
            "Cierre" };
    private static String GETALLSESSIONCOUNT = "SELECT COUNT(*) FROM sesionesinfo";
    private static String GETALLSESSIONS = "SELECT * FROM sesionesinfo";
    
    private static String GETOPENSESSIONCOUNT = "SELECT COUNT(*) FROM opensessionsinfo";
    private static String GETOPENSESSIONS = "SELECT * FROM opensessionsinfo";
    
    private static String INSERT = "{ ? = call insertgamesession( ?, ?, ?) }";
    private static String CLOSE = "{ ? = call closegamesession( ? , ?) }";

    public static boolean closeGameSession(int gameid, double fichas) {
        Connection conn = DatabaseManager.connect();
        CallableStatement cs = null;
        if (conn == null) {
            return false;
        }
        try {
            cs = conn.prepareCall(CLOSE);
            cs.registerOutParameter(1, Types.BOOLEAN);

            cs.setInt(2, gameid);
            BigDecimal bd = new BigDecimal(fichas);
            bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
            cs.setBigDecimal(3, bd);

            cs.execute();
            return cs.getBoolean(1);
        } catch (SQLException e) {
            if (Helpers.DEBUG) {
                // e.printStackTrace();
                System.out.println("Error closing session: " + e.getMessage());
            }
        } finally {
            DatabaseManager.close(cs);
            DatabaseManager.close(conn);
        }
        return false;
    }

    public static boolean startGameSession(GameSession gs) {
        if (gs == null) {
            if (Helpers.DEBUG) {
                throw new NullPointerException("sesion nula");
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

            cs.setInt(2, gs.getTableid());
            cs.setBigDecimal(3, gs.getFichasinicio());
            cs.setString(4, gs.getEmpleadoid());

            cs.execute();
            return cs.getBoolean(1);
        } catch (SQLException e) {
            if (Helpers.DEBUG) {
                // e.printStackTrace();
                System.out
                        .println("Error inserting session: " + e.getMessage());
            }
        } finally {
            DatabaseManager.close(cs);
            DatabaseManager.close(conn);
        }
        return false;
    }

    public static Object[][] getOpenGameSessions() {
        Connection conn = DatabaseManager.connect();
        if (conn == null) {
            return new Object[0][0];
        }
        PreparedStatement rowcount;
        PreparedStatement query;
        try {
            rowcount = conn.prepareStatement(GETOPENSESSIONCOUNT);
            query = conn.prepareStatement(GETOPENSESSIONS);
        } catch (SQLException e) {
            if (Helpers.DEBUG) {
                e.printStackTrace();
            }
            return new Object[0][0];
        }

        return DatabaseOperations.getResults(rowcount, query);
    }
    
    public static Object[][] getAllGameSessions() {
        Connection conn = DatabaseManager.connect();
        if (conn == null) {
            return new Object[0][0];
        }
        PreparedStatement rowcount;
        PreparedStatement query;
        try {
            rowcount = conn.prepareStatement(GETALLSESSIONCOUNT);
            query = conn.prepareStatement(GETALLSESSIONS);
        } catch (SQLException e) {
            if (Helpers.DEBUG) {
                e.printStackTrace();
            }
            return new Object[0][0];
        }

        return DatabaseOperations.getResults(rowcount, query);
    }
}
