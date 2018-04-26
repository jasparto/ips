/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.atencion.modelo;

import com.sinergia.publico.modelo.TipoComponentes;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juliano
 */
public class SeccionDetalle implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    public static final String INPUT_TEXT = "inputText";
    public static final String SELECT_ONE_MENU = "selectOneMenu";
    public static final String INPUT_TEXTAREA = "inputTextarea";

    protected SeccionDetallePK seccionDetallePK;

    private String nombre;
    private int orden;

    private SeccionDetalleCombos seccionDetalleCombos;
    private Set<SeccionDetalleCombos> seccionDetalleCombosSet = new HashSet<>();

    private Seccion seccion;
    private String valorIngresado;
    private TipoComponentes tipoComponentes;

    public SeccionDetalle() {
    }

    public SeccionDetalle(SeccionDetallePK seccionDetallePK) {
        this.seccionDetallePK = seccionDetallePK;
    }

    public SeccionDetalle(short codInstitucion, short codEstablecimiento, Long codSeccion, Long codDetalle) {
        this.seccionDetallePK = new SeccionDetallePK(codInstitucion, codEstablecimiento, codSeccion, codDetalle);
    }

    public SeccionDetallePK getSeccionDetallePK() {
        return seccionDetallePK;
    }

    public void setSeccionDetallePK(SeccionDetallePK seccionDetallePK) {
        this.seccionDetallePK = seccionDetallePK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Set<SeccionDetalleCombos> getSeccionDetalleCombosSet() {
        return seccionDetalleCombosSet;
    }

    public void setSeccionDetalleCombosSet(Set<SeccionDetalleCombos> seccionDetalleCombosSet) {
        this.seccionDetalleCombosSet = seccionDetalleCombosSet;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seccionDetallePK != null ? seccionDetallePK.hashCode() : 0);
        hash += this.orden;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeccionDetalle)) {
            return false;
        }
        SeccionDetalle other = (SeccionDetalle) object;
        if ((this.seccionDetallePK == null && other.seccionDetallePK != null) || (this.seccionDetallePK != null && !this.seccionDetallePK.equals(other.seccionDetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sinergia.atencion.modelo.SeccionDetalle[ seccionDetallePK=" + seccionDetallePK + " ]";
    }

    /**
     * @return the valorIngresado
     */
    public String getValorIngresado() {
        return valorIngresado;
    }

    /**
     * @param valorIngresado the valorIngresado to set
     */
    public void setValorIngresado(String valorIngresado) {
        this.valorIngresado = valorIngresado;
    }

    /**
     * @return the tipoComponentes
     */
    public TipoComponentes getTipoComponentes() {
        return tipoComponentes;
    }

    /**
     * @param tipoComponentes the tipoComponentes to set
     */
    public void setTipoComponentes(TipoComponentes tipoComponentes) {
        this.tipoComponentes = tipoComponentes;
    }

    /**
     * @return the orden
     */
    public int getOrden() {
        return orden;
    }

    /**
     * @param orden the orden to set
     */
    public void setOrden(int orden) {
        this.orden = orden;
    }

    /**
     * @return the seccionDetalleCombos
     */
    public SeccionDetalleCombos getSeccionDetalleCombos() {
        return seccionDetalleCombos;
    }

    /**
     * @param seccionDetalleCombos the seccionDetalleCombos to set
     */
    public void setSeccionDetalleCombos(SeccionDetalleCombos seccionDetalleCombos) {
        this.seccionDetalleCombos = seccionDetalleCombos;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Object clone;
        clone = super.clone();
        return clone;
    }
}
