/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.cliente.controlador;

import com.sinergia.cliente.dao.SubcuentasDAO;
import com.sinergia.cliente.modelo.Subcuentas;
import com.sinergia.general.controlador.Gestor;
import com.sinergia.publico.modelo.Institucion;
import com.sinergia.utilidades.modelo.UtilLog;
import java.io.Serializable;

/**
 *
 * @author juliano
 */
public class GestorSubcuentas extends Gestor implements Serializable {

    public GestorSubcuentas() throws Exception {
        super();
    }

    public int siguienteCodigoSubcuenta(Institucion institucion) throws Exception {

        try {
            this.abrirConexion();
            SubcuentasDAO subcuentasDAO = new SubcuentasDAO(session);
            return subcuentasDAO.siguienteCodigoSubcuenta(institucion);
        } finally {
            this.cerrarConexion();
        }
    }

    public Subcuentas validarSubcuenta(Subcuentas subcuentas) throws Exception {
        if (subcuentas == null || subcuentas.getSubcuentasPK() == null || subcuentas.getSubcuentasPK().getCodSubcuenta() == 0) {
            throw new Exception("No fue posible asignar el consecutivo del cliente.", UtilLog.TW_RESTRICCION);
        }
        if (subcuentas.getNombre() == null || subcuentas.getNombre().equalsIgnoreCase("")) {
            throw new Exception("Ingresa el nombre del cliente.", UtilLog.TW_RESTRICCION);
        }
        subcuentas.setNombre(subcuentas.getNombre().trim().toUpperCase());
        return subcuentas;
    }

}
