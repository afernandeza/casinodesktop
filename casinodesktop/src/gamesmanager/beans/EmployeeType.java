package gamesmanager.beans;

public class EmployeeType {

    private final int typeid;
    private final String type;
    
    public EmployeeType(int typeid){
        this.typeid = typeid;
        type = "";
    }

    public EmployeeType(int typeid, String type) {
        this.typeid = typeid;
        this.type = type;
    }

    public int getTypeid() {
        return typeid;
    }

    public String getType() {
        return type;
    }

    public String toString() {
        final String TAB = ", ";

        StringBuffer retValue = new StringBuffer();

        retValue.append("EmployeeType ( ").append(super.toString()).append(TAB)
                .append("typeid = ").append(this.typeid).append(TAB).append(
                        "type = ").append(this.type).append(TAB).append(" )");

        return retValue.toString();
    }
}
