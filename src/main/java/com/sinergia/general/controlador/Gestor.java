/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.general.controlador;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author juliano
 */
public abstract class Gestor extends HibernateDaoSupport {

    protected Session session;

    public Gestor() throws Exception {
        super();
    }

    public void abrirConexion() throws Exception {
        session = getSessionFactory().openSession();
    }

    public void cerrarConexion() throws Exception {
        if (session != null) {
            session.close();
        }
    }

    public void inicioTransaccion() throws Exception {
        session.beginTransaction();
    }

    public void finTransaccion() throws Exception {
        session.getTransaction().commit();
    }

    public void devolverTransaccion() throws Exception {
        session.getTransaction().rollback();
    }

    public void guardarActualizarEntidad(Object o) throws Exception {
        this.abrirConexion();
        this.inicioTransaccion();
        session.saveOrUpdate(o);
        this.finTransaccion();
        this.cerrarConexion();
    }
    
    public void eliminarEntidad(Object o) throws Exception {
        this.abrirConexion();
        this.inicioTransaccion();
        session.delete(o);
        this.finTransaccion();
        this.cerrarConexion();
    }
}
