package gamesmanager.beans;

import gamesmanager.ui.Helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;

public class Person {

    private String id;
    private String nombres;
    private String appaterno;
    private String apmaterno;
    private Character sexo;
    private Date fechanac;
    private String telcasa;
    private String telcel;
    private Address address;
    private File foto;
    private ImageIcon ii;
    private InputStream fotoInputStream;
    private byte[] fotoarray;

    public Person() {

    }

    public Person(String nombres, String appaterno, String apmaterno,
            Character sexo, Date fechanac, Date membersince, String telcasa,
            String telcel, Address address) {
        super();
        this.nombres = nombres;
        this.appaterno = appaterno;
        this.apmaterno = apmaterno;
        this.sexo = sexo;
        this.fechanac = fechanac;
        this.telcasa = telcasa;
        this.telcel = telcel;
        this.address = address;
    }

    public Person(String id, String nombres, String appaterno,
            String apmaterno, Character sexo, Date fechanac, Date membersince,
            String telcasa, String telcel, Address address) {
        super();
        this.id = id;
        this.nombres = nombres;
        this.appaterno = appaterno;
        this.apmaterno = apmaterno;
        this.sexo = sexo;
        this.fechanac = fechanac;
        this.telcasa = telcasa;
        this.telcel = telcel;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null || !(o instanceof Person))
            return false;
        Person c = (Person) o;
        return this.id.equals(c.getId());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public File getSelectedFoto() {
        return foto;
    }

    public void setFoto(byte[] array){
        this.fotoarray = array;
    }
    
    public ImageIcon getFotoImageIcon(){
        this.ii = new ImageIcon(this.fotoarray);
        if(this.ii == null){
            throw new NullPointerException("Foto no ha sido creado");
        }
        return this.ii;
    }
    
    public void setFoto(File foto) {
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

    public Character getSexo() {
        return sexo;
    }

    public int getSexoIndex() {
        if (this.sexo.equals('M')) {
            return 0;
        } else if (this.sexo.equals('F')) {
            return 1;
        } else {
            if (Helpers.DEBUG) {
                throw new IllegalArgumentException("Sexo invalido");
            }
            return -1;
        }
    }

    public void setSexo(String sexo) {
        if (sexo == null || sexo.length() < 1) {
            if (Helpers.DEBUG) {
                throw new IllegalArgumentException("Sexo invalido");
            }
        }
        this.sexo = sexo.charAt(0);
    }

    public void setSexo(int index) {
        if (index == 0) {
            this.sexo = 'M';
        } else if (index == 1) {
            this.sexo = 'F';
        } else {
            if (Helpers.DEBUG) {
                throw new IllegalArgumentException("Sexo invalido");
            }
        }
    }

    public void setSexo(Character sexo) {
        if (sexo.equals('M') || sexo.equals('F')) {
            this.sexo = sexo;
        } else {
            throw new IllegalArgumentException("Sexo invalido");
        }
    }

    public Date getFechanac() {
        return fechanac;
    }

    public void setFechanac(String fechanac) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.fechanac = sdf.parse(fechanac);
        } catch (ParseException e) {
            System.out.println("wrong birthdate");
            if (Helpers.DEBUG) {
                e.printStackTrace();
            }
        }
    }

    public void setFechanac(Date fechanac) {
        this.fechanac = fechanac;
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

    public String getFullName() {
        return this.id + ": " + this.appaterno + " " + this.apmaterno + " "
                + this.nombres;
    }

    public String toString() {
        final String TAB = ", ";

        StringBuffer retValue = new StringBuffer();

        retValue.append("Person ( ").append(super.toString()).append(TAB)
                .append("id = ").append(this.id).append(TAB).append(
                        "nombres = ").append(this.nombres).append(TAB).append(
                        "appaterno = ").append(this.appaterno).append(TAB)
                .append("apmaterno = ").append(this.apmaterno).append(TAB)
                .append("sexo = ").append(this.sexo).append(TAB).append(
                        "fechanac = ").append(this.fechanac).append(TAB)
                .append("telcasa = ").append(this.telcasa).append(TAB).append(
                        "telcel = ").append(this.telcel).append(TAB).append(
                        "address = ").append(this.address).append(TAB).append(
                        "foto = ").append(this.foto).append(TAB).append(" )");

        return retValue.toString();
    }

    public InputStream getNewFotoInputStream() {
        try {
            if(this.foto == null){
                System.out.println("foto nula");
            }
            this.fotoInputStream = new FileInputStream(this.foto);
        } catch (FileNotFoundException e) {
            System.out.println("photo not found");
            if (Helpers.DEBUG) {
                e.printStackTrace();
            }
        }
        return this.fotoInputStream;
    }
    
    public InputStream getCurrentFotoInputStream() {
        return this.fotoInputStream;
    }

    public void setFotoInputStream(InputStream fotoInputStream) {
        this.fotoInputStream = fotoInputStream;
    }

}
