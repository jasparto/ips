/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.cliente.controlador;

import com.sinergia.cliente.modelo.ProgramasConfiguracionParametros;
import com.sinergia.general.controlador.Gestor;
import com.sinergia.utilidades.modelo.UtilLog;
import java.io.Serializable;

/**
 *
 * @author juliano
 */
public class GestorProgramasConfiguracionParametros extends Gestor implements Serializable {

    public GestorProgramasConfiguracionParametros() throws Exception {
        super();
    }

    public ProgramasConfiguracionParametros validarProgramasConfiguracionParametros(ProgramasConfiguracionParametros programasConfiguracionParametros, boolean validaPk) throws Exception {
        if (programasConfiguracionParametros == null || programasConfiguracionParametros.getProgramasConfiguracionParametrosPK() == null
                || programasConfiguracionParametros.getParametrosConfiguracion() == null
                || programasConfiguracionParametros.getParametrosConfiguracion().getParametrosConfiguracionPK() == null
                || programasConfiguracionParametros.getParametrosConfiguracion().getParametrosConfiguracionPK().getCodParametro() == null
                || programasConfiguracionParametros.getParametrosConfiguracion().getParametrosConfiguracionPK().getCodParametro() == 0) {
            throw new Exception("Selecciona un parametro de la lista.", UtilLog.TW_VALIDACION);
        }
        if (programasConfiguracionParametros.getValor() == null || programasConfiguracionParametros.getValor().equalsIgnoreCase("")) {
            throw new Exception("Indica el valor del parametro.", UtilLog.TW_VALIDACION);
        }
        programasConfiguracionParametros.getProgramasConfiguracionParametrosPK().setCodParametro(programasConfiguracionParametros.getParametrosConfiguracion().getParametrosConfiguracionPK().getCodParametro());
        programasConfiguracionParametros.setValor(programasConfiguracionParametros.getValor().trim().toUpperCase());
        return programasConfiguracionParametros;
    }

}
