/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.publico.vista;

import com.sinergia.modelo.Dialogo;
import com.sinergia.modelo.Sesion;
import com.sinergia.publico.modelo.Establecimiento;
import com.sinergia.publico.modelo.Institucion;
import com.sinergia.utilidades.modelo.UtilJSF;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author juliano
 */
@ManagedBean(name = "uiParametros")
@RequestScoped

public class UIParametros {

    /**
     * @return the institucionEntidad
     */
    public String getInstitucionEntidad() {
        return Institucion.class.getSimpleName();
    }
    
     /**
     * @return the establecimientoEntidad
     */
    public String getEstablecimientoEntidad() {
        return Establecimiento.class.getSimpleName();
    }

}
