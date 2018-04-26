/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.modelo;

/**
 *
 * @author Juan
 */
public class RipsDouble {
    
    private double valor;
    
    private int longitud;

    public RipsDouble(double valor, int longitud) {
        this.valor = valor;
        this.longitud = longitud;
    }

    /**
     * @return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * @return the longitud
     */
    public int getLongitud() {
        return longitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }
    
    
    
}
