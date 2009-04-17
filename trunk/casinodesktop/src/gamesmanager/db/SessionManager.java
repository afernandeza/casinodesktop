package gamesmanager.db;

import gamesmanager.beans.GameSession;
import gamesmanager.ui.Helpers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class SessionManager {

    public static String[] TABLECOLUMNS = { "ID", "ID Mesa", "Juego",
            "Fichas inicio", "Fichas cierre", "Responsable", "Apertura",
            "Cierre" };
    private static String GETSESSIONCOUNT = "SELECT COUNT(*) FROM sesionesinfo";
    private static String GETSESSIONS = "SELECT * FROM sesionesinfo";
    private static String INSERT = "{ ? = call insertgamesession( ?, ?, ?) }";
    private static String CLOSE = "{ ? = call closegamesession( ? , ?) }";

    public static boolean closeGameSession(int gamdeid, double fichas) {
        return true;
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
            cs.setDouble(3, gs.getFichasinicio());
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

    public static Object[][] getGameSessions() {
        Connection conn = DatabaseManager.connect();
        if (conn == null) {
            return new Object[0][0];
        }
        PreparedStatement rowcount;
        PreparedStatement query;
        try {
            rowcount = conn.prepareStatement(GETSESSIONCOUNT);
            query = conn.prepareStatement(GETSESSIONS);
        } catch (SQLException e) {
            if (Helpers.DEBUG) {
                e.printStackTrace();
            }
            return new Object[0][0];
        }

        return DatabaseOperations.getResults(rowcount, query);
    }
}
