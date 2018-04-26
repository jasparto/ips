/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.atencion.modelo;

import com.sinergia.publico.modelo.Institucion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.primefaces.model.DualListModel;

/**
 *
 * @author juliano
 */
public class TipoRecurso implements Serializable {

    public static String CLASE_FISICO = "F";
    public static String CLASE_HUMANO = "H";
    public static int CANTIDAD_DEFECTO = 1;

//    private TipoRecursoPK tipoRecursoPK;
    private Long codTipoRecurso;

    private String nombre;
    private Boolean controlado;
    private String clase;

    private Set<Recurso> recursoSet = new HashSet<>();
    private List<Recurso> recursoOrigenList = new ArrayList<>();
    private List<Recurso> recursoSeleccionadoList = new ArrayList<>();
    private DualListModel<Recurso> recursoDualList;
    private String icon;
    private Institucion institucion;
    private TipoRecursoServiciosCantidad tipoRecursoServiciosCantidad = new TipoRecursoServiciosCantidad();
//    private Recurso recurso;

    private Set<Servicios> serviciosSet;

    public TipoRecurso(Long codTipoRecurso, String nombre) {
        this.codTipoRecurso = codTipoRecurso;
        this.nombre = nombre;
    }

    public TipoRecurso() {
    }

    @PostConstruct
    public void TipoRecurso() {
        recursoDualList = new DualListModel<>(recursoOrigenList, recursoOrigenList);
    }

    public TipoRecurso(Long codTipoRecurso) {
        this.codTipoRecurso = codTipoRecurso;
    }

//    public TipoRecurso(TipoRecursoPK tipoRecursoPK) {
//        this.tipoRecursoPK = tipoRecursoPK;
//    }
//
//    public TipoRecurso(TipoRecursoPK tipoRecursoPK, String nombre) {
//        this.tipoRecursoPK = tipoRecursoPK;
//        this.nombre = nombre;
//    }
//
//    public TipoRecurso(Long codTipoRecurso) {
//        this.tipoRecursoPK = new TipoRecursoPK(codTipoRecurso);
//    }
//
//    public TipoRecursoPK getTipoRecursoPK() {
//        return tipoRecursoPK;
//    }
//
//    public void setTipoRecursoPK(TipoRecursoPK tipoRecursoPK) {
//        this.tipoRecursoPK = tipoRecursoPK;
//    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getControlado() {
        return controlado;
    }

    public void setControlado(Boolean controlado) {
        this.controlado = controlado;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codTipoRecurso != null ? codTipoRecurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoRecurso)) {
            return false;
        }
        TipoRecurso other = (TipoRecurso) object;
        if ((this.codTipoRecurso == null && other.codTipoRecurso != null) || (this.codTipoRecurso != null && !this.codTipoRecurso.equals(other.codTipoRecurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tmp.TipoRecurso[ codTipoRecurso=" + codTipoRecurso + " ]";
    }

    /**
     * @return the recursoSet
     */
    public Set<Recurso> getRecursoSet() {
        return recursoSet;
    }

    /**
     * @param recursoSet the recursoSet to set
     */
    public void setRecursoSet(Set<Recurso> recursoSet) {
        this.recursoSet = recursoSet;
    }

    /**
     * @return the serviciosSet
     */
    public Set<Servicios> getServiciosSet() {
        return serviciosSet;
    }

    /**
     * @param serviciosSet the serviciosSet to set
     */
    public void setServiciosSet(Set<Servicios> serviciosSet) {
        this.serviciosSet = serviciosSet;
    }

//    /**
//     * @return the recurso
//     */
//    public Recurso getRecurso() {
//        return recurso;
//    }
//
//    /**
//     * @param recurso the recurso to set
//     */
//    public void setRecurso(Recurso recurso) throws CloneNotSupportedException {
//        this.recurso = (Recurso) recurso.clone();
//    }
    /**
     * @return the codTipoRecurso
     */
    public Long getCodTipoRecurso() {
        return codTipoRecurso;
    }

    /**
     * @param codTipoRecurso the codTipoRecurso to set
     */
    public void setCodTipoRecurso(Long codTipoRecurso) {
        this.codTipoRecurso = codTipoRecurso;
    }

    /**
     * @return the recursoSeleccionadoList
     */
    public List<Recurso> getRecursoSeleccionadoList() {
        return recursoSeleccionadoList;
    }

    /**
     * @param recursoSeleccionadoList the recursoSeleccionadoList to set
     */
    public void setRecursoSeleccionadoList(List<Recurso> recursoSeleccionadoList) {
        this.recursoSeleccionadoList = recursoSeleccionadoList;
    }

    /**
     * @return the recursoDualList
     */
    public DualListModel<Recurso> getRecursoDualList() {
        return recursoDualList;
    }

    /**
     * @param recursoDualList the recursoDualList to set
     */
    public void setRecursoDualList(DualListModel<Recurso> recursoDualList) {
        this.recursoDualList = recursoDualList;
    }

    /**
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return the recursoOrigenList
     */
    public List<Recurso> getRecursoOrigenList() {
        return recursoOrigenList;
    }

    /**
     * @param recursoOrigenList the recursoOrigenList to set
     */
    public void setRecursoOrigenList(List<Recurso> recursoOrigenList) {
        this.recursoOrigenList = recursoOrigenList;
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
     * @return the tipoRecursoServiciosCantidad
     */
    public TipoRecursoServiciosCantidad getTipoRecursoServiciosCantidad() {
        return tipoRecursoServiciosCantidad;
    }

    /**
     * @param tipoRecursoServiciosCantidad the tipoRecursoServiciosCantidad to set
     */
    public void setTipoRecursoServiciosCantidad(TipoRecursoServiciosCantidad tipoRecursoServiciosCantidad) {
        this.tipoRecursoServiciosCantidad = tipoRecursoServiciosCantidad;
    }
}
