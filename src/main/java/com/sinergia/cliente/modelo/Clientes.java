/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.cliente.modelo;

import com.sinergia.publico.modelo.Establecimiento;
import com.sinergia.publico.modelo.Institucion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juliano
 */
public class Clientes implements Serializable {

    private static final long serialVersionUID = 1L;

    protected ClientesPK clientesPK;
    private String nombre;
    private String nit;
    private String direccion;
    private String telefono;
    private boolean activo;
    private Set<Programas> programasSet;
//    private Establecimiento establecimiento;
    private Institucion institucion;

    public Clientes() {
    }

    public Clientes(ClientesPK clientesPK) {
        this.clientesPK = clientesPK;
    }

    public Clientes(ClientesPK clientesPK, String nombre) {
        this.clientesPK = clientesPK;
        this.nombre = nombre;
    }

    public Clientes(int codInstitucion, int codCliente) {
        this.clientesPK = new ClientesPK(codInstitucion, codCliente);
    }

    public ClientesPK getClientesPK() {
        return clientesPK;
    }

    public void setClientesPK(ClientesPK clientesPK) {
        this.clientesPK = clientesPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Programas> getProgramasList() {
        List<Programas> l = new ArrayList<>();
        if (this.programasSet != null) {
            l.addAll(this.programasSet);
        }
        return l;
    }

    @XmlTransient
    public Set<Programas> getProgramasSet() {
        return programasSet;
    }

    public void setProgramasSet(Set<Programas> programasSet) {
        this.programasSet = programasSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clientesPK != null ? clientesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clientes)) {
            return false;
        }
        Clientes other = (Clientes) object;
        if ((this.clientesPK == null && other.clientesPK != null) || (this.clientesPK != null && !this.clientesPK.equals(other.clientesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.cliente.modelo.Clientes[ clientesPK=" + clientesPK + " ]";
    }

    /**
     * @return the institucion
     */
    public Institucion getInstitucion() {
        return institucion;
    }

    /**
     * @param institucion the institucion to set
     */
    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }

    /**
     * @return the activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}
