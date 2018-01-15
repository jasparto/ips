/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general.controlador;

import general.bd.JavaDAO;

/**
 *
 * @author juliano
 */
public class GestorJava extends Gestor {

    public GestorJava() throws Exception {
        super();
    }

    public String cargarDato() throws Exception {
        try {
            this.abrirConexion();
            JavaDAO javaDAO = new JavaDAO(conexion);
            return javaDAO.cargarDato();

        } finally {
            this.cerrarConexion();
        }
    }
}

