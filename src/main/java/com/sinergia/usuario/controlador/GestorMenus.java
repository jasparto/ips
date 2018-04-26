/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.usuario.controlador;

import com.sinergia.general.controlador.Gestor;
import com.sinergia.publico.modelo.Institucion;
import com.sinergia.usuario.modelo.Menus;
import com.sinergia.utilidades.modelo.UtilLog;
import org.hibernate.Hibernate;

/**
 *
 * @author juliano
 */
public class GestorMenus extends Gestor {

    public GestorMenus() throws Exception {
        super();
    }

    public Menus validarMenus(Menus menus) throws Exception {
        if (menus == null || menus.getDescripcion() == null || menus.getDescripcion().equalsIgnoreCase("")) {
            throw new Exception("Selecciona un menu de la lista.", UtilLog.TW_RESTRICCION);
        }
        return menus;
    }

    public void validarMenuInstitucion(Institucion institucion) throws Exception {
        if (Hibernate.isInitialized(institucion.getMenusSet())) {
            for (Menus m : institucion.getMenusSet()) {
                if (institucion.getMenus().getDescripcion().equals(m.getDescripcion())) {
                    throw new Exception("El menu ya esta asociada para la Institución seleccionada.", UtilLog.TW_VALIDACION);
                }
            }
        }
    }

}
