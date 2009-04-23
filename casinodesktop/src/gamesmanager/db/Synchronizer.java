package gamesmanager.db;

import gamesmanager.beans.Casino;
import gamesmanager.beans.SyncQuery;
import gamesmanager.ui.Helpers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

public class Synchronizer {

    private static String CASINOCOUNT = "SELECT COUNT(*) FROM sucursales";
    private static String GETCASINOS = "SELECT * FROM sucursales";

    private static String QUERYCOUNT = "SELECT COUNT(*) from syncinfofor(?)";
    private static String GETQUERIES = "SELECT qid, query from syncinfofor(?)";
    
    private static String UPDATELATESTSID = "{ ? = call updatelatestsid(?, ?)}";

    public static Object[][] getCasinosTable() {
        List<Casino> casinos = getCasinos();
        Object[][] o = new Object[casinos.size()][3];

        for (int i = 0; i < o.length; i++) {
            o[i][0] = casinos.get(i).getCasinoid();
            o[i][1] = casinos.get(i).getJdbcurl();
            o[i][2] = casinos.get(i).isAvailable();
        }

        return o;
    }

    private static List<Casino> getCasinos() {
        Connection localconn = DatabaseManager.connect();
        PreparedStatement countpstmt = null;
        PreparedStatement getpstmt = null;
        List<Casino> casinos = new LinkedList<Casino>();
        try {
            countpstmt = localconn.prepareStatement(CASINOCOUNT);
            getpstmt = localconn.prepareStatement(GETCASINOS);
            Object[][] o = DatabaseOperations.getResults(countpstmt, getpstmt);

            for (int i = 0; i < o.length; i++) {
                Casino c = new Casino(o[i][0].toString(), o[i][1].toString(),
                        (Integer) o[i][2]);
                Connection conn = DatabaseManager.attemptConnection(c
                        .getJdbcurl());
                if (conn != null) {
                    c.setAvailable(true);
                }
                casinos.add(c);
                DatabaseManager.close(conn);
            }
        } catch (Exception e) {
            if (Helpers.DEBUG) {
                e.printStackTrace();
            }
        }
        return casinos;
    }

    private static List<SyncQuery> getQueriesForCasino(Casino c) {
        Connection localconn = DatabaseManager.connect();
        PreparedStatement countpstmt = null;
        PreparedStatement getpstmt = null;
        List<SyncQuery> queries = new LinkedList<SyncQuery>();
        try {
            countpstmt = localconn.prepareStatement(QUERYCOUNT);
            countpstmt.setInt(1, c.getLatestsyncedid());
            getpstmt = localconn.prepareStatement(GETQUERIES);
            getpstmt.setInt(1, c.getLatestsyncedid());
            Object[][] o = DatabaseOperations.getResults(countpstmt, getpstmt);
            for (int i = 0; i < o.length; i++) {
                SyncQuery sq = new SyncQuery((Integer) o[i][0], o[i][1]
                        .toString());
                queries.add(sq);
            }
        } catch (Exception e) {
            if (Helpers.DEBUG) {
                e.printStackTrace();
            }
        } finally {
            DatabaseManager.close(localconn);
        }
        return queries;
    }

    private static boolean updateLatestSyncedId(String cid, int lqid) {
        Connection conn = DatabaseManager.connect();
        CallableStatement cs = null;
        if (conn == null) {
            return false;
        }
        try {
            cs = conn.prepareCall(UPDATELATESTSID);
            cs.registerOutParameter(1, Types.BOOLEAN);

            cs.setString(2, cid);
            cs.setInt(3, lqid);

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

    public static void sync() {
        List<Casino> casinos = getCasinos();
        for (Casino casino : casinos) {
            if (casino.isAvailable()) {
                System.out.println(casino);
                List<SyncQuery> queries = getQueriesForCasino(casino);
                int initsyncedid = casino.getLatestsyncedid();
                int latestsyncedid = casino.getLatestsyncedid();
                for (SyncQuery sq : queries) {
                    Connection conn = DatabaseManager.attemptConnection(casino
                            .getJdbcurl());
                    if (conn != null) {
                        try {
                            String call = "{? = call " + sq.toString() + "}";
                            System.out.println(call);
                            CallableStatement cs = conn.prepareCall(call);
                            cs.registerOutParameter(1, Types.BOOLEAN);
                            cs.execute();
                            if (cs.getBoolean(1)) {
                                latestsyncedid = sq.getTypeid();
                            } else {
                                break;
                            }
                        } catch (Exception e) {
                            if (Helpers.DEBUG) {
                                e.printStackTrace();
                            }
                            break;
                        } finally {
                            DatabaseManager.close(conn);
                        }
                    } else {
                        break;
                    }
                }
                if (initsyncedid != latestsyncedid) {
                    // update latest synced
                    updateLatestSyncedId(casino.getCasinoid(), latestsyncedid);
                }
            }
        }
    }

}
