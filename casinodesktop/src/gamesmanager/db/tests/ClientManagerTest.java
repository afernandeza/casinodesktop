package gamesmanager.db.tests;

import gamesmanager.beans.Address;
import gamesmanager.beans.Client;
import gamesmanager.db.ClientManager;

import java.io.File;
import org.junit.Assert;

public class ClientManagerTest {

    //@Test
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
    
    //@Test
    public void testUpdate() {
        Client c = new Client();
        c.setId("SUCA_C166");
        c.setCredito("1120.34");
        c.setNombres("Jose Alberto");
        c.setAppaterno("Mu–iz");
        c.setApmaterno("Navarro");
        c.setSexo("F");
        c.setFechanac("1983-08-31");
        c.setFoto(new File("/Users/afa/Desktop/pics/DSCN0559.jpg"));
        c.setTelcasa("5512131415");
        c.setTelcel("04427281113");
        
        Address d = new Address();
        d.setCallenum("memorial shit");
        d.setNumint("mz 123");
        d.setColonia("mit");
        d.setMunicipio("mittermuni");
        d.setCodigopostal("10710");
        d.setEstado("MA");
        d.setPais("USA");
        
        c.setAddress(d);
        
        Assert.assertTrue(ClientManager.updateClient(c));
    }
    
    //@Test
    public void testFind(){
        Assert.assertNotNull(ClientManager.findClient("SUCA_C166"));
    }
    
    //@Test
    public void testDelete(){
        Assert.assertTrue(ClientManager.deleteClient("SUCA_C166"));
    }

}
