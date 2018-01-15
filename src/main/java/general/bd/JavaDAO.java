/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general.bd;

import conexion.Consulta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author juliano
 */
public class JavaDAO {

    private Connection conexion;

    public JavaDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public String cargarDato() throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT valor  FROM public.bd;"

            );
            rs = consulta.ejecutar(sql);
            rs.next();
            return rs.getString("valor");
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

}
