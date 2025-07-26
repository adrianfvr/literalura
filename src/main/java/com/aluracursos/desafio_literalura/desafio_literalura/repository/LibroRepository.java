package com.aluracursos.desafio_literalura.desafio_literalura.repository;

import com.aluracursos.desafio_literalura.desafio_literalura.model.Autor;
import com.aluracursos.desafio_literalura.desafio_literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByTitulo(String titulo);

    @Query("SELECT a FROM Autor a WHERE a.anioMuerte > :anio")
    List<Autor> mostrarAutoresAnio(int anio);

    @Query("SELECT l FROM Libro l JOIN l.idiomas i WHERE i = :codigo")
    List<Libro> buscarPorIdioma(@Param("codigo") String codigo);

    //List<Libro> findByIdiomasContaining(String idioma);
}
