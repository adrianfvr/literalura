package com.aluracursos.desafio_literalura.desafio_literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosRespuesta(List<DatosLibro> results) {
}
