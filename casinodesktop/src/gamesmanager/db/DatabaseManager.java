package gamesmanager.db;

import gamesmanager.ui.Helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    
    public static final int CONNECTIONTIMEOUT = 5;
    public static final String BOSSIP = "10.49.10.108";

    public static Connection connectToBoss(){
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            DriverManager.setLoginTimeout(CONNECTIONTIMEOUT);
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://"+BOSSIP+"/casino",
                    "sucursales", "sucursales");
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

    public static boolean localDatabaseAvailable() {
        Connection conn = attemptConnection("localhost");
        if (conn == null) {
            return false;
        } else {
            close(conn);
            return true;
        }
    }
    
    public static boolean databaseAvailable(String ip) {
        Connection conn = attemptConnection(ip);
        if (conn == null) {
            return false;
        } else {
            close(conn);
            return true;
        }
    }

    public static Connection attemptConnection(String ip) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            DriverManager.setLoginTimeout(CONNECTIONTIMEOUT);
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://"+ip+"/casinolocal",
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
    
    public static Connection connect() {
        return attemptConnection("localhost");
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
