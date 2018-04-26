/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.publico.dao;

import org.hibernate.Session;

/**
 *
 * @author juliano
 */
public class MunicipiosDAO {

    private final Session session;

    public MunicipiosDAO(Session session) {
        this.session = session;
    }
}
