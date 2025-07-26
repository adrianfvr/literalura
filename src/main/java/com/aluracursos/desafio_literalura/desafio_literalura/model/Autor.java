package com.aluracursos.desafio_literalura.desafio_literalura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String nombre;
    private int anioNacimiento;
    private int anioMuerte;
    @ManyToMany(mappedBy = "autores", fetch = FetchType.EAGER)
    private List<Libro> libros;

    // Constructor
    public Autor() {
    }

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.anioNacimiento = datosAutor.anioNacimiento();
        this.anioMuerte = datosAutor.anioMuerte();
    }

    // Metodos Getters y Setters
    public int getAnioMuerte() {
        return anioMuerte;
    }

    public void setAnioMuerte(int anioMuerte) {
        this.anioMuerte = anioMuerte;
    }

    public int getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(int anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    // Metodos Propios
    @Override
    public String toString() {
        return "\n----- AUTOR -----\n" +
                " Nombre: " + nombre + "\n" +
                " Año Nacimiento: " + anioNacimiento + '\n' +
                " Año Fallecimiento: " + anioMuerte + '\n' +
                " Libros: " + libros.stream()
                .map(Libro::getTitulo)
                .collect(Collectors.toList());
    }
}
