package gamesmanager.beans;

public class ExternalClient extends Client {

    private String casinoid;

    public ExternalClient(String casinoid, String clientid, String credito, String nombre) {
        super();
        this.setId(clientid);
        this.setNombres(nombre);
        this.setCredito(credito);
        this.casinoid = casinoid;
    }

    @Override
    public String getFullName(){
        return this.getId() + " : " + this.getNombres();
    }
    
    public String getCasinoid() {
        return casinoid;
    }

    public void setCasinoid(String casinoid) {
        this.casinoid = casinoid;
    }

    @Override
    public boolean isLocal() {
        return false;
    }

    @Override
    public String getSucursal() {
        return this.casinoid;
    }

    public String toString() {
        final String TAB = ", ";

        StringBuffer retValue = new StringBuffer();

        retValue.append("ExternalClient ( ").append(super.toString()).append(
                TAB).append("casinoid = ").append(this.casinoid).append(TAB)
                .append(" )");

        return retValue.toString();
    }

}
