/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.cliente.controlador;

import com.sinergia.cliente.dao.ClientesDAO;
import com.sinergia.cliente.modelo.Clientes;
import com.sinergia.cliente.modelo.Programas;
import com.sinergia.general.controlador.Gestor;
import com.sinergia.publico.modelo.Establecimiento;
import com.sinergia.publico.modelo.Institucion;
import com.sinergia.utilidades.modelo.UtilLog;
import java.io.Serializable;
import java.util.Collection;
import org.hibernate.Hibernate;

/**
 *
 * @author juliano
 */
public class GestorClientes extends Gestor implements Serializable {

    public GestorClientes() throws Exception {
        super();
    }

    public Collection<? extends Clientes> cargarClientesEstablecimiento(Establecimiento establecimiento) throws Exception {
        try {
            this.abrirConexion();
            ClientesDAO clientesDAO = new ClientesDAO(session);
            return clientesDAO.cargarClientesEstablecimiento(establecimiento);
        } finally {
            this.cerrarConexion();
        }

    }

    public void validarClientePrograma(Clientes clientesSeleccionado, Programas programasSeleccionado) throws Exception {
        if (clientesSeleccionado == null || clientesSeleccionado.getClientesPK() == null
                || clientesSeleccionado.getClientesPK().getCodCliente() == 0
                || clientesSeleccionado.getClientesPK().getCodCliente() == -1) {
            throw new Exception("Seleccione el cliente del paciente, en la pestaña Factura", UtilLog.TW_VALIDACION);
        }
        if (programasSeleccionado == null || programasSeleccionado.getProgramasPK() == null
                || programasSeleccionado.getProgramasPK().getCodCliente() == 0) {
            throw new Exception("Seleccione el programa del cliente, en la pestaña Factura", UtilLog.TW_VALIDACION);
        }
        if (programasSeleccionado.getProgramasConfiguracion().getListasPrecios() == null
                || programasSeleccionado.getProgramasConfiguracion().getListasPrecios().getServiciosListaSet() == null
                || !Hibernate.isInitialized(programasSeleccionado.getProgramasConfiguracion().getListasPrecios().getServiciosListaSet())) {
            throw new Exception("El servicio asociado a la cita no se encuentra valorizado, no se puede continuar.", UtilLog.TW_VALIDACION);
        }
    }

    public Clientes validarCliente(Clientes clientes) throws Exception {
        if (clientes == null || clientes.getClientesPK() == null || clientes.getClientesPK().getCodCliente() == 0) {
            throw new Exception("No fue posible asignar el consecutivo del cliente.", UtilLog.TW_RESTRICCION);
        }
        if (clientes.getNombre() == null || clientes.getNombre().equalsIgnoreCase("")) {
            throw new Exception("Ingresa el nombre del cliente.", UtilLog.TW_RESTRICCION);
        }
        if (clientes.getNit() == null || clientes.getNit().equalsIgnoreCase("")) {
            throw new Exception("Ingresa el nit del cliente.", UtilLog.TW_RESTRICCION);
        }
        if (clientes.getDireccion() == null || clientes.getDireccion().equalsIgnoreCase("")) {
            throw new Exception("Ingresa la dirección del cliente.", UtilLog.TW_RESTRICCION);
        }
        if (clientes.getTelefono() == null || clientes.getTelefono().equalsIgnoreCase("")) {
            throw new Exception("Ingresa el telefono del cliente.", UtilLog.TW_RESTRICCION);
        }
        clientes.setNombre(clientes.getNombre().trim().toUpperCase());
        clientes.setNit(clientes.getNit().trim().toUpperCase());
        clientes.setDireccion(clientes.getDireccion().trim().toUpperCase());
        return clientes;
    }

    public int siguienteCodigoCliente(Institucion institucion) throws Exception {
        try {
            this.abrirConexion();
            ClientesDAO clientesDAO = new ClientesDAO(session);
            return clientesDAO.siguienteCodigoCliente(institucion);
        } finally {
            this.cerrarConexion();
        }
    }

}
