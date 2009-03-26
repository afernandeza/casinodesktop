package gamesmanager.beans;

public class Address {

    private int addressid;
    private String callenum;
    private String numint;
    private String colonia;
    private String municipio;
    private String codigopostal;
    private String estado;
    private String pais;

    public Address() {
    }

    public Address(int addressid, String callenum, String numint,
            String colonia, String municipio, String codigopostal,
            String estado, String pais) {
        super();
        this.addressid = addressid;
        this.callenum = callenum;
        this.numint = numint;
        this.colonia = colonia;
        this.municipio = municipio;
        this.codigopostal = codigopostal;
        this.estado = estado;
        this.pais = pais;
    }

    public Address(String callenum, String numint, String colonia,
            String municipio, String codigopostal, String estado, String pais) {
        super();
        this.callenum = callenum;
        this.numint = numint;
        this.colonia = colonia;
        this.municipio = municipio;
        this.codigopostal = codigopostal;
        this.estado = estado;
        this.pais = pais;
    }

    public int getAddressid() {
        return addressid;
    }

    public void setAddressid(int addressid) {
        this.addressid = addressid;
    }

    public String getCallenum() {
        return callenum;
    }

    public void setCallenum(String callenum) {
        this.callenum = callenum;
    }

    public String getNumint() {
        return numint;
    }

    public void setNumint(String numint) {
        this.numint = numint;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getCodigopostal() {
        return codigopostal;
    }

    public void setCodigopostal(String codigopostal) {
        this.codigopostal = codigopostal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String toString() {
        final String TAB = ", ";

        StringBuffer retValue = new StringBuffer();

        retValue.append("Address ( ").append(super.toString()).append(TAB)
        .append("addressid = ").append(this.addressid).append(TAB)
        .append("callenum = ").append(this.callenum).append(TAB)
        .append("numint = ").append(this.numint).append(TAB).append(
        "colonia = ").append(this.colonia).append(TAB).append(
        "municipio = ").append(this.municipio).append(TAB)
        .append("codigopostal = ").append(this.codigopostal)
        .append(TAB).append("estado = ").append(this.estado)
        .append(TAB).append("pais = ").append(this.pais).append(TAB)
        .append(" )");

        return retValue.toString();
    }

}
