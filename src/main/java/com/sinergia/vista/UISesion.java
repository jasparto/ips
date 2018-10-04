/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.vista;

import com.sinergia.modelo.Dialogo;
import com.sinergia.modelo.Sesion;
import com.sinergia.utilidades.modelo.UtilJSF;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author juliano
 */
@ManagedBean(name = "uiSesion")
@RequestScoped
public class UISesion {
    
    private String version;

    public void limpiarDialogo() {
        Sesion sesion = (Sesion) UtilJSF.getBean("sesion");
        sesion.setDialogo(new Dialogo());
        UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);
    }

    /**
     * @return the version
     */
    public String getVersion() {
        version="Ver. ";
        version += "18.10.4.3";
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }
}
