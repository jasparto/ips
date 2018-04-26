/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.modelo;

import com.sinergia.publico.modelo.Establecimiento;
import com.sinergia.publico.modelo.TipoComponentes;
import com.sinergia.usuario.modelo.PermisosBinarios;
import com.sinergia.usuario.modelo.Usuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Julian
 */
@ManagedBean
@SessionScoped

public class Sesion implements Serializable {

    public static String RUTA_SERVICIO;
    public static String APP_IPS = "IPS";

    private Usuarios usuarios;
    private Establecimiento establecimiento;
    private Dialogo dialogo;
    private List<PermisosBinarios> permisosBinariosList;
    
    private List<TipoComponentes> tipoComponentesList = new ArrayList<>();

    /**
     * @return the usuario
     */
    public Usuarios getUsuario() {
        return usuarios;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuarios usuario) {
        this.usuarios = usuario;
    }

    /**
     * @return the establecimiento
     */
    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    /**
     * @param establecimiento the establecimiento to set
     */
    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }

    /**
     * @return the dialogo
     */
    public Dialogo getDialogo() {
        return dialogo;
    }

    /**
     * @param dialogo the dialogo to set
     */
    public void setDialogo(Dialogo dialogo) {
        this.dialogo = dialogo;
    }

    /**
     * @return the tipoComponentesList
     */
    public List<TipoComponentes> getTipoComponentesList() {
        return tipoComponentesList;
    }

    /**
     * @param tipoComponentesList the tipoComponentesList to set
     */
    public void setTipoComponentesList(List<TipoComponentes> tipoComponentesList) {
        this.tipoComponentesList = tipoComponentesList;
    }

    /**
     * @return the permisosBinariosList
     */
    public List<PermisosBinarios> getPermisosBinariosList() {
        return permisosBinariosList;
    }

    /**
     * @param permisosBinariosList the permisosBinariosList to set
     */
    public void setPermisosBinariosList(List<PermisosBinarios> permisosBinariosList) {
        this.permisosBinariosList = permisosBinariosList;
    }


}
