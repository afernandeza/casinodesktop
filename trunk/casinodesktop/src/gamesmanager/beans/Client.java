package gamesmanager.beans;

import java.util.Date;

public class Client extends Person{

	public Client(String nombres, String appaterno, String apmaterno,
			Double credito, Character sexo, Date fechanac, Date membersince,
			String telcasa, String telcel, Address address) {
		super(nombres, appaterno, apmaterno, credito, sexo, fechanac, membersince,
				telcasa, telcel, address);
	}

	public Client(String clientid, String nombres, String appaterno,
			String apmaterno, Double credito, Character sexo, Date fechanac,
			Date membersince, String telcasa, String telcel, Address address) {
		super(clientid, nombres, appaterno, apmaterno, credito, sexo, fechanac,
				membersince, telcasa, telcel, address);
	}

}
