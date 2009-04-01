package gamesmanager.beans;

public class EmployeeType extends Type{

    public EmployeeType(int typeid){
        super(typeid);
    }
    
    public EmployeeType(String type){
        super(type);
    }

    public EmployeeType(int typeid, String type) {
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
