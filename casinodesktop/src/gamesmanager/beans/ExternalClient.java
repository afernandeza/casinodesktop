package gamesmanager.beans;

public class ExternalClient extends Client {

    private String casinoid;

    public ExternalClient(String casinoid, String clientid) {
        super();
        this.setId(clientid);
        this.casinoid = casinoid;
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
