package com.murex.service;

import com.murex.repository.AriadnaRepositoy;
import org.springframework.beans.factory.annotation.Autowired;

public class AriadnaService {

    @Autowired
    private AriadnaRepositoy ariadnaRepositoy;

    public int obtenerContador() {
        // Obtener el total del registros en la tabla
        return (int) ariadnaRepositoy.count() + 1;
    }
}
