package gamesmanager.db;

import gamesmanager.beans.GameTable;
import gamesmanager.ui.Helpers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class TableManager {

    public static String[] TABLECOLUMNS = { "ID", "Juego" };
    private static String GETTABLESCOUNT = "SELECT COUNT(*) FROM mesasinfo";
    private static String GETTABLES = "SELECT * FROM mesasinfo";
    private static String INSERT = "{ ? = call insertgametable( ?, ? ) }";
    private static String DELETE = "{ ? = call deletegametable( ? ) }";
    private static String UPDATE = "{ ? = call updategametable( ?, ? ) }";

    public static boolean updateGameTable(int tableid, int gametypeid) {
        Connection conn = DatabaseManager.connect();
        CallableStatement cs = null;
        if (conn == null) {
            return false;
        }
        try {
            cs = conn.prepareCall(UPDATE);
            cs.registerOutParameter(1, Types.BOOLEAN);

            cs.setInt(2, tableid);
            cs.setInt(3, gametypeid);

            cs.execute();
            return cs.getBoolean(1);
        } catch (SQLException e) {
            if (Helpers.DEBUG) {
                // e.printStackTrace();
                System.out.println("Error updating table: " + e.getMessage());
            }
        } finally {
            DatabaseManager.close(cs);
            DatabaseManager.close(conn);
        }
        return false;
    }

    public static boolean insertGameTable(GameTable gt) {
        if (gt == null) {
            if (Helpers.DEBUG) {
                throw new NullPointerException("Mesa nulo");
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

            cs.setInt(2, gt.getTableid());
            cs.setInt(3, gt.getGameType().getTypeid());

            cs.execute();
            return cs.getBoolean(1);
        } catch (SQLException e) {
            if (Helpers.DEBUG) {
                // e.printStackTrace();
                System.out.println("Error inserting table: " + e.getMessage());
            }
        } finally {
            DatabaseManager.close(cs);
            DatabaseManager.close(conn);
        }
        return false;
    }

    public static boolean deleteGameTable(int tableid) {
        return DatabaseOperations.runStoredProcedure(tableid, DELETE);
    }

    public static Object[][] getTables() {
        Connection conn = DatabaseManager.connect();
        if (conn == null) {
            return new Object[0][0];
        }
        PreparedStatement rowcount;
        PreparedStatement query;
        try {
            rowcount = conn.prepareStatement(GETTABLESCOUNT);
            query = conn.prepareStatement(GETTABLES);
        } catch (SQLException e) {
            if (Helpers.DEBUG) {
                e.printStackTrace();
            }
            return new Object[0][0];
        }

        return DatabaseOperations.getResults(rowcount, query);
    }

    public static GameTable[] getTablesArray() {
        Object[][] o = getTables();
        GameTable[] s = new GameTable[o.length];
        for (int i = 0; i < o.length; i++) {
            GameTable e = new GameTable();
            e.setTableid(o[i][0].toString());
            e.setGame(o[i][1].toString());
            s[i] = e;
        }
        return s;
    }
}
