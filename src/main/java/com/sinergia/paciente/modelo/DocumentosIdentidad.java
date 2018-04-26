/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.paciente.modelo;

import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author juliano
 */

public class DocumentosIdentidad implements Serializable {
    
    
    private int codIdentidad;
    private String nombre;
    private String iniciales;
    private Set<Pacientes> pacientesSet;
    

    public DocumentosIdentidad() {
    }

    public DocumentosIdentidad(Short codIdentidad) {
        this.codIdentidad = codIdentidad;
    }

    public int getCodIdentidad() {
        return codIdentidad;
    }

    public void setCodIdentidad(int codIdentidad) {
        this.codIdentidad = codIdentidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIniciales() {
        return iniciales;
    }

    public void setIniciales(String iniciales) {
        this.iniciales = iniciales;
    }

    /**
     * @return the pacientesSet
     */
    public Set<Pacientes> getPacientesSet() {
        return pacientesSet;
    }

    /**
     * @param pacientesSet the pacientesSet to set
     */
    public void setPacientesSet(Set<Pacientes> pacientesSet) {
        this.pacientesSet = pacientesSet;
    }
    
}
