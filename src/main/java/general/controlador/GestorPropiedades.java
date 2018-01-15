/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package general.controlador;

import java.util.Properties;

/**
 *
 * @author juliano
 */
public class GestorPropiedades {

    public Properties cargarPropiedades() throws Exception {
        Properties p = new Properties();
        try {
//            p.setProperty("urlbd", "jdbc:postgresql://127.0.0.1:5434/ipsbd");
//            p.setProperty("urlbd", "jdbc:postgresql://postgresql.ipsbd.svc:5432/ipsbd");
            p.setProperty("urlbd", "jdbc:postgresql://172.30.153.239:5432/ipsbd");
            
            
//            p.setProperty("urlbd", "jdbc:postgresql://127.3.226.130:5432/cuponio");
//            p.setProperty("urlbd", "jdbc:postgresql://54dc96645973ca7c56000153-oxes.rhcloud.com:51801/cuponrs");
            p.setProperty("controlador", "org.postgresql.Driver");
//            p.setProperty("usuario", "adminvqltilx");
//            p.setProperty("clave", "2cbRV4EurrpR");
            p.setProperty("usuario", "userQA6");
            p.setProperty("clave", "Cnhw2OnLIHRINdg0");

        } catch (Exception e) {
            throw e;
        }
        return p;
    }
}
