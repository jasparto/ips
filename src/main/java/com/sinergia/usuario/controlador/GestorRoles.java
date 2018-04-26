/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.usuario.controlador;

import com.sinergia.general.controlador.Gestor;
import com.sinergia.publico.modelo.Institucion;
import com.sinergia.usuario.dao.RolesDAO;
import com.sinergia.usuario.modelo.Roles;
import com.sinergia.utilidades.modelo.UtilLog;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Hibernate;

/**
 *
 * @author juliano
 */
public class GestorRoles extends Gestor {

    public GestorRoles() throws Exception {
        super();
    }

    public Roles validarRoles(Roles roles) throws Exception {
        if (roles == null || roles.getNombre() == null || roles.getNombre().equalsIgnoreCase("")) {
            throw new Exception("Selecciona un menu de la lista.", UtilLog.TW_RESTRICCION);
        }
        return roles;
    }

    public void validarRolInstitucion(Institucion institucion) throws Exception {
        if (Hibernate.isInitialized(institucion.getRolesSet())) {
            for (Roles r : institucion.getRolesSet()) {
                if (institucion.getRoles().getNombre().equals(r.getNombre())) {
                    throw new Exception("El rol ya esta asociada para la Institución seleccionada.", UtilLog.TW_VALIDACION);
                }
            }
        }
    }

    public void guardarRolesInstitucion(Roles roles) throws Exception {
        try {
            this.abrirConexion();
            this.inicioTransaccion();
            RolesDAO rolesDAO = new RolesDAO(session);
            rolesDAO.eliminarRolesMenus(roles);
            session.saveOrUpdate(roles);
            this.finTransaccion();
        } finally {
            this.cerrarConexion();
        }
    }

}
