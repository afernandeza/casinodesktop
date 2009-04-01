package gamesmanager.beans;

public class GameType extends Type{

    public GameType(int typeid){
        super(typeid);
    }

    public GameType(String type){
        super(type);
    }

    public GameType(int typeid, String type) {
        super(typeid, type);
    }

    @Override
    public String toString() {
        final String TAB = ", ";

        StringBuffer retValue = new StringBuffer();

        retValue.append("EmployeeType ( ")
                .append("typeid = ").append(this.typeid).append(TAB).append(
                        "type = ").append(this.type).append(TAB).append(" )");

        return retValue.toString();
    }
}
