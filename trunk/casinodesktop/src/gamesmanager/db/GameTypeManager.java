package gamesmanager.db;

import java.util.List;

import gamesmanager.beans.EmployeeType;
import gamesmanager.beans.Type;

public class GameTypeManager {
    
    private static String INSERTGAMETYPE = "{? = call insertgametype(?)}";
    private static String DELETEGAMETYPE = "{? = call deletegametype(?)}";
    private static String GETGAMETYPES = "SELECT * "
        + "FROM tiposjuego ORDER BY tipo";
    
    public static boolean insertGameType(EmployeeType et){
        return DatabaseOperations.manageType(et, INSERTGAMETYPE);
    }
    
    public static boolean insertGameType(String s){
        EmployeeType et = new EmployeeType(s);
        return insertGameType(et);
    }
    
    public static boolean deleteGameType(EmployeeType et){
        return DatabaseOperations.manageType(et, DELETEGAMETYPE);
    }
    
    public static boolean deleteGameType(String s){
        EmployeeType et = new EmployeeType(s);
        return deleteGameType(et);
    }
    
    public static List<Type> getGameTypes() {
        return DatabaseOperations.getTypes(GETGAMETYPES);
    }
    
}
