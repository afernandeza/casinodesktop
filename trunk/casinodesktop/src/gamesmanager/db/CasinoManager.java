package gamesmanager.db;

import gamesmanager.beans.Casino;
import gamesmanager.ui.Helpers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

public class CasinoManager {

    private static String INSERT = "{ ? = call insertcasino( ?, ? ) }";
    private static String UPDATE = "{ ? = call updatecasino( ?, ? ) }";
    private static String DELETE = "{ ? = call deletecasino( ? ) }";
    private static String GETCASINOID = "select * from getlocalcasinoid()";
    private static String GETCASINOS = "SELECT * FROM sync(?)";

    public static String[][] getCasinosTable(){
        List<Casino> casinos = getCasinos();
        int l = casinos.size();
        String[][] cs = new String[l][2];
        for(int i = 0; i < l; i++){
            cs[i][0] = casinos.get(i).getCasinoid();
            cs[i][1] = casinos.get(i).getIp();
        }
        return cs;
    }
    
    public static List<Casino> getCasinos() {
        Connection localconn = DatabaseManager.connectToBoss();
        PreparedStatement getpstmt = null;
        List<Casino> casinos = new LinkedList<Casino>();
        try {
            getpstmt = localconn.prepareStatement(GETCASINOS);
            getpstmt.setString(1, CasinoManager.getLocalCasinoId());

            ResultSet rs = getpstmt.executeQuery();
            while(rs.next()){
                Casino c = new Casino(rs.getString(1), rs.getString(2));
                c.setAvailable(DatabaseManager.databaseAvailable(c.getIp()));
                casinos.add(c);
            }
        } catch (Exception e) {
            if (Helpers.DEBUG) {
                e.printStackTrace();
            }
        }
        return casinos;
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
            cs.setString(3, casino.getIp());

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
            cs.setString(3, casino.getIp());

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
