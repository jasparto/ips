/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.modelo;

/**
 *
 * @author Juan
 */
public class RipsInt {

    private int valor;
    private int longitud;

    public RipsInt(int valor, int longitud) {
        this.valor = valor;
        this.longitud = longitud;
    }

    /**
     * @return the valor
     */
    public int getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(int valor) {
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
