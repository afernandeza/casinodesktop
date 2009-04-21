package gamesmanager.db;

import gamesmanager.beans.Address;
import gamesmanager.beans.Client;
import gamesmanager.beans.ExternalClient;
import gamesmanager.ui.Helpers;

import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class ClientManager {

    private static String INSERT = "{? = call insertclient(?, ?, ?, ?, ?, ?, ?,"
            + "?, ?, ?, ?, ?, ?, ?, ?, ?)}";
    private static String UPDATE = "{? = call updateclient(?, ?, ?, ?, ?, ?, ?,"
            + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
    private static String DELETE = "{? = call deleteclient(?)}";
    private static String FIND = "SELECT * FROM findclient(?)";
    private static String FINDEXTERNALCLIENT = "SELECT * FROM findexternalclient(?)";
    private static String EDITCREDIT = "{? = call editcredit(?, ?)}";
    private static String CLIENTTYPE = "{? = call clienttype(?)}";

    private static ExternalClient getExternalClient(String cid) {
        Connection conn = DatabaseManager.connect();
        PreparedStatement pstmt = null;
        if (conn == null) {
            return null;
        }
        ExternalClient c = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(FINDEXTERNALCLIENT);
            pstmt.setString(1, cid);
            rs = pstmt.executeQuery();
            rs.next();
            String casinoid = rs.getString(1);
            if (casinoid == null) {
                return null;
            }
            c = new ExternalClient(casinoid, rs.getString(2), rs.getString(3),
                    rs.getString(4));

        } catch (SQLException e) {
            if (Helpers.DEBUG) {
                // e.printStackTrace();
                System.out.println("Error finding external client: "
                        + e.getMessage());
            }
        } finally {
            DatabaseManager.close(rs);
            DatabaseManager.close(pstmt);
            DatabaseManager.close(conn);
        }
        return c;
    }

    public static Client findClient(String cid) {
        int type = getClientType(cid);
        if (type == -1 || type == 3) {
            return null;
        } else if (type == 1) {
            // this is a local client
            return getClient(cid);
        } else if (type == 2) {
            // this is an external client
            return getExternalClient(cid);
        } else {
            // unknown
            return null;
        }

    }

    public static int getClientType(String cid) {
        Connection conn = DatabaseManager.connect();
        CallableStatement cs = null;
        if (conn == null) {
            return -1;
        }
        try {
            cs = conn.prepareCall(CLIENTTYPE);
            cs.registerOutParameter(1, Types.INTEGER);

            cs.setString(2, cid);

            cs.execute();
            return cs.getInt(1);
        } catch (SQLException e) {
            if (Helpers.DEBUG) {
                // e.printStackTrace();
                System.out.println("Error getting client type: "
                        + e.getMessage());
            }
        } finally {
            DatabaseManager.close(cs);
            DatabaseManager.close(conn);
        }
        return -1;
    }

    public static boolean editCredit(String clientid, String amount) {
        BigDecimal bd = null;
        try {
            bd = new BigDecimal(amount);
        } catch (NumberFormatException mfe) {
            if (Helpers.DEBUG) {
                mfe.printStackTrace();
            } else {
                return false;
            }
        }
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return editCredit(clientid, bd);

    }

    public static boolean editCredit(String clientid, double amount) {
        BigDecimal bd = null;
        try {
            bd = new BigDecimal(amount);
        } catch (NumberFormatException mfe) {
            if (Helpers.DEBUG) {
                mfe.printStackTrace();
            } else {
                return false;
            }
        }
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return editCredit(clientid, bd);
    }

    public static boolean editCredit(String clientid, BigDecimal amount) {
        if (clientid == null) {
            if (Helpers.DEBUG) {
                throw new NullPointerException("clientid nulo");
            } else {
                return false;
            }
        }
        if (clientid.equals("")) {
            if (Helpers.DEBUG) {
                throw new IllegalArgumentException("clientid vacio");
            } else {
                return false;
            }
        }
        if (!(amount instanceof BigDecimal)) {
            if (Helpers.DEBUG) {
                throw new IllegalArgumentException("cantidad incorrecta");
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
            cs = conn.prepareCall(EDITCREDIT);
            cs.registerOutParameter(1, Types.BOOLEAN);

            cs.setString(2, clientid);
            cs.setBigDecimal(3, amount);

            cs.execute();
            return cs.getBoolean(1);
        } catch (SQLException e) {
            if (Helpers.DEBUG) {
                // e.printStackTrace();
                System.out.println("Error updating credit: " + e.getMessage());
            }
        } finally {
            DatabaseManager.close(cs);
            DatabaseManager.close(conn);
        }
        return false;
    }

    public static boolean insertClient(Client c) {
        if (c == null) {
            if (Helpers.DEBUG) {
                throw new NullPointerException("Cliente nulo");
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

            cs.setBigDecimal(2, c.getCredito());
            cs.setString(3, c.getNombres());
            cs.setString(4, c.getAppaterno());
            cs.setString(5, c.getApmaterno());
            cs.setString(6, c.getSexo() + "");
            cs.setDate(7, new Date(c.getFechanac().getTime()));

            File foto = c.getSelectedFoto();
            InputStream is = c.getNewFotoInputStream();

            if (is != null) {
                cs.setBinaryStream(8, is, (int) foto.length());
            } else {
                if (Helpers.DEBUG) {
                    throw new NullPointerException("Foto es null");
                }
            }

            cs.setString(9, c.getTelcasa());
            cs.setString(10, c.getTelcel());

            Address d = c.getAddress();
            cs.setString(11, d.getCallenum());
            cs.setString(12, d.getNumint());
            cs.setString(13, d.getColonia());
            cs.setString(14, d.getMunicipio());
            cs.setString(15, d.getCodigopostal());
            cs.setString(16, d.getEstado());
            cs.setString(17, d.getPais());

            cs.execute();
            return cs.getBoolean(1);
        } catch (SQLException e) {
            if (Helpers.DEBUG) {
                // e.printStackTrace();
                System.out.println("Error inserting client: " + e.getMessage());
            }
        } finally {
            DatabaseManager.close(cs);
            DatabaseManager.close(conn);
        }
        return false;
    }

    public static boolean updateClient(Client c) {
        if (c == null) {
            if (Helpers.DEBUG) {
                throw new NullPointerException("Cliente nulo");
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

            cs.setString(2, c.getId());
            cs.setBigDecimal(3, c.getCredito());
            cs.setString(4, c.getNombres());
            cs.setString(5, c.getAppaterno());
            cs.setString(6, c.getApmaterno());
            cs.setString(7, c.getSexo() + "");
            cs.setDate(8, new Date(c.getFechanac().getTime()));

            InputStream is = c.getNewFotoInputStream();
            if (is != null) {
                cs.setBinaryStream(9, is, c.getFotoSize());
            } else {
                throw new NullPointerException("Foto es null");
            }

            cs.setString(10, c.getTelcasa());
            cs.setString(11, c.getTelcel());

            Address d = c.getAddress();
            cs.setString(12, d.getCallenum());
            cs.setString(13, d.getNumint());
            cs.setString(14, d.getColonia());
            cs.setString(15, d.getMunicipio());
            cs.setString(16, d.getCodigopostal());
            cs.setString(17, d.getEstado());
            cs.setString(18, d.getPais());

            cs.execute();
            return cs.getBoolean(1);
        } catch (SQLException e) {
            if (Helpers.DEBUG) {
                // e.printStackTrace();
                System.out.println("Error updating client: " + e.getMessage());
            }
        } finally {
            DatabaseManager.close(cs);
            DatabaseManager.close(conn);
        }
        return false;
    }

    public static Client getClient(String id) {
        if (id == null) {
            if (Helpers.DEBUG) {
                throw new NullPointerException("clientid nulo");
            }
        }
        if (id.equals("")) {
            if (Helpers.DEBUG) {
                throw new IllegalArgumentException("clientid vacio");
            }
        }
        Connection conn = DatabaseManager.connect();
        PreparedStatement pstmt = null;
        if (conn == null) {
            return null;
        }
        Client c = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(FIND);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            rs.next();
            if (rs.getString(1) == null) {
                return null;
            }
            c = new Client();
            c.setId(rs.getString(1));
            c.setNombres(rs.getString(2));
            c.setAppaterno(rs.getString(3));
            c.setApmaterno(rs.getString(4));
            c.setSexo(rs.getString(5));
            c.setFechanac(rs.getDate(6));

            c.setFoto(rs.getBytes(7));

            c.setTelcasa(rs.getString(8));
            c.setTelcel(rs.getString(9));
            c.setMembersince(rs.getString(10));

            Address a = new Address();
            a.setAddressid(rs.getInt(11));
            a.setCallenum(rs.getString(12));
            a.setNumint(rs.getString(13));
            a.setColonia(rs.getString(14));
            a.setMunicipio(rs.getString(15));
            a.setCodigopostal(rs.getString(16));
            a.setEstado(rs.getString(17));
            a.setPais(rs.getString(18));
            c.setAddress(a);

            c.setCredito(rs.getBigDecimal(19));
        } catch (SQLException e) {
            if (Helpers.DEBUG) {
                // e.printStackTrace();
                System.out.println("Error finding client: " + e.getMessage());
            }
        } finally {
            DatabaseManager.close(rs);
            DatabaseManager.close(pstmt);
            DatabaseManager.close(conn);
        }
        return c;
    }

    public static boolean deleteClient(Client c) {
        if (c == null) {
            if (Helpers.DEBUG) {
                throw new NullPointerException("Cliente nulo");
            } else {
                return false;
            }
        }
        return deleteClient(c.getId());
    }

    public static boolean deleteClient(String clientid) {
        return DatabaseOperations.runStoredProcedure(clientid, DELETE);
    }
}
