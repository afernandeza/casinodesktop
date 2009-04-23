package gamesmanager.beans;

public class Casino {

    private String casinoid;
    private String jdbcurl;
    private int latestsyncedid;
    private boolean available;

    public Casino(String casinoid, String jdbcurl) {
        super();
        this.casinoid = casinoid;
        this.jdbcurl = jdbcurl;
        this.latestsyncedid = 0;
        this.available = false;
    }
    
    public Casino(String casinoid, String jdbcurl, Integer latestsyncedid) {
        super();
        this.casinoid = casinoid;
        this.jdbcurl = jdbcurl;
        this.latestsyncedid = latestsyncedid;
        this.available = false;
    }
    
    public String getCasinoid() {
        return casinoid;
    }
    public void setCasinoid(String casinoid) {
        this.casinoid = casinoid;
    }
    public String getJdbcurl() {
        return jdbcurl;
    }
    public void setJdbcurl(String jdbcurl) {
        this.jdbcurl = jdbcurl;
    }
    public int getLatestsyncedid() {
        return latestsyncedid;
    }
    public void setLatestsyncedid(int latestsyncedid) {
        this.latestsyncedid = latestsyncedid;
    }

    public String toString(){
        final String TAB = ", ";
        
        StringBuffer retValue = new StringBuffer();
        
        retValue.append("Casino ( ")
            .append(super.toString()).append(TAB)
            .append("casinoid = ").append(this.casinoid).append(TAB)
            .append("jdbcurl = ").append(this.jdbcurl).append(TAB)
            .append("latestsyncedid = ").append(this.latestsyncedid).append(TAB)
            .append(" )");
        
        return retValue.toString();
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    
}
