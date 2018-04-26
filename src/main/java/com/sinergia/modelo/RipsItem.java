/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.modelo;

/**
 *
 * @author Luis Alvarez
 */
public class RipsItem {
    
    private int codigo;
    
    private String texto;

    public RipsItem() {
    }

    public RipsItem(int codigo, String texto) {
        this.codigo = codigo;
        this.texto = texto;
    }

    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

}
