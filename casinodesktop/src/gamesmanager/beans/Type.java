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
    
    @Override
    public String toString(){
        return this.type;
    }
    
    @Override
    public boolean equals(Object o){
        if(o == null)
            return false;
        if(!(o instanceof Type))
            return false;
        Type t = (Type)o;
        return this.type.equals(t.getType()) && this.typeid == t.getTypeid();
    }

}
