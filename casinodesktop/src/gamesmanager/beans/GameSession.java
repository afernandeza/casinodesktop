package gamesmanager.beans;

import java.math.BigDecimal;

public class GameSession {

    private int sessionid;
    private int tableid;
    private BigDecimal fichasinicio;
    private BigDecimal fichasfin;
    private String empleadoid;
    private String timeopened;
    private String timeclosed;

    public GameSession(){
    }
    
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

    public BigDecimal getFichasinicio() {
        return fichasinicio;
    }

    public void setFichasinicio(double fichasinicio) {
        BigDecimal bd = new BigDecimal(fichasinicio);
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.fichasinicio = bd;
    }

    public BigDecimal getFichasfin() {
        return fichasfin;
    }

    public void setFichasfin(double fichasfin) {
        BigDecimal bd = new BigDecimal(fichasfin);
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.fichasfin = bd;
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
