package com.aluracursos.desafio_literalura.desafio_literalura.repository;

import com.aluracursos.desafio_literalura.desafio_literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombre(String nombre);
}
