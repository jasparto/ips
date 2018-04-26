/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.usuario.modelo;

import java.io.Serializable;

/**
 *
 * @author juliano
 */
public class RolesPK implements Serializable {
    
    private int codInstitucion;
    private Long codigoRol;

    public RolesPK() {
    }

    public RolesPK(int codInstitucion, Long codigoRol) {
        this.codInstitucion = codInstitucion;
        this.codigoRol = codigoRol;
    }

    public int getCodInstitucion() {
        return codInstitucion;
    }

    public void setCodInstitucion(int codInstitucion) {
        this.codInstitucion = codInstitucion;
    }

    public Long getCodigoRol() {
        return codigoRol;
    }

    public void setCodigoRol(Long codigoRol) {
        this.codigoRol = codigoRol;
    }
    
}
