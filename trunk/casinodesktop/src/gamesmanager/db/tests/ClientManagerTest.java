package gamesmanager.db.tests;

import gamesmanager.beans.Address;
import gamesmanager.beans.Client;
import gamesmanager.db.ClientManager;

import java.io.File;

import junit.framework.Assert;

import org.junit.Test;

public class ClientManagerTest {

//    select insertclient(90.34, 'Alfredo', 'Fernandez', 'Acuna', 
//            'M', '1986-08-30', '01010101010101001', '5536051150', '0445554534335', 'av club de golf lomas 8',
//            'mz 3 lt 7', 'col. greenhouse', 'huixquilucan', '52779', 'Edo Mex', 'Mexico');
    
    @Test
    public void testInsert() {
        Client c = new Client();
        c.setCredito("90.34");
        c.setNombres("Jose");
        c.setAppaterno("Fern‡ndez");
        c.setApmaterno("Acu–a");
        c.setSexo("M");
        c.setFechanac("1986-08-31");
        c.setFoto(new File("/Users/afa/Desktop/pics/8423.729.486.jpg"));
        c.setTelcasa("5536051150");
        c.setTelcel("0445554534335");
        
        Address d = new Address();
        d.setCallenum("av club de golf lomas 8");
        d.setNumint("mz 3 lt 7");
        d.setColonia("greenhouse");
        d.setMunicipio("huixquilucan");
        d.setCodigopostal("52779");
        d.setEstado("Edo Mex");
        d.setPais("Mexico");
        
        c.setAddress(d);
        
        Assert.assertTrue(ClientManager.insertClient(c));
    }

}
