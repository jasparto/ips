/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.publico.controlador;

import com.sinergia.general.controlador.Gestor;
import com.sinergia.publico.modelo.Municipios;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author juliano
 */
public class GestorMunicipios extends Gestor implements Serializable {

    public GestorMunicipios() throws Exception {
        super();
    }

    public Collection<? extends Municipios> cargarMunicipios() {
         return getHibernateTemplate().find("FROM Municipios M ORDER BY M.nombre");
    }

}
