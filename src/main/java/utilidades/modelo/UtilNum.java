/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.utilidades.modelo;

import java.text.DecimalFormat;

/**
 *
 * @author juliano
 */
public class UtilNum {
    public static String PATRON_DECIMAL_N_NN = "#.##";
    
    public static String formatoDecimal(Double n, String patronDecimal) {
        if (n == null) {
            return null;
        }
        DecimalFormat decimalFormat = new DecimalFormat(patronDecimal);
        return decimalFormat.format(n);
    }
    
}
