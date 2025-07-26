package com.aluracursos.desafio_literalura.desafio_literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "libros_autores",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autores_id")
    )
    private List<Autor> autores;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "libros_idiomas", joinColumns = @JoinColumn(name = "libro_id"))
    @Column(name = "idioma")
    private List<String> idiomas = new ArrayList<>();

    private int numeroDescargas;


    // Constructor
    public Libro() {
    }

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.autores = datosLibro.autores() == null
                ? List.of()
                : datosLibro.autores().stream()
                .map(Autor::new)
                .toList();
        this.idiomas = datosLibro.idiomas();
        this.numeroDescargas = datosLibro.numeroDescargas();
    }

    // Metodos Getters y Setters


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public int getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(int numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    // Metodos Propios
    @Override
    public String toString() {
        return "----- LIBRO -----\n" +
                "Titulo: " + titulo + "\n" +
                "Autor(es): " + autores.stream()
                .map(Autor::getNombre)
                .collect(Collectors.joining()) + "\n" +
                "Idioma(s): " + idiomas + "\n" +
                "Numero de Descargas: " + numeroDescargas + "\n" +
                "------------------\n\n";
    }
}
