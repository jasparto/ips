/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.cliente.controlador;

import com.sinergia.cliente.dao.ListasPreciosDAO;
import com.sinergia.cliente.modelo.ListasPrecios;
import com.sinergia.cliente.modelo.ServiciosLista;
import com.sinergia.general.controlador.Gestor;
import com.sinergia.publico.modelo.Institucion;
import com.sinergia.utilidades.modelo.UtilLog;
import java.io.Serializable;

/**
 *
 * @author juliano
 */
public class GestorListasPrecios extends Gestor implements Serializable {

    public GestorListasPrecios() throws Exception {
        super();
    }

    public int siguienteCodigoListasPrecios(Institucion institucion) throws Exception {
        try {
            this.abrirConexion();
            ListasPreciosDAO listasPreciosDAO = new ListasPreciosDAO(session);
            return listasPreciosDAO.siguienteCodigoListasPrecios(institucion);
        } finally {
            this.cerrarConexion();
        }
    }

    public ListasPrecios validarListasPrecios(ListasPrecios listasPrecios) throws Exception {
        if (listasPrecios == null || listasPrecios.getListasPreciosPK() == null || listasPrecios.getListasPreciosPK().getCodLista() == 0) {
            throw new Exception("No fue posible asignar el consecutivo del cliente.", UtilLog.TW_RESTRICCION);
        }
        if (listasPrecios.getNombre() == null || listasPrecios.getNombre().equalsIgnoreCase("")) {
            throw new Exception("Ingresa el nombre de la lista de precios.", UtilLog.TW_RESTRICCION);
        }
        if (listasPrecios.getTipo() == null || listasPrecios.getTipo().equalsIgnoreCase("")) {
            throw new Exception("Ingresa el tipo de la lista de precios.", UtilLog.TW_RESTRICCION);
        }
        listasPrecios.setNombre(listasPrecios.getNombre().trim().toUpperCase());
        listasPrecios.setTipo(listasPrecios.getTipo().trim().toUpperCase());
        return listasPrecios;
    }

    public ServiciosLista validarServiciosLista(ServiciosLista serviciosLista) throws Exception {
        if (serviciosLista == null || serviciosLista.getServiciosListaPK() == null
                || serviciosLista.getServiciosListaPK().getCodServicio() == null || serviciosLista.getServiciosListaPK().getCodServicio() == 0) {
            throw new Exception("Selecciona un servicio de la lista.", UtilLog.TW_RESTRICCION);
        }
        if (serviciosLista.getListasPrecios() == null || serviciosLista.getListasPrecios().getListasPreciosPK() == null
                || serviciosLista.getListasPrecios().getListasPreciosPK().getCodLista() == 0
                || serviciosLista.getServiciosListaPK().getCodInstitucion() == 0) {
            throw new Exception("No se pudo procesar la información, cierra sesión e intenta de nuevo.", UtilLog.TW_RESTRICCION);
        }
        if (serviciosLista.getValor() == 0) {
            throw new Exception("Ingresa el valor del servicio.", UtilLog.TW_RESTRICCION);
        }
        return serviciosLista;
    }

}
