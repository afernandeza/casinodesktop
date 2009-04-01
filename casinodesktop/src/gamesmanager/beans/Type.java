package gamesmanager.beans;

public class Type {

    protected final int typeid;
    protected final String type;
    
    public Type(int typeid){
        this.typeid = typeid;
        type = "";
    }
    
    public Type(String type){
        typeid = -1;
        this.type = type;
    }

    public Type(int typeid, String type) {
        this.typeid = typeid;
        this.type = type;
    }

    public int getTypeid() {
        return typeid;
    }

    public String getType() {
        return type;
    }

}
