package com.aluracursos.desafio_literalura.desafio_literalura.principal;

import com.aluracursos.desafio_literalura.desafio_literalura.model.*;
import com.aluracursos.desafio_literalura.desafio_literalura.repository.AutorRepository;
import com.aluracursos.desafio_literalura.desafio_literalura.repository.LibroRepository;
import com.aluracursos.desafio_literalura.desafio_literalura.service.ConsumoAPI;
import com.aluracursos.desafio_literalura.desafio_literalura.service.ConvierteDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    // Atributos
    private Scanner sc = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books";
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private List<Libro> libros;
    private List<Autor> autores;

    // Constructor
    public Principal(LibroRepository repository, AutorRepository autorRepository) {
        this.libroRepository = repository;
        this.autorRepository = autorRepository;
    }

    // Getters & Setters

    // Metodos Propios
    public void muestraMenu() {
        int opcion = -1;
        while (opcion != 0) {
            String menu = """
                    \n!!!BIENVENIDO A LITERALURA!!!
                    Selecciona la opcion a realizar
                    
                    1 - Buscar Libro por Titulo
                    2 - Listar Libros Registrados
                    3 - Listar Autores Registrados
                    4 - Listar Autores Vivos por Año
                    5 - Listar Libros por Idiomas\n
                    0 - Salir""";
            System.out.println(menu);
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 0:
                    System.out.println("Gracias por usar la aplicación.");
                    System.out.println("Cerrando la aplicación...");
                    break;
                case 1:
                    buscarLibrosPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresPorAnio();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                default:
                    System.out.println("Opción NO valida");
            }
        }
    }

    private void buscarLibrosPorTitulo() {
        DatosLibro datosLibro = getDatosLibro();
        if (datosLibro != null) {
            Optional<Libro> libroExistente = libroRepository.findByTitulo(datosLibro.titulo());
            if (libroExistente.isEmpty()) {
                List<Autor> autores = datosLibro.autores().stream()
                        .map(da -> autorRepository.findByNombre(da.nombre())
                                .orElseGet(() -> {
                                    Autor autor = new Autor(da);
                                    return autorRepository.save(autor);
                                }))
                        .toList();

                Libro libro = new Libro(datosLibro);
                libro.setAutores(autores);
                libroRepository.save(libro);
                System.out.println(libro.toString());
            } else {
                System.out.println("El libro ya esta registrado.");
            }
        }
    }

    private void listarLibrosRegistrados() {
        libros = libroRepository.findAll();
        libros.stream().forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        autores = autorRepository.findAll();
        autores.stream().forEach(System.out::println);
    }

    private void listarAutoresPorAnio() {
        System.out.print("Ingrese año: ");
        int anio = sc.nextInt();
        autores = libroRepository.mostrarAutoresAnio(anio);
        autores.stream().forEach(System.out::println);
    }

    private void listarLibrosPorIdioma() {
        System.out.print("Ingrese el código de idioma (ej: en, es, fr): ");
        String codigoIngresado = sc.nextLine().trim();
        if (codigoIngresado.length() != 2) {
            System.out.println("❌ Código inválido. Debe tener 2 letras (ej: en, es, fr).");
            return;
        }
        List<Libro> librosFiltrados = libroRepository.buscarPorIdioma(codigoIngresado);

        if (librosFiltrados.isEmpty()) {
            System.out.println("⚠️ No se encontraron libros en el idioma '" + codigoIngresado + "'");
        } else {
            System.out.println("📚 Libros en idioma '" + codigoIngresado + "':\n");
            librosFiltrados.forEach(System.out::println);
        }

    }

    private DatosLibro getDatosLibro() {
        System.out.print("Ingrese nombre del libro: ");
        String nombreLibro = sc.nextLine();
        String json = consumoAPI.obtenerDatos(URL_BASE, nombreLibro);
        DatosRespuesta respuesta = convierteDatos.obtenerDatos(json, DatosRespuesta.class);
        if (respuesta.results() == null || respuesta.results().isEmpty()) {
            System.out.println("No se encontro ningun libro");
            return null;
        }
        DatosLibro datosLibro = respuesta.results().get(0);
        return datosLibro;
    }


}
