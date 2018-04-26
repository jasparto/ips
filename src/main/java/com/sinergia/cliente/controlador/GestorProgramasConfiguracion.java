/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.cliente.controlador;

import com.sinergia.cliente.modelo.ProgramasConfiguracion;
import com.sinergia.general.controlador.Gestor;
import com.sinergia.utilidades.modelo.UtilLog;
import java.io.Serializable;

/**
 *
 * @author juliano
 */
public class GestorProgramasConfiguracion extends Gestor implements Serializable {
    
    public GestorProgramasConfiguracion() throws Exception {
        super();
    }
    
    public ProgramasConfiguracion validarProgramasConfiguracion(ProgramasConfiguracion programasConfiguracion, boolean validaPk) throws Exception {
        if (validaPk) {
            if (programasConfiguracion == null || programasConfiguracion.getProgramasConfiguracionPK() == null
                    || programasConfiguracion.getProgramasConfiguracionPK().getCodConfiguracion() == null
                    || programasConfiguracion.getProgramasConfiguracionPK().getCodConfiguracion() == 0) {
                throw new Exception("No fue posible asignar el consecutivo del configuración programa.", UtilLog.TW_RESTRICCION);
            }
        }
        if (programasConfiguracion == null || programasConfiguracion.getNombre() == null || programasConfiguracion.getNombre().equalsIgnoreCase("")) {
            throw new Exception("Ingresa el nombre de la configuración del programa.", UtilLog.TW_RESTRICCION);
        }
        if (programasConfiguracion.getListasPrecios() == null || programasConfiguracion.getListasPrecios().getListasPreciosPK() == null
                || programasConfiguracion.getListasPrecios().getListasPreciosPK().getCodInstitucion() == 0) {
            throw new Exception("Selecciona la lista de precios de la configuración el programa.", UtilLog.TW_RESTRICCION);
        }
        programasConfiguracion.setCodLista(programasConfiguracion.getListasPrecios().getListasPreciosPK().getCodLista());
        programasConfiguracion.setNombre(programasConfiguracion.getNombre().trim().toUpperCase());
        return programasConfiguracion;
    }
    
}
