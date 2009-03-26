package gamesmanager.beans;

import java.util.Date;

public class Client extends Person {

    private Double credito;
    private Date membersince;

    public Client() {
        super();
    }

    public Client(String nombres, String appaterno, String apmaterno,
            Double credito, Character sexo, Date fechanac, Date membersince,
            String telcasa, String telcel, Address address) {
        super(nombres, appaterno, apmaterno, sexo, fechanac, membersince,
                telcasa, telcel, address);
        this.credito = credito;
        this.membersince = membersince;
    }

    public Client(String clientid, String nombres, String appaterno,
            String apmaterno, Double credito, Character sexo, Date fechanac,
            Date membersince, String telcasa, String telcel, Address address) {
        super(clientid, nombres, appaterno, apmaterno, sexo, fechanac,
                membersince, telcasa, telcel, address);
        this.credito = credito;
        this.membersince = membersince;
    }

    public Double getCredito() {
        return credito;
    }

    public void setCredito(Double credito) {
        this.credito = credito;
    }

    public Date getMembersince() {
        return membersince;
    }

    public void setMembersince(Date membersince) {
        this.membersince = membersince;
    }

    public String toString() {
        final String TAB = ", ";

        StringBuffer retValue = new StringBuffer();

        retValue.append("Client ( ").append(super.toString()).append(TAB)
                .append("credito = ").append(this.credito).append(TAB).append(
                        "membersince = ").append(this.membersince).append(TAB)
                .append(" )");

        return retValue.toString();
    }
}
