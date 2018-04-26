/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.cliente.controlador;

import com.sinergia.cliente.dao.TiposContratoDAO;
import com.sinergia.cliente.modelo.TiposContrato;
import com.sinergia.general.controlador.Gestor;
import com.sinergia.publico.modelo.Institucion;
import com.sinergia.utilidades.modelo.UtilLog;
import java.io.Serializable;

/**
 *
 * @author juliano
 */
public class GestorTiposContrato extends Gestor implements Serializable {

    public GestorTiposContrato() throws Exception {
        super();
    }

    public int siguienteCodigoTipoContrato(Institucion institucion) throws Exception {
        try {
            this.abrirConexion();
            TiposContratoDAO tiposContratoDAO = new TiposContratoDAO(session);
            return tiposContratoDAO.siguienteCodigoTipoContrato(institucion);
        } finally {
            this.cerrarConexion();
        }
    }

    public TiposContrato validarTipoContrato(TiposContrato tiposContrato) throws Exception {
        if (tiposContrato == null || tiposContrato.getTiposContratoPK() == null || tiposContrato.getTiposContratoPK().getCodContrato() == 0) {
            throw new Exception("No fue posible asignar el consecutivo del cliente.", UtilLog.TW_RESTRICCION);
        }
        if (tiposContrato.getNombre() == null || tiposContrato.getNombre().equalsIgnoreCase("")) {
            throw new Exception("Ingresa el nombre del tipo de contrato.", UtilLog.TW_RESTRICCION);
        }
        tiposContrato.setNombre(tiposContrato.getNombre().trim().toUpperCase());
        return tiposContrato;
    }

}
