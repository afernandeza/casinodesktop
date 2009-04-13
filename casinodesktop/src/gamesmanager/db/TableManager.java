package gamesmanager.db;

import gamesmanager.ui.Helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TableManager {
    
    public static String[] TABLECOLUMNS = {"ID", "Juego"};
    private static String GETTABLESCOUNT = "SELECT COUNT(*) FROM mesasinfo";
    private static String GETTABLES = "SELECT * FROM mesasinfo";
    
    public static Object[][] getTables(){
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
            if(Helpers.DEBUG){
                e.printStackTrace();
            }
            return new Object[0][0];
        }
        
        return DatabaseOperations.getResults(rowcount, query);
    }
}
