/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.utilidades.modelo;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juliano
 */
public class UtilBinario {

    public static boolean permisoBinario(int codPermiso, int permiso) throws Exception {
        int contador = 0, residuo;
        int potencia = (int) Math.pow(2, contador);
        boolean res = false;
        while (potencia <= codPermiso) {
            residuo = permiso % 2;
            res = residuo == 1;
            permiso = permiso / 2;
            contador++;
            potencia = (int) Math.pow(2, contador);
        }
        return res;
    }
}
