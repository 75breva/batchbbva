package com.murex.model;

import com.murex.repository.AriadnaRepositoy;
import com.murex.service.AriadnaService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.management.ConstructorParameters;
import javax.persistence.*;

@Entity
@Table(name = "ariadna")
@Getter
@Setter
public class Ariadna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID en formato "ariad0001", "ariad0002"...

    @Column(name = "codestr")
    private String codestr;

    @Column(name = "producto")
    private String producto;

    @Column(name = "descripcion")
    private String descripcion;
//    @Autowired
//    AriadnaRepositoy ariadnaRepositoy;
//    @Autowired
//    AriadnaService ariadnaService;
    public Ariadna(){

    }
    public Ariadna(String codestr ,String producto,String descripcion){
        this.codestr = codestr;
        this.descripcion = descripcion;
        this.producto = producto;
    }

    private static int contador = 0; // Contador inicial

//    @PostConstruct  // metodo justo despues de que el objeto haya sido creado e inicializado
//    public void init() {
//        //contador = (int) ariadnaRepositoy.count() + 1; // Obtener total de registros
//        contador = (int) ariadnaService.obtenerContador() + 1;
//    }
//
//    @PrePersist  // meto antes de que una entidad se guarde
//    public void generarId() {
//        if (this.id == null || this.id.isEmpty()) {
//            this.id = "ariad" + String.format("%04d", contador++);
//        }
//    }

}
