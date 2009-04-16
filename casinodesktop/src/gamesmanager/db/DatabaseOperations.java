package gamesmanager.db;

import gamesmanager.beans.Type;
import gamesmanager.beans.User;
import gamesmanager.ui.Helpers;
import gamesmanager.ui.session.Session;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

public class DatabaseOperations {

    private final static String AUTH = "{ ? = call authenticate( ?, ? ) }";
    private final static String GETCOUNTRIES = "SELECT printable_name "
        + "FROM country ORDER BY printable_name";
    private final static String GETSTATES = "SELECT estado "
        + "FROM estadosmexico ORDER BY estado";
    private final static String SESSIONINFO = "SELECT empleadoid, usuario, externo, " +
    		"tipoempleadoid, tipo FROM employeesdata WHERE usuario = ?";

    public static boolean runStoredProcedure(int id, String query){
        Connection conn = DatabaseManager.connect();
        CallableStatement cs = null;
        if (conn == null) {
            return false;
        }
        try {
            cs = conn.prepareCall(query);
            cs.registerOutParameter(1, Types.BOOLEAN);
            cs.setInt(2, id);
            cs.execute();
            return cs.getBoolean(1);
        } catch (SQLException e) {
            if (Helpers.DEBUG) {
                e.printStackTrace();
                System.out.println("Error running procedure: " + e.getMessage());
            }
        } finally {
            DatabaseManager.close(cs);
            DatabaseManager.close(conn);
        }
        return false;
    }
    
    public static boolean runStoredProcedure(String id, String query){
        if (id == null) {
            if (Helpers.DEBUG) {
                throw new NullPointerException("id nulo");
            } else {
                return false;
            }
        }
        if (id.equals("")) {
            if (Helpers.DEBUG) {
                throw new IllegalArgumentException("id vacio");
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
            cs = conn.prepareCall(query);
            cs.registerOutParameter(1, Types.BOOLEAN);
            cs.setString(2, id);
            cs.execute();
            return cs.getBoolean(1);
        } catch (SQLException e) {
            if (Helpers.DEBUG) {
                // e.printStackTrace();
                System.out.println("Error running procedure: " + e.getMessage());
            }
        } finally {
            DatabaseManager.close(cs);
            DatabaseManager.close(conn);
        }
        return false;
    }
    
    public static Session getSessionInfo(String usuario){
        Connection conn = DatabaseManager.connect();
        if (conn == null) {
            return null;
        }
        PreparedStatement query;
        Session session = null;
        try {
            query = conn.prepareStatement(SESSIONINFO);
            query.setString(1, usuario);
            Object[] o = getResult(query);
            User u = new User(o[1].toString());
            u.setExterno((Boolean)o[2]);
            Type et = new Type((Integer)o[3], o[4].toString());
            session = new Session(o[0].toString(), u, et);
            
        } catch (SQLException e) {
            if(Helpers.DEBUG){
                e.printStackTrace();
            }
            return null;
        }
        
        return session;
    }
    
    public static Object[] getResult(PreparedStatement query){
        Connection conn = DatabaseManager.connect();
        Statement st = null;
        ResultSet rs = null;
        Object[] o = new Object[0];
        if (conn == null) {
            return o;
        }
        try {
            rs = query.executeQuery();
            
            ResultSetMetaData rsmd = rs.getMetaData();
            int ncols = rsmd.getColumnCount();
            o = new Object[ncols];

            if(rs.next()){
                for(int i = 0; i < ncols; i++){
                    o[i] = rs.getObject(i+1);
                }
            }
            return o;

        } catch (SQLException sqle) {
            if (Helpers.DEBUG) {
                // e.printStackTrace();
                System.out.println("Error getting objects: "
                        + sqle.getMessage());
            }
        } finally {
            DatabaseManager.close(rs);
            DatabaseManager.close(st);
            DatabaseManager.close(conn);
        }
        return o;
    }
    
    public static Object[][] getResults(PreparedStatement rowcount, 
            PreparedStatement query){
        Connection conn = DatabaseManager.connect();
        Statement st = null;
        ResultSet rs = null;
        Object[][] o = new Object[0][0];
        if (conn == null) {
            return o;
        }
        try {
            ResultSet rsc = rowcount.executeQuery();
            rs = query.executeQuery();

            rsc.next();
            int rows = rsc.getInt(1);
            if(rows <= 0){
                return o;
            }
            
            ResultSetMetaData rsmd = rs.getMetaData();
            int ncols = rsmd.getColumnCount();

            o = new Object[rows][ncols];
            int rown = 0;
            while(rs.next()){
                for(int i = 0; i < ncols; i++){
                    o[rown][i] = rs.getObject(i+1);
                }
                rown++;
            }
            return o;

        } catch (SQLException sqle) {
            if (Helpers.DEBUG) {
                // e.printStackTrace();
                System.out.println("Error getting objects: "
                        + sqle.getMessage());
            }
        } finally {
            DatabaseManager.close(rs);
            DatabaseManager.close(st);
            DatabaseManager.close(conn);
        }
        return o;
    }

    public static boolean manageType(Type et, String call){
        if (et == null) {
            if (Helpers.DEBUG) {
                throw new NullPointerException("Type nulo");
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
            cs = conn.prepareCall(call);
            cs.registerOutParameter(1, Types.BOOLEAN);

            cs.setString(2, et.getType());

            cs.execute();
            return cs.getBoolean(1);
        } catch (SQLException sqle) {
            if (Helpers.DEBUG) {
                // e.printStackTrace();
                System.out.println("Error inserting employee type: " 
                        + sqle.getMessage());
            }
        } finally {
            DatabaseManager.close(cs);
            DatabaseManager.close(conn);
        }
        return false;
    }

    public static boolean login(User u) {
        boolean authorized = false;

        Connection conn = DatabaseManager.connect();
        if (conn == null) {
            return false;
        }
        CallableStatement cs = null;
        try {
            cs = conn.prepareCall(AUTH);
            cs.registerOutParameter(1, Types.BOOLEAN);
            cs.setString(2, u.getUsername());
            cs.setString(3, u.getPassword());

            cs.execute();
            authorized = cs.getBoolean(1);
        } catch (SQLException sqle) {
            if (Helpers.DEBUG) {
                sqle.printStackTrace();
                System.out.println("Error authorizing: " + sqle.getMessage());
            }
        } finally {
            DatabaseManager.close(cs);
            DatabaseManager.close(conn);
        }

        return authorized;
    }

    public static List<String> getCountries() {
        return getList(GETCOUNTRIES);
    }

    public static List<String> getStates() {
        return getList(GETSTATES);
    }

    public static List<Type> getTypes(String sql) {
        Connection conn = DatabaseManager.connect();
        if (conn == null) {
            return null;
        }
        List<Type> types = new LinkedList<Type>();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                types.add(new Type(rs.getInt(1), rs.getString(2)));
            }

        } catch (SQLException sqle) {
            if (Helpers.DEBUG) {
                sqle.printStackTrace();
                System.out.println("Error getting types: "
                        + sqle.getMessage());
            }
        } finally {
            DatabaseManager.close(pstmt);
            DatabaseManager.close(conn);
        }

        return types;
    }

    private static List<String> getList(String query) {
        Connection conn = DatabaseManager.connect();
        if (conn == null) {
            return null;
        }
        List<String> types = new LinkedList<String>();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                types.add(rs.getString(1));
            }

        } catch (SQLException sqle) {
            if (Helpers.DEBUG) {
                sqle.printStackTrace();
                System.out.println("Error getting types: " + sqle.getMessage());
            }
        } finally {
            DatabaseManager.close(pstmt);
            DatabaseManager.close(conn);
        }

        return types;
    }
}
