package gamesmanager.db;

import gamesmanager.beans.Address;
import gamesmanager.beans.Employee;
import gamesmanager.beans.Type;
import gamesmanager.beans.User;
import gamesmanager.ui.Helpers;

import java.io.File;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class EmployeeManager {

    private static String INSERT = "{? = call insertemployee(?, ?, ?, ?, ?, ?, ?,"
            + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
    private static String UPDATE = "{? = call updateemployee(?, ?, ?, ?, ?, ?, ?,"
            + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
    private static String FIND = "SELECT * FROM findemployee(?)";

    private static String DEACTIVATEACCOUNT = "{? = call deactivateaccount(?)}";
    private static String REACTIVATEACCOUNT = "{? = call reactivateaccount(?)}";
    private static String FIRETEMPORARILY = "{? = call firetemporarily(?)}";
    private static String REHIRE = "{? = call rehire(?)}";
    private static String USREXISTS = "{? = call userexists(?)}";

    private static String GETEMPLOYEETYPES = "SELECT * FROM tipoempleados "
            + "ORDER BY tipo";

    public static String GETEMPSUMMARY = "SELECT * FROM employeessummary ORDER BY nombre";
    public static String EMPSUMMARYCOUNT = "SELECT COUNT(*) from employeessummary";
    
    public static String GETAVEMPSUMMARY = "SELECT * FROM availableemployees ORDER BY nombre";
    public static String EMPAVSUMMARYCOUNT = "SELECT COUNT(*) from availableemployees";

    public static String SEARCH = "SELECT * from searchemployees(?)";
    public static String SEARCHCOUNT = "SELECT COUNT(*) from searchemployees(?)";

    public static String[] EMPLOYEECOLUMNS = { "ID", "Tipo", "Nombre",
            "Usuario", "Us. activo", "Fecha alta", "Tel. Casa", "Tel. Celular",
            "Despedido", };

    public static boolean userExists(String usr){
        return DatabaseOperations.runStoredProcedure(usr, USREXISTS);
    }
    
    public static boolean deactivateAccount(String eid) {
        return DatabaseOperations.runStoredProcedure(eid, DEACTIVATEACCOUNT);
    }

    public static boolean reactivateAccount(String eid) {
        return DatabaseOperations.runStoredProcedure(eid, REACTIVATEACCOUNT);
    }

    public static boolean fireTemporarily(String eid) {
        return DatabaseOperations.runStoredProcedure(eid, FIRETEMPORARILY);
    }

    public static boolean rehire(String eid) {
        return DatabaseOperations.runStoredProcedure(eid, REHIRE);
    }

    public static Object[][] searchEmployees(String s) {
        Connection conn = DatabaseManager.connect();
        if (conn == null) {
            return new Object[0][0];
        }
        PreparedStatement rowcount;
        PreparedStatement query;
        try {
            rowcount = conn.prepareStatement(SEARCHCOUNT);
            rowcount.setString(1, s);
            query = conn.prepareStatement(SEARCH);
            query.setString(1, s);
        } catch (SQLException e) {
            if (Helpers.DEBUG) {
                e.printStackTrace();
            }
            return new Object[0][0];
        }

        return DatabaseOperations.getResults(rowcount, query);
    }

    public static Employee[] getEmployeeNames() {
        Object[][] o = getEmployeesSummary();
        Employee[] s = new Employee[o.length];
        for (int i = 0; i < o.length; i++) {
            Employee e = new Employee();
            e.setId(o[i][0].toString());
            e.setNombres(o[i][2].toString());
            s[i] = e;
        }
        return s;
    }

    public static Employee[] getAvailableEmployeeNames() {
        Object[][] o = getAvailableEmployeesSummary();
        Employee[] s = new Employee[o.length];
        for (int i = 0; i < o.length; i++) {
            Employee e = new Employee();
            e.setId(o[i][0].toString());
            e.setNombres(o[i][1].toString());
            s[i] = e;
        }
        return s;
    }
    
    public static Object[][] getEmployeesSummary() {
        Connection conn = DatabaseManager.connect();
        if (conn == null) {
            return new Object[0][0];
        }
        PreparedStatement rowcount;
        PreparedStatement query;
        try {
            rowcount = conn.prepareStatement(EMPSUMMARYCOUNT);
            query = conn.prepareStatement(GETEMPSUMMARY);
        } catch (SQLException e) {
            if (Helpers.DEBUG) {
                e.printStackTrace();
            }
            return new Object[0][0];
        }

        return DatabaseOperations.getResults(rowcount, query);
    }
    
    public static Object[][] getAvailableEmployeesSummary() {
        Connection conn = DatabaseManager.connect();
        if (conn == null) {
            return new Object[0][0];
        }
        PreparedStatement rowcount;
        PreparedStatement query;
        try {
            rowcount = conn.prepareStatement(EMPAVSUMMARYCOUNT);
            query = conn.prepareStatement(GETAVEMPSUMMARY);
        } catch (SQLException e) {
            if (Helpers.DEBUG) {
                e.printStackTrace();
            }
            return new Object[0][0];
        }

        return DatabaseOperations.getResults(rowcount, query);
    }

    public static List<Type> getEmployeeTypes() {
        return DatabaseOperations.getTypes(GETEMPLOYEETYPES);
    }

    public static boolean insertEmployee(Employee e) {
        if (e == null) {
            if (Helpers.DEBUG) {
                throw new NullPointerException("Empleado nulo");
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

            cs.setString(2, e.getNombres());
            cs.setString(3, e.getAppaterno());
            cs.setString(4, e.getApmaterno());
            cs.setString(5, e.getSexo() + "");
            cs.setDate(6, new Date(e.getFechanac().getTime()));

            File foto = e.getSelectedFoto();
            InputStream is = e.getNewFotoInputStream();

            if (is != null) {
                cs.setBinaryStream(7, is, (int) foto.length());
            } else {
                if (Helpers.DEBUG) {
                    throw new NullPointerException("Foto es null");
                }
            }

            cs.setString(8, e.getTelcasa());
            cs.setString(9, e.getTelcel());

            Address d = e.getAddress();
            cs.setString(10, d.getCallenum());
            cs.setString(11, d.getNumint());
            cs.setString(12, d.getColonia());
            cs.setString(13, d.getMunicipio());
            cs.setString(14, d.getCodigopostal());
            cs.setString(15, d.getEstado());
            cs.setString(16, d.getPais());

            User u = e.getUser();
            cs.setString(17, u.getUsername());
            cs.setString(18, u.getPassword());

            Type et = e.getEmployeetype();
            cs.setInt(19, et.getTypeid());

            cs.execute();
            return cs.getBoolean(1);
        } catch (SQLException sqle) {
            if (Helpers.DEBUG) {
                // e.printStackTrace();
                System.out.println("Error inserting employee: "
                        + sqle.getMessage());
            }
        } finally {
            DatabaseManager.close(cs);
            DatabaseManager.close(conn);
        }
        return false;
    }

    public static Employee findEmployee(String id) {
        if (id == null) {
            if (Helpers.DEBUG) {
                throw new NullPointerException("employeeid nulo");
            }
        }
        if (id.equals("")) {
            if (Helpers.DEBUG) {
                throw new IllegalArgumentException("employeeid vacio");
            }
        }
        Connection conn = DatabaseManager.connect();
        if (conn == null) {
            return null;
        }
        PreparedStatement pstmt = null;
        Employee e = null;
        try {
            pstmt = conn.prepareStatement(FIND);
            pstmt.setString(1, id);

            ResultSet rs = pstmt.executeQuery();
            rs.next();

            String eid = rs.getString(1);
            if (eid == null) {
                return null;
            }

            e = new Employee();
            e.setId(eid);
            e.setNombres(rs.getString(2));
            e.setAppaterno(rs.getString(3));
            String appaterno = null;
            if ((appaterno = rs.getString(4)) != null) {
                if (!appaterno.trim().equals("")) {
                    e.setApmaterno(appaterno);
                }
            }
            e.setSexo(rs.getString(5));
            e.setFechanac(rs.getDate(6));

            e.setFoto(rs.getBytes(7));

            e.setTelcasa(rs.getString(8));
            e.setTelcel(rs.getString(9));
            e.setHired(rs.getDate(10));
            e.setFired(rs.getDate(11));

            // Date despedido;
            // if((despedido = rs.getDate(11)) != null){
            // e.setFired(despedido);
            // }

            Address a = new Address();
            a.setAddressid(rs.getInt(12));
            a.setCallenum(rs.getString(13));
            a.setNumint(rs.getString(14));
            a.setColonia(rs.getString(15));
            a.setMunicipio(rs.getString(16));
            a.setCodigopostal(rs.getString(17));
            a.setEstado(rs.getString(18));
            a.setPais(rs.getString(19));

            e.setAddress(a);
            User u = new User(rs.getInt(20), rs.getString(21),
                    rs.getString(22), rs.getBoolean(23));
            u.setExterno(rs.getBoolean(24));
            e.setUser(u);

            Type et = new Type(rs.getInt(25), rs.getString(26));
            e.setEmployeetype(et);
        } catch (SQLException sqle) {
            if (Helpers.DEBUG) {
                sqle.printStackTrace();
                System.out.println("Error finding employee: "
                        + sqle.getMessage());
            }
        } finally {
            DatabaseManager.close(pstmt);
            DatabaseManager.close(conn);
        }
        return e;
    }

    public static boolean updateEmployee(Employee e) {
        if (e == null) {
            if (Helpers.DEBUG) {
                throw new NullPointerException("Empleado nulo");
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

            cs.setString(2, e.getId());
            cs.setString(3, e.getNombres());
            cs.setString(4, e.getAppaterno());
            cs.setString(5, e.getApmaterno());
            cs.setString(6, e.getSexo() + "");
            cs.setDate(7, new Date(e.getFechanac().getTime()));

            InputStream is = e.getNewFotoInputStream();
            if (is != null) {
                cs.setBinaryStream(8, is, e.getFotoSize());
            } else {
                throw new NullPointerException("Foto es null");
            }

            cs.setString(9, e.getTelcasa());
            cs.setString(10, e.getTelcel());

            Address d = e.getAddress();
            cs.setString(11, d.getCallenum());
            cs.setString(12, d.getNumint());
            cs.setString(13, d.getColonia());
            cs.setString(14, d.getMunicipio());
            cs.setString(15, d.getCodigopostal());
            cs.setString(16, d.getEstado());
            cs.setString(17, d.getPais());

            User u = e.getUser();
            cs.setString(18, u.getPassword());

            Type et = e.getEmployeetype();
            cs.setInt(19, et.getTypeid());

            cs.execute();
            return cs.getBoolean(1);
        } catch (SQLException sqle) {
            if (Helpers.DEBUG) {
                // e.printStackTrace();
                System.out.println("Error updating employee: "
                        + sqle.getMessage());
            }
        } finally {
            DatabaseManager.close(cs);
            DatabaseManager.close(conn);
        }
        return false;
    }
}
