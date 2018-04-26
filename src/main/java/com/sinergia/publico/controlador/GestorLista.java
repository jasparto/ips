/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.publico.controlador;

import com.sinergia.general.controlador.Gestor;
import com.sinergia.publico.modelo.Lista;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author juliano
 */
public class GestorLista extends Gestor implements Serializable {

    public GestorLista() throws Exception {
        super();
    }

    public Lista cargarLista(String nombre) {
        Object[] p = {nombre};
        Collection<Lista> l = getHibernateTemplate().find("FROM Lista L"
                + " JOIN FETCH L.detalleListaSet DL"
                + " WHERE L.nombre=?", p);
        for (Object u : l) {
            return (Lista) u;
        }
        return null;
    }

}
