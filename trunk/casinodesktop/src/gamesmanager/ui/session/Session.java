package gamesmanager.ui.session;

import gamesmanager.beans.Type;
import gamesmanager.beans.User;

public class Session {

    private static Session session;

    private final String empid;
    private final User u;
    private final Type et;

    public Session(String empid, User u, Type et) {
        this.empid = empid;
        this.u = u;
        this.et = et;
    }

    public boolean belongsTo(String empid) {
        return this.empid.equals(empid);
    }

    public User getU() {
        return u;
    }

    public Type getEt() {
        return et;
    }

    public static void setCurrentSession(Session s) {
        Session.session = s;
    }

    public String toString() {
        final String TAB = ", ";

        StringBuffer retValue = new StringBuffer();

        retValue.append("Session ( ").append(super.toString()).append(TAB)
                .append("empid = ").append(this.empid).append(TAB).append(
                        "u = ").append(this.u).append(TAB).append("et = ")
                .append(this.et).append(TAB).append(" )");

        return retValue.toString();
    }

    public static boolean mayDeactivateAccount(String empid) {
        // administradores, gerentes y duenio
        if (session.et.getTypeid() <= 2 || session.empid.equals(empid)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean mayActivateAccount() {
        // administradores, gerentes
        if (session.et.getTypeid() <= 2) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean mayTemporarilyFire() {
        // administradores y gerentes
        if (session.et.getTypeid() <= 2) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean mayPermanentlyFire() {
        // Solo administradores
        return session.et.getTypeid() == 1;
    }

    public static boolean mayChangePasswordFor(String empid) {
        // administradores, gerentes y el duenio
        if (session.et.getTypeid() <= 2 || session.empid.equals(empid)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean mayChangePersonalInfoFor(String empid) {
        // administradores, gerentes y el duenio
        if (session.et.getTypeid() <= 2 || session.empid.equals(empid)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean mayHire() {
        // administradores y gerentes
        if (session.et.getTypeid() <= 2) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean mayDeleteGameTable() {
        // administradores y gerentes
        if (session.et.getTypeid() <= 2) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean mayUpdateGameTable() {
        // administradores y gerentes
        if (session.et.getTypeid() <= 2) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean mayChangeUserType() {
        // administradores y gerentes
        if (session.et.getTypeid() <= 2) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean mayOpenGameSession() {
        // administradores y gerentes
        if (session.et.getTypeid() <= 2) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean mayManageCasinos() {
        // administradores y gerentes
        if (session.et.getTypeid() <= 2) {
            return true;
        } else {
            return false;
        }
    }
}
