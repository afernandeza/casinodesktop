package gamesmanager.beans;

public class GameSession {

    private int sessionid;
    private int tableid;
    private double fichasinicio;
    private double fichasfin;
    private String empleadoid;
    private String timeopened;
    private String timeclosed;
    
    public int getSessionid() {
        return sessionid;
    }
    public void setSessionid(int sessionid) {
        this.sessionid = sessionid;
    }
    public int getTableid() {
        return tableid;
    }
    public void setTableid(int tableid) {
        this.tableid = tableid;
    }
    public double getFichasinicio() {
        return fichasinicio;
    }
    public void setFichasinicio(double fichasinicio) {
        this.fichasinicio = fichasinicio;
    }
    public double getFichasfin() {
        return fichasfin;
    }
    public void setFichasfin(double fichasfin) {
        this.fichasfin = fichasfin;
    }
    public String getEmpleadoid() {
        return empleadoid;
    }
    public void setEmpleadoid(String empleadoid) {
        this.empleadoid = empleadoid;
    }
    public String getTimeopened() {
        return timeopened;
    }
    public void setTimeopened(String timeopened) {
        this.timeopened = timeopened;
    }
    public String getTimeclosed() {
        return timeclosed;
    }
    public void setTimeclosed(String timeclosed) {
        this.timeclosed = timeclosed;
    }
    
    
    
}
