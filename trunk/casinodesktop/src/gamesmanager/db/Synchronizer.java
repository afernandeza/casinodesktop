package gamesmanager.db;

import gamesmanager.beans.Casino;
import gamesmanager.beans.SyncQuery;
import gamesmanager.ui.Helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.LinkedList;
import java.util.List;

public class Synchronizer {
    
    private static String CASINOCOUNT = "SELECT COUNT(*) FROM sucursales";
    private static String GETCASINOS = "SELECT * FROM sucursales";
    
    private static String QUERYCOUNT = "SELECT COUNT(*) from syncinfofor(?)";
    private static String GETQUERIES = "SELECT qid, query from syncinfofor(?)";

    private static List<Casino> getAvailableCasinos(){
        Connection localconn = DatabaseManager.connect();
        PreparedStatement countpstmt = null;
        PreparedStatement getpstmt = null;
        List<Casino> casinos = new LinkedList<Casino>();
        try{
            countpstmt = localconn.prepareStatement(CASINOCOUNT);
            getpstmt = localconn.prepareStatement(GETCASINOS);
            Object[][] o = DatabaseOperations.getResults(countpstmt, getpstmt);
            
            for(int i = 0; i < o.length; i++){
                Casino c = new Casino(o[i][0].toString(), o[i][1].toString(), (Integer)o[i][2]);
                Connection conn = DatabaseManager.attemptConnection(c.getJdbcurl());
                if(conn != null){
                    casinos.add(c);   
                }
                DatabaseManager.close(conn);
            }
        } catch(Exception e){
            if(Helpers.DEBUG){
                e.printStackTrace();
            }
        }
        return casinos;
    }
    
    private static List<SyncQuery> getQueriesForCasino(Casino c){
        Connection localconn = DatabaseManager.connect();
        PreparedStatement countpstmt = null;
        PreparedStatement getpstmt = null;
        List<SyncQuery> queries = new LinkedList<SyncQuery>();
        try{
            countpstmt = localconn.prepareStatement(QUERYCOUNT);
            countpstmt.setInt(1, c.getLatestsyncedid());
            getpstmt = localconn.prepareStatement(GETQUERIES);
            getpstmt.setInt(1, c.getLatestsyncedid());
            Object[][] o = DatabaseOperations.getResults(countpstmt, getpstmt);
            for(int i = 0; i < o.length; i++){
                SyncQuery sq = new SyncQuery((Integer)o[i][0], o[i][1].toString());
                queries.add(sq);
            }
        } catch(Exception e){
            if(Helpers.DEBUG){
                e.printStackTrace();
            }
        }
        return queries;
    }
    
    public static void sync(){
        List<Casino> casinos = getAvailableCasinos();
        for(Casino casino: casinos){
            System.out.println(casino);
            List<SyncQuery> queries = getQueriesForCasino(casino);
            for(SyncQuery sq: queries){
                Connection conn = DatabaseManager.attemptConnection(casino.getJdbcurl());
                if(conn != null){
                    System.out.println(sq);   
                }
            }
        }
    }
    
}
