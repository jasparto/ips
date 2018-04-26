/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.usuario.dao;

import com.sinergia.usuario.modelo.Roles;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author juliano
 */
public class RolesDAO {

    private Session session = null;

    public RolesDAO(Session session) {
        this.session = session;
    }

    public void eliminarRolesMenus(Roles roles) {
        Query query = session.createSQLQuery("DELETE FROM usuario.roles_menus WHERE cod_institucion=? AND cod_rol=?");
        query.setParameter(0, roles.getRolesPK().getCodInstitucion());
        query.setParameter(1, roles.getRolesPK().getCodigoRol());
        query.executeUpdate();
    }
    
    
}
