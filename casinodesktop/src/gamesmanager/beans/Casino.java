package gamesmanager.beans;

public class Casino {

    private String casinoid;
    private String ip;
    private int latestsyncedid;
    private boolean available;
    private boolean updatedid;

    public Casino(String casinoid, String ip) {
        super();
        this.casinoid = casinoid;
        this.ip = ip;
        this.latestsyncedid = 0;
        this.available = false;
    }
    
    public Casino(String casinoid, String ip, Integer latestsyncedid) {
        super();
        this.casinoid = casinoid;
        this.ip = ip;
        this.latestsyncedid = latestsyncedid;
        this.available = false;
        updatedid = true;
    }
    
    public String getCasinoid() {
        return casinoid;
    }
    public void setCasinoid(String casinoid) {
        this.casinoid = casinoid;
    }
    public int getLatestsyncedid() {
        if(!updatedid){
            throw new NullPointerException("No se ha definido el latestsyncedid");
        }
        return latestsyncedid;
    }
    public void setLatestsyncedid(int latestsyncedid) {
        this.latestsyncedid = latestsyncedid;
        updatedid = true;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * Constructs a <code>String</code> with all attributes
     * in name = value format.
     *
     * @return a <code>String</code> representation 
     * of this object.
     */
    public String toString(){
        final String TAB = ", ";
        
        StringBuffer retValue = new StringBuffer();
        
        retValue.append("Casino ( ")
            .append(super.toString()).append(TAB)
            .append("casinoid = ").append(this.casinoid).append(TAB)
            .append("ip = ").append(this.ip).append(TAB)
            .append("latestsyncedid = ").append(this.latestsyncedid).append(TAB)
            .append("available = ").append(this.available).append(TAB)
            .append(" )");
        
        return retValue.toString();
    }
    
}
