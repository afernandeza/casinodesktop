package gamesmanager.beans;

import gamesmanager.ui.Helpers;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client extends Person {

    private BigDecimal credito;
    private Date membersince;

    public Client() {
        super();
    }

    public Client(String nombres, String appaterno, String apmaterno,
            BigDecimal credito, Character sexo, Date fechanac, Date membersince,
            String telcasa, String telcel, Address address) {
        super(nombres, appaterno, apmaterno, sexo, fechanac, membersince,
                telcasa, telcel, address);
        this.credito = credito;
        this.membersince = membersince;
    }

    public Client(String clientid, String nombres, String appaterno,
            String apmaterno, BigDecimal credito, Character sexo, Date fechanac,
            Date membersince, String telcasa, String telcel, Address address) {
        super(clientid, nombres, appaterno, apmaterno, sexo, fechanac,
                membersince, telcasa, telcel, address);
        this.credito = credito;
        this.membersince = membersince;
    }

    public BigDecimal getCredito() {
        return credito;
    }

    public void setCredito(BigDecimal credito) {
        this.credito = credito.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    
    public void setCredito(String credito) {
        BigDecimal bd = new BigDecimal(credito);
        this.credito = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public Date getMembersince() {
        return membersince;
    }

    public void setMembersince(Date membersince) {
        this.membersince = membersince;
    }
    
    public void setMembersince(String membersince) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.membersince = sdf.parse(membersince);
        } catch (ParseException e) {
            System.out.println("wrong membersince date");
            if(Helpers.DEBUG){
                e.printStackTrace();
            }
        }
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
