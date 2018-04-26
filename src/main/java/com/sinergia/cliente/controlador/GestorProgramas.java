/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.cliente.controlador;

import com.sinergia.cliente.dao.ProgramasDAO;
import com.sinergia.cliente.modelo.Programas;
import com.sinergia.cliente.modelo.ProgramasConfiguracion;
import com.sinergia.cliente.modelo.RelProgramasConfiguracion;
import com.sinergia.general.controlador.Gestor;
import com.sinergia.publico.modelo.Establecimiento;
import com.sinergia.utilidades.modelo.UtilLog;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author juliano
 */
public class GestorProgramas extends Gestor implements Serializable {

    public GestorProgramas() throws Exception {
        super();
    }

    public Programas validarProgramas(Programas programas, boolean validaPk) throws Exception {
        if (programas == null || programas.getNombre() == null || programas.getNombre().equalsIgnoreCase("")) {
            throw new Exception("Ingresa el nombre del programa.", UtilLog.TW_RESTRICCION);
        }
//        if (programas.getProgramasConfiguracion() == null || programas.getProgramasConfiguracion().getProgramasConfiguracionPK() == null) {
//            throw new Exception("Indica la configuración del programa.", UtilLog.TW_RESTRICCION);
//        }
        if (validaPk) {
            if (programas == null || programas.getProgramasPK() == null || programas.getProgramasPK().getCodPrograma() == null
                    || programas.getProgramasPK().getCodPrograma() == 0) {
                throw new Exception("No fue posible asignar el consecutivo del programa.", UtilLog.TW_RESTRICCION);
            }
            if (programas.getProgramasPK().getCodCliente() == 0 || programas.getProgramasPK().getCodCliente() == -1) {
                throw new Exception("Indica el cliente del programa.", UtilLog.TW_RESTRICCION);
            }
            if (programas.getProgramasPK().getCodEntidad() == 0 || programas.getProgramasPK().getCodEntidad() == -1) {
                throw new Exception("Indica la entidad del programa.", UtilLog.TW_RESTRICCION);
            }
            if (programas.getProgramasPK().getCodContrato() == 0 || programas.getProgramasPK().getCodContrato() == -1) {
                throw new Exception("Indica el contrato del programa.", UtilLog.TW_RESTRICCION);
            }
            if (programas.getProgramasPK().getCodSubcuenta() == 0 || programas.getProgramasPK().getCodSubcuenta() == -1) {
                throw new Exception("Indica la subcuenta del programa.", UtilLog.TW_RESTRICCION);
            }
        }
        programas.setNombre(programas.getNombre().trim().toUpperCase());
        return programas;
    }

//    public void actualizarPrograma(Programas programas, Programas programasRemover) throws Exception {
//        try {
//            this.abrirConexion();
//            this.inicioTransaccion();
//            session.delete(programasRemover);
//            session.save(programas);
//            this.finTransaccion();
//        } catch (Exception e) {
//            this.devolverTransaccion();
//            throw e;
//        } finally {
//            this.cerrarConexion();
//        }
//    }
    public Collection<? extends Establecimiento> cargarEstablecimientosProgramaConfiguracion(Programas programas, ProgramasConfiguracion programasConfiguracion) throws Exception {
        try {
            this.abrirConexion();
            ProgramasDAO programasDAO = new ProgramasDAO(session);
            return programasDAO.cargarEstablecimientosProgramaConfiguracion(programas, programasConfiguracion);
        } finally {
            this.cerrarConexion();
        }
    }

    public void almacenarEstablecimientosProgramasConfiguracion(Programas programas, ProgramasConfiguracion programasConfiguracion, List<RelProgramasConfiguracion> relProgramasConfiguracionList) throws Exception {
        try {
            this.abrirConexion();
            this.inicioTransaccion();
            ProgramasDAO programasDAO = new ProgramasDAO(session);
            programasDAO.borrarProgramasConfiguracion(programas, programasConfiguracion);
            for (RelProgramasConfiguracion r : relProgramasConfiguracionList) {
                this.session.save(r);
            }
            this.finTransaccion();
        } finally {
            this.cerrarConexion();
        }
    }

}
