package gamesmanager.db.sync;

import gamesmanager.beans.Casino;
import gamesmanager.db.DatabaseManager;
import gamesmanager.ui.Helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class CasinoManager {

    private static String GETCASINOID = "select * from getlocalcasinoid()";
    private static String GETCASINOS = "SELECT * FROM sync(?)";
    private static String GETLATESTSID = "SELECT * FROM getlatestsyncedid(?)";
    
    public static Object[][] getCasinosTable(){
        List<Casino> casinos = getCasinos();
        int l = casinos.size();
        Object[][] cs = new Object[l][3];
        for(int i = 0; i < l; i++){
            cs[i][0] = casinos.get(i).getCasinoid();
            cs[i][1] = casinos.get(i).getIp();
            cs[i][2] = casinos.get(i).isAvailable();
        }
        return cs;
    }
    
    public static List<Casino> getCasinos() {
        Connection remconn = DatabaseManager.connectToBoss();
        PreparedStatement getpstmt = null;
        ResultSet rs = null;
        List<Casino> casinos = new LinkedList<Casino>();
        try {
            getpstmt = remconn.prepareStatement(GETCASINOS);
            getpstmt.setString(1, CasinoManager.getLocalCasinoId());

            rs = getpstmt.executeQuery();
            while(rs.next()){
                Casino c = new Casino(rs.getString(1), rs.getString(2));
                c.setAvailable(DatabaseManager.databaseAvailable(c.getIp()));
                casinos.add(c);
            }
            getLatestSyncedIds(casinos);
        } catch (Exception e) {
            if (Helpers.DEBUG) {
                e.printStackTrace();
            }
        } finally {
            DatabaseManager.close(getpstmt);
            DatabaseManager.close(rs);
            DatabaseManager.close(remconn);
        }
        return casinos;
    }

    public static void getLatestSyncedIds(List<Casino> casinos){
        Connection localconn = DatabaseManager.connect();
        PreparedStatement getpstmt = null;
        ResultSet rs = null;
        try {
            getpstmt = localconn.prepareStatement(GETLATESTSID);
            for(Casino c: casinos){
                getpstmt.setString(1, c.getCasinoid());
                rs = getpstmt.executeQuery();
                if(rs.next()){
                    c.setLatestsyncedid(rs.getInt(1));
                }
            }

        } catch (Exception e) {
            if (Helpers.DEBUG) {
                e.printStackTrace();
            }
        } finally {
            DatabaseManager.close(getpstmt);
            DatabaseManager.close(rs);
            DatabaseManager.close(localconn);
        }
    }
    
    public static String getLocalCasinoId(){
        Connection conn = DatabaseManager.connect();
        Statement s = null;
        ResultSet rs = null;
        if (conn == null) {
            return null;
        }
        try {
            s = conn.createStatement();
            rs = s.executeQuery(GETCASINOID);
            if(rs.next()){
                String id = rs.getString(1);
                return id;
            }
            return null;
        } catch (SQLException e) {
            if (Helpers.DEBUG) {
                // e.printStackTrace();
                System.out.println("Error retrieving casino id: " + e.getMessage());
            }
        } finally {
            DatabaseManager.close(s);
            DatabaseManager.close(rs);
            DatabaseManager.close(conn);
        }
        return null;
    }
}
