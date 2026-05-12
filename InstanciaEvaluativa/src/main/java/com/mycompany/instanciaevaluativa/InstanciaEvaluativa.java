/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.instanciaevaluativa;

/**
 *
 * @author djere
 */

import java.util.ArrayList;
import java.util.Scanner;

public class InstanciaEvaluativa 
{ 
    static ArrayList<Materia> materias = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenuPrincipal();

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Entrada invalida. Debe ingresar un numero.");
                scanner.nextLine();
                opcion = 0;
            }

            switch (opcion) {
                case 1:
                    verPerfilEstudiante();
                    break;
                case 2:
                    menuGestionMaterias(scanner);
                    break;
                case 3:
                    System.out.println("\n-> Registrar asistencia (en desarrollo)");
                    break;
                case 4:
                    System.out.println("\n-> Registrar calificacion (en desarrollo)");
                    break;
                case 5:
                    System.out.println("\n-> Ver reportes (en desarrollo)");
                    break;
                case 6:
                    System.out.println("\nGracias por usar el sistema! Hasta pronto.");
                    break;
                default:
                    System.out.println("Opcion no valida. Por favor ingrese un numero entre 1 y 6.");
            }

            if (opcion != 6) {
                System.out.println("\nPresione ENTER para continuar...");
                scanner.nextLine();
            }

        } while (opcion != 6);

        scanner.close();
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n" + "=".repeat(55));
        System.out.println("       SISTEMA DE GESTION ESTUDIANTIL");
        System.out.println("=".repeat(55));
        System.out.println("1. Ver perfil del estudiante");
        System.out.println("2. Gestion de materias");
        System.out.println("3. Registrar asistencia");
        System.out.println("4. Registrar calificacion");
        System.out.println("5. Ver reportes");
        System.out.println("6. Salir");
        System.out.println("=".repeat(55));
    }

// ====================== GESTION DE MATERIAS ======================
    private static void menuGestionMaterias(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n" + "-".repeat(50));
            System.out.println("          GESTION DE MATERIAS");
            System.out.println("-".repeat(50));
            System.out.println("1. Inscribirse a una materia");
            System.out.println("2. Darse de baja de una materia");
            System.out.println("3. Listar todas las materias inscriptas");
            System.out.println("4. Buscar materia");
            System.out.println("5. Volver al menu principal");
            System.out.println("-".repeat(50));

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Entrada invalida.");
                scanner.nextLine();
                opcion = 0;
            }

            switch (opcion) {
                case 1:
                    inscribirMateria(scanner);
                    break;
                case 2:
                    darseDeBaja(scanner);
                    break;
                case 3:
                    listarMaterias();
                    break;
                case 4:
                    buscarMateria(scanner);
                    break;
                case 5:
                    System.out.println("Volviendo al menu principal...");
                    break;
                default:
                    System.out.println("Opcion no valida en el submenu.");
            }

            if (opcion != 5) {
                System.out.println("\nPresione ENTER para continuar...");
                scanner.nextLine();
            }

        } while (opcion != 5);
    }

    private static void inscribirMateria(Scanner scanner) {
        System.out.print("Nombre de la materia: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Codigo de la materia: ");
        String codigo = scanner.nextLine().trim().toUpperCase();

        if (codigo.length() < 3 || codigo.length() > 10) {
            System.out.println("Error: El codigo debe tener entre 3 y 10 caracteres.");
            return;
        }

        // Validar duplicado
        for (Materia m : materias) {
            if (m.codigo.equals(codigo)) {
                System.out.println("Error: Ya estas inscrito en esa materia (codigo duplicado).");
                return;
            }
        }

        int cuatrimestre;
        while (true) {
            System.out.print("Cuatrimestre (1 o 2): ");
            if (scanner.hasNextInt()) {
                cuatrimestre = scanner.nextInt();
                scanner.nextLine();
                if (cuatrimestre == 1 || cuatrimestre == 2) {
                    break;
                }
            } else {
                scanner.nextLine();
            }
            System.out.println("Error: Solo se permite 1 o 2.");
        }

        System.out.print("Ano: ");
        int anio = scanner.nextInt();
        scanner.nextLine();

        materias.add(new Materia(nombre, codigo, cuatrimestre, anio));
        System.out.println("Inscripcion realizada con exito!");
    }

    private static void darseDeBaja(Scanner scanner) {
        System.out.print("Ingrese el codigo de la materia a dar de baja: ");
        String codigo = scanner.nextLine().trim().toUpperCase();

        for (int i = 0; i < materias.size(); i++) {
            if (materias.get(i).codigo.equals(codigo)) {
                System.out.println("Dado de baja: " + materias.get(i).nombre);
                materias.remove(i);
                return;
            }
        }
        System.out.println("No se encontro ninguna materia con ese codigo.");
    }

    private static void listarMaterias() {
        System.out.println("\nLISTADO DE MATERIAS INSCRIPTAS");
        System.out.println("-".repeat(60));
        if (materias.isEmpty()) {
            System.out.println("No tienes materias inscritas.");
            return;
        }

        for (Materia m : materias) {
            System.out.println("Materia: " + m.nombre + " (" + m.codigo + ")");
            System.out.println("Cuatrimestre: " + m.cuatrimestre + " - Ano: " + m.anio);
            System.out.println("-".repeat(60));
        }
    }

    private static void buscarMateria(Scanner scanner) {
        System.out.print("Buscar por codigo o nombre: ");
        String busqueda = scanner.nextLine().trim().toLowerCase();

        System.out.println("\nResultados de busqueda:");
        boolean encontrado = false;

        for (Materia m : materias) {
            if (m.codigo.toLowerCase().contains(busqueda) || 
                m.nombre.toLowerCase().contains(busqueda)) {
                System.out.println("-> " + m.nombre + " (" + m.codigo + ") - " + 
                                 m.cuatrimestre + "/" + m.anio);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron materias.");
        }
    }

    private static void verPerfilEstudiante() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("           PERFIL DEL ESTUDIANTE");
        System.out.println("=".repeat(50));
        System.out.println("Nombre:     Juan Perez");
        System.out.println("Matricula:  20230045");
        System.out.println("Edad:       20 anos");
        System.out.println("Materias inscritas: " + materias.size());
        System.out.println("=".repeat(50));
    }
}

// ====================== CLASE MATERIA ======================
class Materia {
    String nombre;
    String codigo;
    int cuatrimestre;
    int anio;

    public Materia(String nombre, String codigo, int cuatrimestre, int anio) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.cuatrimestre = cuatrimestre;
        this.anio = anio;
    }
}