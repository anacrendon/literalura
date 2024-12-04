package com.catalogo.libros.console;

import com.catalogo.libros.api.GutendexClient;
import com.catalogo.libros.model.Libro;

import java.util.List;
import java.util.Scanner;

public class CatalogoLibrosApp {
    private static final GutendexClient client = new GutendexClient();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Bienvenido al Catálogo de Libros");
            System.out.println("1. Buscar libros por título");
            System.out.println("2. Buscar libros por autor");
            System.out.println("3. Listar libros por categoría");
            System.out.println("4. Ver detalles de un libro");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el título del libro: ");
                    String titulo = scanner.nextLine();
                    buscarLibrosPorTitulo(titulo);
                    break;
                case 2:
                    System.out.print("Ingrese el nombre del autor: ");
                    String autor = scanner.nextLine();
                    buscarLibrosPorAutor(autor);
                    break;
                case 3:
                    // Implementar lógica para listar libros por categoría
                    break;
                case 4:
                    System.out.print("Ingrese el ID del libro: ");
                    int idLibro = scanner.nextInt();
                    verDetallesLibro(idLibro);
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 5);

        scanner.close();
    }

    private static void buscarLibrosPorTitulo(String titulo) {
        List<Libro> libros = client.buscarLibrosPorTitulo(titulo);
        mostrarLibros(libros);
    }

    private static void buscarLibrosPorAutor(String autor) {
        List<Libro> libros = client.buscarLibrosPorAutor(autor);
        mostrarLibros(libros);
    }

    private static void mostrarLibros(List<Libro> libros) {
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros.");
        } else {
            for (Libro libro : libros) {
                System.out.println(libro);
            }
        }
    }

    private static void verDetallesLibro(int id) {
        Libro libro = client.obtenerDetallesLibro(id);
        if (libro != null) {
            System.out.println(libro);
        } else {
            System.out.println("Libro no encontrado.");
        }
    }
}