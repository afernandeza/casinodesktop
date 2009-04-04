package gamesmanager.db;

import gamesmanager.ui.Helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    public static boolean databaseAvailable(){
        Connection conn = connect();
        if(conn == null){
            return false;
        } else {
            close(conn);
            return true;
        }
    }
    
    public static Connection connect() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/casinolocal",
                    "casindesktopapp", "casindesktopapp");
        } catch (ClassNotFoundException cnfe) {
            if (Helpers.DEBUG) {
                System.err.println(cnfe.getMessage());
            }
        } catch (SQLException e) {
            if (Helpers.DEBUG) {
                System.err.println(e.getMessage());
            }
        }

        return conn;
    }

    public static void close(ResultSet rs) {
        try {
            rs.close();
        } catch (Exception e) {

        }
    }

    public static void close(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {

        }
    }

    public static void close(Statement stmt) {
        try {
            stmt.close();
        } catch (Exception e) {

        }
    }
}
