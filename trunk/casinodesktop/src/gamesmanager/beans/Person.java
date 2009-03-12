package gamesmanager.beans;

import java.io.InputStream;
import java.util.Date;

public class Person {

	private String id;
	private String nombres;
	private String appaterno;
	private String apmaterno;
	private Double credito;
	private Character sexo;
	private Date fechanac;
	private Date membersince;
	private String telcasa;
	private String telcel;
	private Address address;
	private InputStream foto;
	
	public Person(){
		
	}
	
	public Person(String nombres, String appaterno, String apmaterno,
			Double credito, Character sexo, Date fechanac, Date membersince,
			String telcasa, String telcel, Address address) {
		super();
		this.nombres = nombres;
		this.appaterno = appaterno;
		this.apmaterno = apmaterno;
		this.credito = credito;
		this.sexo = sexo;
		this.fechanac = fechanac;
		this.membersince = membersince;
		this.telcasa = telcasa;
		this.telcel = telcel;
		this.address = address;
	}

	public Person(String id, String nombres, String appaterno,
			String apmaterno, Double credito, Character sexo, Date fechanac,
			Date membersince, String telcasa, String telcel, Address address) {
		super();
		this.id = id;
		this.nombres = nombres;
		this.appaterno = appaterno;
		this.apmaterno = apmaterno;
		this.credito = credito;
		this.sexo = sexo;
		this.fechanac = fechanac;
		this.membersince = membersince;
		this.telcasa = telcasa;
		this.telcel = telcel;
		this.address = address;
	}

	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object o){
		if(o == this) return true;
		if(o == null || !(o instanceof Person)) return false;
		Person c = (Person) o;
		return this.id.equals(c.getId());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public InputStream getFoto() {
		return foto;
	}

	public void setFoto(InputStream foto) {
		this.foto = foto;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getAppaterno() {
		return appaterno;
	}

	public void setAppaterno(String appaterno) {
		this.appaterno = appaterno;
	}

	public String getApmaterno() {
		return apmaterno;
	}

	public void setApmaterno(String apmaterno) {
		this.apmaterno = apmaterno;
	}

	public Double getCredito() {
		return credito;
	}

	public void setCredito(Double credito) {
		this.credito = credito;
	}

	public Character getSexo() {
		return sexo;
	}

	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}

	public Date getFechanac() {
		return fechanac;
	}

	public void setFechanac(Date fechanac) {
		this.fechanac = fechanac;
	}

	public Date getMembersince() {
		return membersince;
	}

	public void setMembersince(Date membersince) {
		this.membersince = membersince;
	}

	public String getTelcasa() {
		return telcasa;
	}

	public void setTelcasa(String telcasa) {
		this.telcasa = telcasa;
	}

	public String getTelcel() {
		return telcel;
	}

	public void setTelcel(String telcel) {
		this.telcel = telcel;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
}
